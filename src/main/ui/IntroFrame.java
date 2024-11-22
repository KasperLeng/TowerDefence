package ui;

import javax.swing.*;
import java.awt.*;

/*
 * Creates frame to contain intro panel, allows played to load game.
 */
public class IntroFrame extends JFrame {

    public IntroFrame(GamePanel gamePanel) {
        super("game");
        setTitle("Village Raiders");
        setSize(920, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(gamePanel);
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
