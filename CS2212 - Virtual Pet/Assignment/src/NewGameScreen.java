import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.regex.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * NewGameScreen class. Lets users begin a new game.
 * 
 * <br>
 * <br>
 * Users can choose to begin a new game and are brought here. They then have the
 * option to choose from three different types of pet presented to them: easy,
 * medium, and hard. From here they can name their pet, and then begin playing.
 * <br>
 * <br>
 * 
 * @author Libby Cook
 * @author Teagan Rand Martins
 */


@SuppressWarnings("serial")
public class NewGameScreen extends Screen {
	
    /**
     * NewGameScreen constructor. Creates the display of the screen.
     * 
     * <br>
     * <br>
     * Users are presented with three different pet types:
     * easy, medium, and hard. They are displayed with a sprite
     * and with a short description. They each have a button to choose
     * them. Screen also allows for navigation back to main menu.
     * <br>
     * <br>
     * 
     * @param gameWindow
     * @param cardLayout
     * @param cardPanel
     */

	private String difficulty;
	private GamePlayScreen gamePlayScreen;
    private Sound music;
    private CardLayout cl; 
    private JPanel panelCont;
    public NewGameScreen(JFrame gameWindow, CardLayout cardLayout, JPanel cardPanel, Sound music) {
        
    	// Screen setup
    	super(gameWindow, cardLayout, cardPanel);
        
        this.panelCont = cardPanel;
        this.music = music;
        this.cl = cardLayout;

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
        
        // Creating the top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setOpaque(false);

        // Creating the navigation Panel
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new BorderLayout());
        JButton backButton = new JButton("Back to main menu");
        navigationPanel.add(backButton, BorderLayout.EAST);
        navigationPanel.setOpaque(false);

        // Creating the title Panel
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

        // Adding to title panel
        titlePanel.add(verticalPanel);
        titlePanel.add(Box.createHorizontalStrut(200));
        titlePanel.setOpaque(false);

        // Adding to top panel
        topPanel.add(navigationPanel, BorderLayout.NORTH);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        topPanel.setOpaque(false);

        // Creating content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        contentPanel.add(Box.createVerticalStrut(300));
        contentPanel.setOpaque(false);

        // Create pet panels for each different pet type
        JPanel petPanel1 = new JPanel();
        petPanel1.setLayout(new BoxLayout(petPanel1, BoxLayout.Y_AXIS));
        petPanel1.setOpaque(false);
        ImageIcon myPicture = new ImageIcon("src/Pet1/Normal.png");
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
        ImageIcon myPicture2 = new ImageIcon("src/Pet2/Normal.png");
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
        ImageIcon myPicture3 = new ImageIcon("src//Pet3/Normal.png");
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
                difficulty = new String("easy");
                chooseNameDialog("easy");
            }
        });

        petButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = new String("medium");
                chooseNameDialog("medium");
            }
        });

        petButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = new String("hard");
                chooseNameDialog("hard");
            }
        });

        /**
         * BACK
         * TO
         * MAIN
         * SCREEN
         */
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "1"));

    }

    /**
     * chooseNameDialog method. Creates the popup displayed to name a pet.
     * 
     * <br>
     * <br>
     * This method defines how we create the pop-up that actually
     * allows the user to name their pet. The name must only consist
     * of letters in the alphabet, no numbers, and must not be empty.
     * Once the user has chosen a name that's valid, they can submit
     * to start playing.
     * <br>
     * <br>
     * 
     * @param petType a string which indicates which type of pet they chose to name
     * @throws
     */

    private void chooseNameDialog(String petType) {
    	
        // Creates a new dialog/pop-up box
        JDialog petChosen = new JDialog(gameWindow, "Name your pet", true);
        petChosen.setSize(400, 150);
        petChosen.setLayout(new BorderLayout());

        JLabel label = new JLabel("Choose a name for your pet:");
        petChosen.add(label, BorderLayout.NORTH);

        JTextArea nameInputted = new JTextArea("Write name here...", 1, 10);

        petChosen.add(nameInputted);

        JButton enterName = new JButton("Start Game");
        petChosen.add(enterName, BorderLayout.SOUTH);

        // Creates action listeners for the button
        enterName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate the name chosen (text in input area)
                String name = nameInputted.getText();
                Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
                Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    // Name is allowed and we begin game
                    petChosen.dispose();
                    
                    // Create a file for the new pet to save all of its data
                    File file = new File("src/pet_saves/" + name+ ".csv");
                    
                    
                    try { 
                        // create FileWriter object with file as parameter 
                        FileWriter outputfile = new FileWriter(file); 
                  
                        // create CSVWriter object filewriter object as parameter 
                        CSVWriter writer = new CSVWriter(outputfile); 
                  
                        // adding header to newly created csv for the new pet
                        String[] header = {"Type","Name","Health", "Sleep", "Fullness","Happiness","Pie","Banana","Pizza","Ball","Plush Toy","Sword","Score"}; 
                        writer.writeNext(header); 
                        // add data to newly created csv for the new pet 
                        String[] data1 = {difficulty,name, "100","100","100","100","3","3","3","3","3","3","0"}; 
                        writer.writeNext(data1);  
                  
                        // closing writer connection 
                        writer.close();
                        
                        if (difficulty.equals("easy")) { // Giving the pet different stats depending on the difficulty chosen
                        	gamePlayScreen = new GamePlayScreen(new Pet("easy",100, 100, 100, 100, 100, 100, 100, 100, 1, 1, 1, 1, 10, 10, 10, 10,3,3,3,3,3,3,name,0),gameWindow,name,music,cl,panelCont);
                        }
                        else if (difficulty.equals("medium")) {
                        	gamePlayScreen = new GamePlayScreen(new Pet("medium",100, 100, 100, 100, 100, 100, 100, 100, 2, 2, 2, 2, 8, 8, 8, 8,3,3,3,3,3,3,name,0),gameWindow,name,music,cl,panelCont);
                        }
                        else {
                        	gamePlayScreen = new GamePlayScreen(new Pet("hard",100, 100, 100, 100, 100, 100, 100, 100, 3, 3, 3, 3, 6, 6, 6, 6,3,3,3,3,3,3,name,0),gameWindow,name,music,cl,panelCont);
                        }
                        
                        
                       // Adding the gameplayScreen to the cardpanel
                        cardPanel.add(gamePlayScreen.gamePanel,"6");
                        cardLayout.show(cardPanel, "6"); // Displaying the gameplay screen
                    } 
                    catch (IOException e1) { 
                        // TODO Auto-generated catch block 
                        e1.printStackTrace(); 
                    } 
                    
                    
                } else {
                    // Name not allowed
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
                    // Make our pop-up visible and set to right place
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