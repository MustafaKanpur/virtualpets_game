import java.io.*;
import javax.swing.*;
import java.awt.*;

public class SettingsScreen extends Screen {

    public SettingsScreen(JFrame gameWindow, CardLayout cardLayout, JPanel cardPanel) {
        super(gameWindow, cardLayout, cardPanel);
        setLayout(new FlowLayout());
        setBackground(new java.awt.Color(161, 204, 247));
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        add(backButton);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main Screen"));
        JLabel title = new JLabel("Sound");
        title.setAlignmentX(CENTER_ALIGNMENT);
        add(title);
        JSlider slider = new JSlider();
        slider.setAlignmentX(CENTER_ALIGNMENT);
        add(slider);

    }

}
