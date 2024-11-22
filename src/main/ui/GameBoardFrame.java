package ui;

import javax.swing.*;

import model.GameBoard;
import model.Position;

import java.awt.*;

/*
 * GameBoard Frame that includes both game map and game panel
 */
public class GameBoardFrame extends JFrame {
    private int height = GameBoard.ymax * Position.pixelSize;
    private int width = GameBoard.xmax * Position.pixelSize;

    public GameBoardFrame(SidePanel sidePanel, Map map) {
        super("game");
        setTitle("Village Raiders");
        add(sidePanel);
        add(map);
        setSize(width + 195, height + 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        centreOnScreen();
        setResizable(false);
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }
}
