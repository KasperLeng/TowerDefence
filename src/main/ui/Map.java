package ui;

import model.Buildings;
import model.GameBoard;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/*
 * Panel for players to place their buildings.
 */
public class Map extends GamePanel {
    private int height = GameBoard.ymax * Position.pixelSize;
    private int width = GameBoard.xmax * Position.pixelSize;
    private SidePanel sidePanel; // Reference to the SidePanel
    private Image backgroundImage; // Background
    private Image towerImage;
    private Image mineImage;

    public Map(GameBoard gb, ActionListener al) throws FileNotFoundException {
        super(gb);
        gameBoard = gb;
        backgroundImage = new ImageIcon(getClass().getResource("/resources/background.png")).getImage();
        towerImage = new ImageIcon(getClass().getResource("/resources/tower.png")).getImage();
        mineImage = new ImageIcon(getClass().getResource("/resources/mine.png")).getImage();
        

        setBounds(210, 0, width, height);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Validate the click is within bounds
                if (isValidClick(e.getX(), e.getY())) {
                    Position closestPosition = getClosestGridPosition(e.getX(), e.getY());
                    System.out.println("Closest: " + closestPosition.getRow() + " ," + closestPosition.getColumn());
                    if (sidePanel != null && sidePanel.getState() == 1) {
                        sidePanel.placeArcherTower(closestPosition);
                    } else if (sidePanel != null && sidePanel.getState() == 2) {
                        sidePanel.placeGoldMine(closestPosition);
                    }
                }
            }
        });
    }

    /**
     * Sets the SidePanel reference for communication.
     *
     */
    public void setSidePanel(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
    }

    /**
     * Converts a given x, y coordinate to the closest grid position.
     */
    private Position getClosestGridPosition(int x, int y) {
        int row = y / Position.pixelSize;
        int column = (x - 210) / Position.pixelSize;

        // Ensure the row and column are within bounds
        row = Math.max(0, Math.min(GameBoard.ymax - 1, row));
        column = Math.max(0, Math.min(GameBoard.xmax - 1, column));

        return new Position(row, column);
    }

    private boolean isValidClick(int x, int y) {
        // Validate based on the map's panel bounds
        return x >= 210 && x < getWidth() && y >= 0 && y < getHeight();
    }

    /**
     * Updates the map by calling repaint, ensuring all buildings are redrawn.
     */
    public void updateMap() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }
        sidePanel.updateLabels();

        // Draw buildings dynamically on top of the background
        for (Buildings building : gameBoard.getBuildings()) {
            drawBuilding(g, building);
        }
    }

    private void drawBuilding(Graphics g, Buildings building) {
        int x = building.getPosition().getColumn() * Position.pixelSize;
        int y = building.getPosition().getRow() * Position.pixelSize;

        if (building.getType().equals("Archer Tower")) {
            g.drawImage(towerImage, x+210, y, Position.pixelSize, Position.pixelSize, this);
        } else if (building.getType().equals("Gold Mine")) {
            g.drawImage(mineImage, x+210, y, Position.pixelSize, Position.pixelSize, this);
        }
    }

    @Override
    protected void processCommand(String command) throws FileNotFoundException {
        throw new UnsupportedOperationException("Unimplemented method 'processCommand'");
    }

}
