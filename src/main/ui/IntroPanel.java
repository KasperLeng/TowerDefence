package ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import model.GameBoard;

/*
 * Intro Panel includes functions to view the game log(Not implemented), load game, and start new game.
 */
public class IntroPanel extends GamePanel {
    private JButton newGame;
    private JButton loadGame;
    private SidePanel sidePanel;
    private Map map;
    private ActionListener al;
    private Image backgroundImage;

    /*
     * MODIFIES: this
     * EFFECTS: Initializes the IntroPanel with game buttons, map, and side panel.
     */
    public IntroPanel(GameBoard gb, ActionListener al) throws FileNotFoundException {
        super(gb);
        gameBoard = gb;
        this.al = al;
        backgroundImage = new ImageIcon(getClass().getResource("/resources/introBackground.png")).getImage();

        // Add buttons
        newGame = new JButton("NEW GAME");
        loadGame = new JButton("LOAD GAME");


        setUpButtons(newGame);
        newGame.setBounds(190, 390, 150, 60);
        setUpButtons(loadGame);
        loadGame.setBounds(590, 390, 150, 60);
        addButtons();

        // Initialize map and sidePanel without cross-references
        map = new Map(gameBoard, al);
        sidePanel = new SidePanel(gameBoard, al);

        // Set cross-references after initialization
        sidePanel.setMap(map);
        map.setSidePanel(sidePanel);
    }

    /*
     * MODIFIES: btn
     * EFFECTS: Configures the appearance and behavior of the given button.
     */
    private JButton setUpButtons(JButton btn) {
        btn.setForeground(java.awt.Color.WHITE);
        btn.setBackground(java.awt.Color.BLACK);
        btn.setFocusable(false);
        btn.addActionListener(al);
        btn.setBorder(null);
        btn.setBorderPainted(true);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        return btn;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds the game buttons to the IntroPanel.
     */
    private void addButtons() {
        add(newGame);
        add(loadGame);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user commands to start a new game, load a game, or show
     * game logs.
     */
    @Override
    protected void processCommand(String command) {
        if (command.equals("NEW GAME")) {
            new GameBoardFrame(sidePanel, map);
            gameBoard.newGame();
            Main.disposeIntro();
        } else if (command.equals("LOAD GAME")) {
            sidePanel.loadGame();
            sidePanel.updateLabels();
            gameBoard.loadGame();
            new GameBoardFrame(sidePanel, map);
            Main.disposeIntro();
        } else {
            // Delegate any side panel commands to sidePanel's processCommand
            sidePanel.processCommand(command);
        }
    }

    /*
     * EFFECTS: Returns the SidePanel associated with this IntroPanel.
     */
    public SidePanel getSidePanel() {
        return sidePanel;
    }

    /*
     * EFFECTS: Returns the Map associated with this IntroPanel.
     */
    public Map getMap() {
        return map;
    }
}
