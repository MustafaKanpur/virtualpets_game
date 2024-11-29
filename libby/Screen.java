import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    protected CardLayout cardLayout;
    protected JPanel cardPanel;
    protected JFrame gameWindow;
    private Image backgroundImage;

    public Screen(JFrame gameWindow, CardLayout cardLayout, JPanel cardPanel) {
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.gameWindow = gameWindow;
        try {
            this.backgroundImage = ImageIO.read(new File("../VPets/src/resources/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            this.backgroundImage = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

// References:
// https://stackoverflow.com/questions/19125707/simplest-way-to-set-image-as-jpanel-background