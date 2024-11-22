package ui;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;

import model.GameBoard;

/*
 * Intro Panel includes functions to view the game log(Not implemented), load game, and start new game.
 */
public class IntroPanel extends GamePanel {
    private JButton newGame;
    private JButton loadGame;
    private JButton gameLog;
    private SidePanel sidePanel;
    private Map map;
    private ActionListener al;

    /*
     * MODIFIES: this
     * EFFECTS: Initializes the IntroPanel with game buttons, map, and side panel.
     */
    public IntroPanel(GameBoard gb, ActionListener al) throws FileNotFoundException {
        super(gb);
        gameBoard = gb;
        this.al = al;

        // Add buttons
        newGame = new JButton("NEW GAME");
        loadGame = new JButton("LOAD GAME");
        gameLog = new JButton("GAME LOG");

        setUpButtons(newGame);
        newGame.setBounds(190, 390, 150, 40);
        setUpButtons(loadGame);
        loadGame.setBounds(590, 390, 150, 40);
        setUpButtons(gameLog);
        gameLog.setBounds(40, 30, 80, 35);
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
        btn.setForeground(java.awt.Color.BLACK);
        btn.setBackground(java.awt.Color.WHITE);
        btn.setFocusable(false);
        btn.addActionListener(al);
        btn.setBorder(null);
        btn.setBorderPainted(false);
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
        add(gameLog);
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
            Main.disposeIntro();
        } else if (command.equals("LOAD GAME")) {
            sidePanel.loadGame();
            sidePanel.updateLabels();
            new GameBoardFrame(sidePanel, map);
            Main.disposeIntro();
        } else if (command.equals("GAME LOG")) {
            // Handle game log command
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
