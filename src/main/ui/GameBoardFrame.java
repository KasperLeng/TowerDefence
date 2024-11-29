package ui;

import javax.swing.*;

import model.EventLog;
import model.GameBoard;
import model.Position;
import model.exceptions.LogException;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

/*
 * GameBoard Frame that includes both game map and game panel
 */
public class GameBoardFrame extends JFrame {
    private int height = GameBoard.ymax * Position.pixelSize;
    private int width = GameBoard.xmax * Position.pixelSize;

    // MODIFIES: this
    // EFFECTS: initializes a Game Object and creates the gameBoard frame
    public GameBoardFrame(SidePanel sidePanel, Map map) {
        super("game");
        setTitle("Village Raiders");
        add(sidePanel);
        add(map);
        setSize(width + 230, height + 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        centreOnScreen();
        setResizable(false);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    LogPrinter.printLog(EventLog.getInstance());
                    System.out.println("Game Closed");
                } catch (LogException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }
}
