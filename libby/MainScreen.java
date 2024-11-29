import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MainScreen extends Screen {
    public MainScreen(JFrame gameWindow, CardLayout cardLayout, JPanel cardPanel) {
        super(gameWindow, cardLayout, cardPanel);
        JButton settingsButton = new JButton("Settings");
        add(settingsButton);
        JButton newGameButton = new JButton("Start a new game");
        add(newGameButton);
        JButton instructionButton = new JButton("View Instructions");
        add(instructionButton);
        newGameButton.addActionListener(e -> cardLayout.show(cardPanel, "New Game"));
        instructionButton.addActionListener(e -> cardLayout.show(cardPanel, "Instruction Screen"));
        settingsButton.addActionListener(e -> cardLayout.show(cardPanel, "Settings Screen"));

    }

}
