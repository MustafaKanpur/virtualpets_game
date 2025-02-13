// Import necessary Java AWT and Swing classes for UI components and layout management
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.CardLayout;

/**
 * Represents a passcode entry screen in the application.
 * This screen allows users to enter a passcode to access restricted parts of the program or return to the main menu.
 * @author Teagan Rand Martins
 */
@SuppressWarnings("serial")
public class PasscodeScreen extends JPanel {

    // UI components for the passcode entry screen
    private JTextField passcode; // Input field for passcode (hidden input)
    private JButton enter;       // Button to attempt to validate the passcode
    private JButton exit;        // Button to exit the current screen

    /**
     * Constructs a new PasscodeScreen.
     *
     * @param cl         the CardLayout for switching between panels
     * @param panelCont  the container JPanel holding the different screens
     */
    public PasscodeScreen(CardLayout cl, JPanel panelCont) {
        // Initialize components
        this.passcode = new JPasswordField(); // Passcode input field
        this.enter = new JButton("Enter");   // Button to submit passcode
        this.exit = new JButton("Exit");     // Button to return to the previous screen

        // Configure passcode input field properties
        this.passcode.setMaximumSize(new Dimension(250, 50)); // Set maximum size for the field
        this.passcode.setAlignmentX(Component.CENTER_ALIGNMENT); // Align it to the center

        // Configure "Enter" button properties
        enter.setAlignmentX(Component.CENTER_ALIGNMENT); // Align the button to the center
        enter.setBounds(150, 200, 100, 100); // Set button position and size

        /**
         * ActionListener for the "Enter" button.
         * Validates the entered passcode and switches to the appropriate screen if the passcode is correct.
         */
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Retrieve the text from the passcode field
                String passwordAttempt = PasscodeScreen.this.getPasscode().getText();
                // Check if the entered passcode matches the expected value
                if (passwordAttempt.equals("group35")) {
                    cl.show(panelCont, "3"); // Switch to the panel with identifier "3"
                } else {
                }
            }
        });

        /**
         * ActionListener for the "Exit" button.
         * Switches back to the main menu screen.
         */
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Switch back to the panel with identifier "1"
                cl.show(panelCont, "1");
            }
        });

        // Set up the layout of the PasscodeScreen
        this.setLayout(new GridLayout(0, 7)); // Use a grid layout with 7 columns

        // Populate the grid layout with components
        for (int i = 0; i < 100; i++) {
            if (i == 45) {
                this.add(passcode); // Add the passcode field at position 45
            } else if (i == 52) {
                this.add(enter); // Add the "Enter" button at position 52
            } else if (i == 0) {
                this.add(exit); // Add the "Exit" button at position 0
            } else {
                this.add(new JLabel(" ")); // Add empty labels to fill the grid
            }
        }

        // Set the background color of the panel
        this.setBackground(Color.white);
    }

    /**
     * Getter for the passcode field.
     *
     * @return the JTextField object representing the passcode input field
     */
    public JTextField getPasscode() {
        return this.passcode;
    }
}
