import java.io.*;
import javax.swing.*;
import java.awt.*;

// Main class
public class Launcher {

    // Main driver method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                // Setting up the game window
                JFrame gameWindow = new JFrame();
                gameWindow.setTitle("Game Window");
                gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameWindow.setSize(900, 700);
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setResizable(false);
                CardLayout cardLayout = new CardLayout();
                JPanel cardPanel = new JPanel(cardLayout);

                // Adding all the different types of screens
                Screen mainScreen = new MainScreen(gameWindow, cardLayout, cardPanel);
                cardPanel.add(mainScreen, "Main Screen");
                Screen newGameScreen = new NewGameScreen(gameWindow, cardLayout, cardPanel);
                cardPanel.add(newGameScreen, "New Game");
                Screen instructionScreen = new InstructionScreen(gameWindow, cardLayout, cardPanel);
                cardPanel.add(instructionScreen, "Instruction Screen");
                Screen settingsScreen = new SettingsScreen(gameWindow, cardLayout, cardPanel);
                cardPanel.add(settingsScreen, "Settings Screen");

                // Starting up the window
                gameWindow.add(cardPanel);
                gameWindow.setVisible(true);
            }
        });
    }

}
