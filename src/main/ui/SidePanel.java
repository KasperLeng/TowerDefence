package ui;

import model.ArcherTower;
import model.Buildings;
import model.GameBoard;
import model.GoldMine;
import model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.*;

/*
 * Side panel provides players buttons to buy, sell buildings, save game, and start round. 
 */
public class SidePanel extends GamePanel {
    private int height = GameBoard.ymax * Position.pixelSize;
    private ActionListener al;
    private int canSelectPosition = 0; // Indicates the state (buying Archer Tower or Gold Mine)
    private Map map;
    private int selectedTower;
    private int selectedMine;

    // Labels
    private JLabel nameLabel;
    private JLabel roundLabel;
    private JLabel moneyLabel;
    private JLabel purchaseLabel;
    private JLabel archerTowerLabel;
    private JLabel goldMineLabel;

    // Buttons
    private JButton buyTower;
    private JButton buyMine;
    private JButton sellTower;
    private JButton sellMine;
    private JButton saveGame;
    private JButton startGame;

    // ComboBoxs
    private JComboBox<String> archerTowerComboBox;
    private JComboBox<String> goldMineComboBox;

    // MODIFIES: this
    // EFFECTS: initializes a the buttons in the side panel.
    public SidePanel(GameBoard gb, ActionListener al) throws FileNotFoundException {
        super(gb);
        gameBoard = gb;
        this.al = al;
        selectedTower = -1;
        selectedMine = -1;
        setBounds(0, 0, 210, height);
        setBackground(Color.LIGHT_GRAY);
        setLayout(null);
        addLabels();
        addButtons();
        addComboBoxes();
    }

    /*
     * MODIFIES: archerTowerComboBox
     * EFFECTS: Updates Archer Tower combo box to display all Archer Towers on the
     * game board.
     */
    private void updateArcherTowerComboBox() {
        // Save the current selection
        String previousSelection = (String) archerTowerComboBox.getSelectedItem();

        // Temporarily remove the ActionListener to avoid triggering it during updates
        ActionListener[] listeners = archerTowerComboBox.getActionListeners();
        for (ActionListener listener : listeners) {
            archerTowerComboBox.removeActionListener(listener);
        }

        // Update the combo box items
        archerTowerComboBox.removeAllItems();
        ArrayList<Buildings> buildings = gameBoard.getBuildings();
        for (Buildings building : buildings) {
            if (building instanceof ArcherTower) {
                ArcherTower tower = (ArcherTower) building;
                archerTowerComboBox.addItem(
                        "Archer Tower " + tower.getPosition().getRow() + ", " + tower.getPosition().getColumn());
            }
        }
        if (previousSelection != null) {
            archerTowerComboBox.setSelectedItem(previousSelection);
        }

        for (ActionListener listener : listeners) {
            archerTowerComboBox.addActionListener(listener);
        }
    }

    /*
     * MODIFIES: goldMineComboBox
     * EFFECTS: Updates Gold Mine combo box to display all Gold Mine on the game
     * board.
     */
    private void updateGoldMineComboBox() {
        // Save the current selection
        String previousSelection = (String) goldMineComboBox.getSelectedItem();

        // Temporarily remove the ActionListener to avoid triggering it during updates
        ActionListener[] listeners = goldMineComboBox.getActionListeners();
        for (ActionListener listener : listeners) {
            goldMineComboBox.removeActionListener(listener);
        }

        // Update the combo box items
        goldMineComboBox.removeAllItems();
        ArrayList<Buildings> buildings = gameBoard.getBuildings();
        for (Buildings building : buildings) {
            if (building instanceof GoldMine) {
                GoldMine mine = (GoldMine) building;
                goldMineComboBox
                        .addItem("Gold Mine " + mine.getPosition().getRow() + ", " + mine.getPosition().getColumn());
            }
        }
        if (previousSelection != null) {
            goldMineComboBox.setSelectedItem(previousSelection);
        }

        for (ActionListener listener : listeners) {
            goldMineComboBox.addActionListener(listener);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds combo boxes for Archer Towers and Gold Mines to the panel.
     */
    public void addComboBoxes() {
        archerTowerComboBox = new JComboBox<>();
        archerTowerComboBox.setPreferredSize(new Dimension(180, 30));
        archerTowerComboBox.setBounds(20, 270, 140, 35);
        archerTowerComboBox.setForeground(Color.BLACK);
        archerTowerComboBox.setBackground(Color.WHITE);
        archerTowerComboBox.setActionCommand("COMBO_BOX_TOWER");
        archerTowerComboBox.addActionListener(al);

        goldMineComboBox = new JComboBox<>();
        goldMineComboBox.setPreferredSize(new Dimension(180, 30));
        goldMineComboBox.setBounds(20, 430, 140, 35);
        goldMineComboBox.setForeground(Color.BLACK);
        goldMineComboBox.setBackground(Color.WHITE);
        goldMineComboBox.setActionCommand("COMBO_BOX_MINE");
        goldMineComboBox.addActionListener(al);

        add(archerTowerComboBox);
        add(goldMineComboBox);
    }

    public void setUpButtons(JButton button) {
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFocusable(false);
        button.addActionListener(al);
    }

    /*
     * Adds Buttons to panel
     */
    public void addButtons() {
        buyTower = new JButton("BUY");
        buyTower.setBounds(20, 190, 100, 35);
        buyTower.setActionCommand("BUY_TOWER");

        buyMine = new JButton("BUY");
        buyMine.setBounds(20, 350, 100, 35);
        buyMine.setActionCommand("BUY_MINE");

        sellTower = new JButton("SELL");
        sellTower.setBounds(20, 230, 100, 35);
        sellTower.setActionCommand("SELL_TOWER");

        sellMine = new JButton("SELL");
        sellMine.setBounds(20, 390, 100, 35);
        sellMine.setActionCommand("SELL_MINE");

        saveGame = new JButton("SAVE");
        saveGame.setBounds(20, 500, 80, 35);
        saveGame.setActionCommand("SAVE_GAME");

        startGame = new JButton("START");
        startGame.setBounds(120, 500, 80, 35);
        startGame.setActionCommand("START_ROUND");

        addAndSetUpButtons();
    }

    private void addAndSetUpButtons() {
        setUpButtons(buyTower);
        setUpButtons(buyMine);
        setUpButtons(sellTower);
        setUpButtons(sellMine);
        setUpButtons(saveGame);
        setUpButtons(startGame);
        add(buyTower);
        add(buyMine);
        add(saveGame);
        add(startGame);
        add(sellTower);
        add(sellMine);
    }

    /*
     * Loads saved game
     */
    public void loadGame() {
        loadGameBoard(); // Load the saved game data
        updateLabels(); // Refresh labels to display the loaded data
        if (map != null) {
            map.updateMap(); // Refresh the map to show the loaded buildings
        }
    }

    /*
     * Initializes labels
     */
    public void addLabels() {
        nameLabel = new JLabel("Name: " + gameBoard.getName());
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(20, 20, 200, 30);

        roundLabel = new JLabel("Round: " + gameBoard.getRound());
        roundLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        roundLabel.setForeground(Color.BLACK);
        roundLabel.setBounds(20, 50, 200, 30);

        moneyLabel = new JLabel("Balance: " + gameBoard.getMoney() + " $");
        moneyLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        moneyLabel.setForeground(Color.BLACK);
        moneyLabel.setBounds(20, 80, 200, 60);

        purchaseLabel = new JLabel("Purchase");
        purchaseLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        purchaseLabel.setForeground(Color.BLACK);
        purchaseLabel.setBounds(60, 120, 160, 60);

        archerTowerLabel = new JLabel("Archer Tower " + "($100)");
        archerTowerLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        archerTowerLabel.setForeground(Color.BLACK);
        archerTowerLabel.setBounds(20, 140, 180, 60);

        addLabelsToPanel();
    }

    /*
     * Adds the labels to panel
     */
    private void addLabelsToPanel() {
        goldMineLabel = new JLabel("GoldMine " + "($200)");
        goldMineLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        goldMineLabel.setForeground(Color.BLACK);
        goldMineLabel.setBounds(20, 300, 180, 60);

        add(nameLabel);
        add(roundLabel);
        add(moneyLabel);
        add(purchaseLabel);
        add(archerTowerLabel);
        add(goldMineLabel);
    }

    /*
     * Refreshes labels
     */
    public void updateLabels() {
        nameLabel.setText("Name: " + gameBoard.getName());
        roundLabel.setText("Round: " + gameBoard.getRound());
        moneyLabel.setText("Balance: " + gameBoard.getMoney() + " $");

        // Remove all existing building labels and re-add them
        updateArcherTowerComboBox();
        updateGoldMineComboBox();

        // Revalidate and repaint to ensure the panel updates correctly
        this.revalidate();
        this.repaint();
    }

    /*
     * Returns the index of selected buildings
     */
    private void getSelectedBuilding() {
        ArrayList<Buildings> buildings = gameBoard.getBuildings();
        for (int i = 0; i < buildings.size(); i++) {
            Position pos = buildings.get(i).getPosition();
            if (archerTowerComboBox.getSelectedItem() == null) {
                selectedTower = -1;
            } else if (goldMineComboBox.getSelectedItem() == null) {
                selectedMine = -1;
            } else if ((buildings.get(i).getType() + " " + pos.getRow() + ", " + (pos.getColumn()))
                    .equals(archerTowerComboBox.getSelectedItem())) {
                selectedTower = i;
            } else if (goldMineComboBox.getSelectedItem() != null
                    && (buildings.get(i).getType() + " " + pos.getRow() + ", " + (pos.getColumn()))
                            .equals(goldMineComboBox.getSelectedItem())) {
                selectedMine = i;
            }
        }
    }

    /*
     * Processes commands of the buttons
     */
    @Override
    protected void processCommand(String command) {
        if ("BUY_TOWER".equals(command)) {
            canSelectPosition = 1; // Set state to buy Archer Tower
        } else if ("BUY_MINE".equals(command)) {
            canSelectPosition = 2; // Set state to buy Gold Mine
        } else if ("SAVE_GAME".equals(command)) {
            super.saveGameBoard();
            gameBoard.gameSaved();
        } else if ("SELL_TOWER".equals(command)) {
            getSelectedBuilding();
            try {
                if (selectedTower >= 0) {
                    gameBoard.buildingRemoved(selectedTower);
                    gameBoard.getBuildings().remove(selectedTower);
                    gameBoard.addMoney(100);
                    updateLabels();
                    map.updateMap();
                }
            } catch (IndexOutOfBoundsException e) {
                // Expected
            }
        } else if ("SELL_MINE".equals(command)) {
            getSelectedBuilding();
            try {
                if (selectedMine >= 0) {
                    gameBoard.buildingRemoved(selectedMine);
                    gameBoard.getBuildings().remove(selectedMine);
                    gameBoard.addMoney(200);
                    updateLabels();
                    map.updateMap();
                }
            } catch (IndexOutOfBoundsException e) {
                // Expected
            }
        }

    }

    /*
     * Cross reference with map
     */
    public void setMap(Map map) {
        this.map = map; // Set map reference after initialization
    }

    /*
     * Returns get State
     */
    public int getState() {
        return canSelectPosition;
    }

    /*
     * Places Archer Towers
     */
    public void placeArcherTower(Position position) {
        if (position != null && checkValidPosition(position)) {
            Buildings archerTower = new ArcherTower(gameBoard.getBuildings().size() + 1, position, 100);
            if (gameBoard.getMoney() >= archerTower.getCost()) {
                GameBoard.addBuilding(archerTower);
                gameBoard.spendMoney(archerTower.getCost());
                updateLabels();
                map.updateMap(); // Update map to reflect the placed building
            }
            canSelectPosition = 0;
        }
    }

    /*
     * Places gold mine
     */
    public void placeGoldMine(Position position) {
        if (position != null) {
            Buildings goldMine = new GoldMine(gameBoard.getBuildings().size() + 1, position, 200);
            if (gameBoard.getMoney() >= goldMine.getCost()) {
                GameBoard.addBuilding(goldMine);
                gameBoard.spendMoney(goldMine.getCost());
                updateLabels();
                map.updateMap(); // Update map to reflect the placed building
            }
            canSelectPosition = 0;
        }
    }

    /*
     * checks if position is valid
     */
    public Boolean checkValidPosition(Position position) {
        for (Buildings i : gameBoard.getBuildings()) {
            if (i.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Adds lines
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(10, 153, 50, 153);
        g.drawLine(140, 153, 180, 153);
    }
}
