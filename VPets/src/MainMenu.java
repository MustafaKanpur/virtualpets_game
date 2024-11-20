import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenu extends JFrame {
    private Font customFont;
    private JLabel animatedLabel;
    private JLabel titleLabel;
    private ImageIcon[] animationFrames;
    private int currentFrame = 0;

    public MainMenu() {
        // Load the custom font from the provided file
        loadCustomFont();

        // Set up the JFrame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("VPets - Main Menu");
        this.setLocationRelativeTo(null);

        // Set up the main panel with a BoxLayout for center alignment
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Load and add animated images
        animatedLabel = new JLabel();
        animationFrames = new ImageIcon[]{
                new ImageIcon("src/resources/Walk.png"),       // First frame
                new ImageIcon("src/resources/WalkFlip.png")    // Second frame
        };
        animatedLabel.setIcon(animationFrames[currentFrame]);
        animatedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(animatedLabel);

        // Start animation
        startAnimation();

        // Add the title label with the custom font
        titleLabel = new JLabel("VPets");
        if (customFont != null) {
            titleLabel.setFont(customFont.deriveFont(Font.BOLD, 175f)); // Larger Piacevoli font at 120pt size
        } else {
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 175)); // Fallback font
        }
        titleLabel.setForeground(Color.BLACK); // Set title color to black
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create buttons
        JButton loadGameButton = createButton("Load Game");
        JButton newGameButton = createButton("New Game");
        JButton instructionsButton = createButton("Instructions");
        JButton parentalControlsButton = createButton("Parental Controls");
        JButton exitButton = createButton("Exit");

        // Add an action to the exit button
        exitButton.addActionListener(e -> System.exit(0));

        // Add components to the main panel
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20)); // Spacer
        mainPanel.add(loadGameButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Spacer
        mainPanel.add(newGameButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Spacer
        mainPanel.add(instructionsButton);
        mainPanel.add(Box.createVerticalStrut(10)); // Spacer
        mainPanel.add(parentalControlsButton);
        mainPanel.add(Box.createVerticalStrut(20)); // Spacer
        mainPanel.add(exitButton);

        // Add footer text
        JLabel footerLabel = new JLabel("Created by Group 35: Alaan Libby, Musawer, Mustafa, Teagan in Fall 2024, CS2212 at Western University");
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create the main container with a border and layout
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createLineBorder(Color.BLACK, 7)); // 7px black border
        container.add(mainPanel, BorderLayout.CENTER);
        container.add(footerLabel, BorderLayout.SOUTH);

        // Use JLayeredPane with BorderLayout to keep components positioned correctly when resizing
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());

        // Add the main container to the center of the layered pane
        layeredPane.add(container, BorderLayout.CENTER);

        // Create and add the settings button to the top-left corner
        JButton settingsButton = createSettingsButton();
        JPanel settingsOverlay = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        settingsOverlay.setOpaque(false); // Transparent background for overlay
        settingsOverlay.add(settingsButton);
        layeredPane.add(settingsOverlay, BorderLayout.NORTH);

        this.setContentPane(layeredPane);
        this.setVisible(true);
    }

    private void loadCustomFont() {
        try {
            // Load font from src/resources/Piacevoli.ttf
            File fontFile = new File("src/resources/Piacevoli.ttf");
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont); // Register the font
        } catch (IOException | FontFormatException e) {
            System.out.println("Failed to load custom font. Using default font.");
            e.printStackTrace();
        }
    }

    private JButton createSettingsButton() {
        // Load the gear icon
        ImageIcon gearIcon = new ImageIcon("src/resources/gear.png");
        JButton settingsButton = new JButton(gearIcon);
        settingsButton.setPreferredSize(new Dimension(40, 40));
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);
        settingsButton.setFocusPainted(false);

        // Add action to open settings
        settingsButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Settings menu"));

        return settingsButton;
    }

    // Helper method to create buttons with consistent styling
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setFocusPainted(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    // Method to start the animation with a Timer
    private void startAnimation() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                currentFrame = (currentFrame + 1) % animationFrames.length;
                animatedLabel.setIcon(animationFrames[currentFrame]);
            }
        }, 0, 500); // Change frames every 500 milliseconds (0.5 seconds)
    }

    public static void main(String[] args) {
        new MainMenu(); // Launch the main menu screen
    }
}

