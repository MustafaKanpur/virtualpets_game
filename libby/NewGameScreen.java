import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.concurrent.Flow;

public class NewGameScreen extends Screen {
    public NewGameScreen(JFrame gameWindow, CardLayout cardLayout, JPanel cardPanel) {
        super(gameWindow, cardLayout, cardPanel);

        /**
         * SCREEN
         * SETUP
         */
        setLayout(new BorderLayout());

        /**
         * CONTENTS
         * OF
         * OUR
         * SCREEN
         */

        /**
         * TOP
         * PANEL
         */

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);

        // Navigation Panel
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new BorderLayout());
        JButton backButton = new JButton("Back to main menu");
        navigationPanel.add(backButton, BorderLayout.EAST);
        navigationPanel.setOpaque(false);

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("New Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        JLabel titleDescription = new JLabel("Choose a type of pet");
        titleDescription.setFont(new Font("Arial", Font.PLAIN, 20));
        verticalPanel.add(titleLabel);
        verticalPanel.add(Box.createVerticalStrut(20));
        verticalPanel.add(titleDescription);
        verticalPanel.setOpaque(false);

        titlePanel.add(verticalPanel);
        titlePanel.add(Box.createHorizontalStrut(200));
        titlePanel.setOpaque(false);

        topPanel.add(navigationPanel, BorderLayout.NORTH);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        topPanel.setOpaque(false);

        /**
         * CONTENT
         * PANEL
         */

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        contentPanel.add(Box.createVerticalStrut(300));
        contentPanel.setOpaque(false);

        // Each separate panel

        JPanel petPanel1 = new JPanel();
        petPanel1.setLayout(new BoxLayout(petPanel1, BoxLayout.Y_AXIS));
        petPanel1.setOpaque(false);
        ImageIcon myPicture = new ImageIcon("Normal-3.png");
        Image resizedImage = myPicture.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel image1 = new JLabel(resizedIcon);
        image1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel description1 = new JLabel(
                "<html><div style='width:200px;'>'Easy': This pet has the slowest decrease in sleep, happiness, and fullness. This will make for the easiest gameplay. </div></html>");
        description1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton petButton1 = new JButton("Choose Easy Pet");
        petButton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        petPanel1.add(description1);
        petPanel1.add(image1);
        petPanel1.add(petButton1);

        JPanel petPanel2 = new JPanel();
        petPanel2.setLayout(new BoxLayout(petPanel2, BoxLayout.Y_AXIS));
        ImageIcon myPicture2 = new ImageIcon("Normal-2.png");
        Image resizedImage2 = myPicture2.getImage().getScaledInstance(120, 130, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        JLabel image2 = new JLabel(resizedIcon2);
        image1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel description2 = new JLabel(
                "<html><div style='width:200px;'>'Medium': This pet has the normal decrease in sleep, happiness, and fullness. This will make for medium difficult gameplay.</div><html>");
        description2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton petButton2 = new JButton("Choose Medium Pet");
        petButton2.setAlignmentX(Component.CENTER_ALIGNMENT);
        petPanel2.add(description2);

        petPanel2.add(image2);
        petPanel2.add(petButton2);
        petPanel2.setOpaque(false);
        JPanel petPanel3 = new JPanel();
        petPanel3.setLayout(new BoxLayout(petPanel3, BoxLayout.Y_AXIS));
        ImageIcon myPicture3 = new ImageIcon("Normal.png");
        Image resizedImage3 = myPicture3.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        JLabel image3 = new JLabel(resizedIcon3);
        image1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel description3 = new JLabel(
                "<html><div style='width:200px;'>'Hard': This pet's fullness, happiness, and sleep decrease the fastest. This will make for a very challenging game!</div></html>");
        description3.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton petButton3 = new JButton("Choose Hard Pet");
        petButton3.setAlignmentX(Component.CENTER_ALIGNMENT);
        petPanel3.add(description3);
        petPanel3.add(image3);
        petPanel3.add(petButton3);
        petPanel3.setOpaque(false);

        // Adding pets to the content panel
        contentPanel.add(petPanel1, BorderLayout.WEST);
        contentPanel.add(petPanel2, BorderLayout.CENTER);
        contentPanel.add(petPanel3, BorderLayout.EAST);

        /**
         * ADDING
         * MAIN
         * SCREEN
         * COMPONENTS
         * Including: titlePanel and Content Panel
         */
        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        /**
         * NAVIGATION
         * ACTION
         * LISTENERS
         */

        petButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseNameDialog("easy");
            }
        });

        petButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseNameDialog("medium");
            }
        });

        petButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseNameDialog("hard");
            }
        });

        /**
         * BACK
         * TO
         * MAIN
         * SCREEN
         */
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main Screen"));

    }

    // Method to show a popup to choose a name for your pet

    private void chooseNameDialog(String petType) {
        JDialog petChosen = new JDialog(gameWindow, "Name your pet", true);
        petChosen.setSize(400, 150);
        petChosen.setLayout(new BorderLayout());

        JLabel label = new JLabel("Choose a name for your pet:");
        petChosen.add(label, BorderLayout.NORTH);

        JTextArea nameInputted = new JTextArea("Write name here...", 1, 10);

        petChosen.add(nameInputted);

        JButton enterName = new JButton("Start Game");
        petChosen.add(enterName, BorderLayout.SOUTH);

        enterName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // validate input of text area for name
                String name = nameInputted.getText();
                Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
                Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    // name allowed
                    // begin game
                    petChosen.dispose();
                } else {
                    // name not alloweed
                    nameInputted.setText("");
                    JDialog nameNotAllowed = new JDialog(gameWindow, "Invalid name", true);
                    nameNotAllowed.setSize(400, 150);
                    nameNotAllowed.setLayout(new BorderLayout());
                    nameNotAllowed.setLocationRelativeTo(null);

                    JLabel labelName = new JLabel(
                            "<html><font color='red'><p><b>Name not allowed.</b></br>Names must only include characters (not numbers or punctuation), and must not be empty</p></font></html>");
                    nameNotAllowed.add(labelName, BorderLayout.CENTER);
                    JButton acknowledgedButton = new JButton("Okay");
                    acknowledgedButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            nameNotAllowed.dispose();
                        }
                    });

                    nameNotAllowed.add(acknowledgedButton, BorderLayout.SOUTH);
                    nameNotAllowed.setVisible(true);
                }
            }
        });
        petChosen.setLocationRelativeTo(gameWindow);
        petChosen.setVisible(true);
    }
}

// References:
// https://stackoverflow.com/questions/5895829/resizing-image-in-java