import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * Screen class defines a screen
 * @author Libby Cook
 */
@SuppressWarnings("serial")
public class Screen extends JPanel {

    protected CardLayout cardLayout;
    protected JPanel cardPanel;
    protected JFrame gameWindow;
    private Image backgroundImage;

    /**
     * Constructor for screen.
     * 
     * <br>
     * <br>
     * Creates the screen. Changes the background image to that
     * specified by the path. Gives each screen a reference to the window,
     * its cardLayout and its cardPanel.
     * <br>
     * <br>
     * 
     * @param gameWindow
     * @param cardLayout
     * @param cardPanel
     */
    public Screen(JFrame gameWindow, CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.gameWindow = gameWindow;
        try {
            this.backgroundImage = ImageIO.read(new File("src/resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }
    }

    /**
     * paintComponent override. Allows us to put an image on the background
     * 
     * @param g the graphics to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

// References:
// https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background
