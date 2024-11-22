package ui;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import model.GameBoard;

/*
 * Main class that creates new gameBoard, and calls the intro frame.
 */
public class Main {
    private ActionHandler introActionHandler;
    private GamePanel introPanel;
    private static IntroFrame introFrame;
    private GameBoard gameBoard;

    // MODIFIES: this
    // EFFECTS: Constructs main window in which the game will be played
    public Main() throws FileNotFoundException {
        gameBoard = new GameBoard("Kasper's Game", 0); // Shared GameBoard instance

        introActionHandler = new ActionHandler();

        introPanel = new IntroPanel(gameBoard, introActionHandler);
        introPanel.setLayout(null);

        introFrame = new IntroFrame(introPanel);
    }

    // ActionListener for IntroPanel
    public class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            try {
                introPanel.processCommand(e.getActionCommand());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    // ActionListener for SidePanel
    public class SidePanelActionHandler implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if (introPanel instanceof IntroPanel) {
                ((IntroPanel) introPanel).getSidePanel().processCommand(e.getActionCommand());
            }
        }
    }

    // ActionListener for Map
    public class MapActionHandler implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            try {
                // Call processCommand directly on SidePanel
                if (introPanel instanceof IntroPanel) {
                    ((IntroPanel) introPanel).getMap().processCommand(e.getActionCommand());
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    // EFFECTS: Starts the game by creating a Game object
    public static void main(String[] args) {
        try {
            new Main();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    public static void disposeIntro() {
        introFrame.dispose();
    }
}
