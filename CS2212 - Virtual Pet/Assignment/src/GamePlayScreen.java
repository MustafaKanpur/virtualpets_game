import javax.imageio.ImageIO;
import javax.swing.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.awt.event.ActionEvent;


/**
 * This class represents the gameplay screen. It initializes all the necessary GUI functionality
 * as well as gameplay (buttons increase stats, stats tick down, etc..)
 * @author Mustafa Kanpurwala
 * @author Teagan Rand Martins
 */

/**
 * The GamePlayScreen class represents the gameplay screen for the VPets game.
 * It handles the user interface, animations, timers for various stats decay,
 * and interactions between the player and their pet.
 */
public class GamePlayScreen {
    // Timers for stat decay (hunger, sleep, and regular)
    private Timer regularDecayTimer;
    private Timer hungerDecayTimer;
    private Timer sleepDecayTimer;

    // Panels for organizing the UI
    public JPanel gamePanel;
    private JPanel buttonPanel;
    private JPanel statPanel;
    private JPanel topPanel;

    // Labels to display game statistics
    private JLabel score;
    private JLabel health;
    private JLabel sleepLevel;
    private JLabel fullness;
    private JLabel happiness;
    private JLabel petSprite;

    // Button for exiting the screen
    private JButton exit;

    // Layout manager for switching between panels
    private CardLayout cl;

    // Score value for the gameplay session
    public int scoreVal = 0;

    // Current pet being interacted with
    private Pet currentPet;
    private PetController petControl;

    // Buttons for interacting with the pet
    private JButton feed;
    private JButton play;
    private JButton exercise;
    private JButton sleep;
    private JButton vet;
    private JButton gift;
    private JButton inventory;

    // Frame containing the gameplay screen
    private JFrame frame;

    // Background image for the screen
    private Image backgroundImage;

    // Arrays of images for pet animations and states
    private Image[] petImages;
    private Timer animationTimer; // Timer for cycling animations
    private int currentAnimationIndex; // Index to track the current animation frame
    private Image[] sleepImages;
    private Image[] hungryImages;
    private Image[] angryImages;
    private Image[] deadImages;

    // Sound for sleeping animation or other actions
    private Sound sleepSound;

    // Panel for containing multiple views
    private JPanel panelCont;

    // Button for settings
    private JButton settings;

    /**
     * Constructs a GamePlayScreen instance.
     * Initializes the UI, loads resources (e.g., images, sounds), and sets up animations and timers.
     *
     * @param pet         the current pet object
     * @param frame       the main game frame
     * @param name        the name of the pet
     * @param music       background music for the gameplay
     * @param cl          CardLayout for switching between screens
     * @param panelCont   container for managing multiple panels
     */
    public GamePlayScreen(Pet pet, JFrame frame, String name, Sound music, CardLayout cl, JPanel panelCont) {
        // Initialize exit button and layout manager
        this.exit = new JButton("Exit");
        this.cl = cl;
        this.panelCont = panelCont;

        // Stop the background music
        music.stopSound();

        // Load pet images and animations based on difficulty level
        try {
            if (pet.getDifficulty().equals("hard")) {
                petImages = new Image[]{
                    ImageIO.read(new File("src/Pet3/Normal.png")),
                    ImageIO.read(new File("src/Pet3/NormalFlip.png"))
                };
                sleepImages = new Image[]{
                    ImageIO.read(new File("src/Pet3/Sleep.png")),
                    ImageIO.read(new File("src/Pet3/Sleepflip.png"))
                };
                hungryImages = new Image[]{
                    ImageIO.read(new File("src/Pet3/Hungry.png")),
                    ImageIO.read(new File("src/Pet3/HungryFlip.png"))
                };
                angryImages = new Image[]{
                    ImageIO.read(new File("src/Pet3/Anger.png")),
                    ImageIO.read(new File("src/Pet3/AngerFlip.png"))
                };
                deadImages = new Image[]{
                    ImageIO.read(new File("src/Pet3/Dead.png"))
                };
            } else if (pet.getDifficulty().equals("medium")) {
                petImages = new Image[]{
                    ImageIO.read(new File("src/Pet2/Normal.png")),
                    ImageIO.read(new File("src/Pet2/NormalFlip.png"))
                };
                sleepImages = new Image[]{
                    ImageIO.read(new File("src/Pet2/Sleep.png")),
                    ImageIO.read(new File("src/Pet2/Sleepflip.png"))
                };
                hungryImages = new Image[]{
                    ImageIO.read(new File("src/Pet2/Hungry.png")),
                    ImageIO.read(new File("src/Pet2/HungryFlip.png"))
                };
                angryImages = new Image[]{
                    ImageIO.read(new File("src/Pet2/Anger.png")),
                    ImageIO.read(new File("src/Pet2/AngerFlip.png"))
                };
                deadImages = new Image[]{
                    ImageIO.read(new File("src/Pet2/Dead.png"))
                };
            } else {
                petImages = new Image[]{
                    ImageIO.read(new File("src/Pet1/Normal.png")),
                    ImageIO.read(new File("src/Pet1/NormalFlip.png"))
                };
                sleepImages = new Image[]{
                    ImageIO.read(new File("src/Pet1/Sleep.png")),
                    ImageIO.read(new File("src/Pet1/Sleepflip.png"))
                };
                hungryImages = new Image[]{
                    ImageIO.read(new File("src/Pet1/Hungry.png")),
                    ImageIO.read(new File("src/Pet1/HungryFlip.png"))
                };
                angryImages = new Image[]{
                    ImageIO.read(new File("src/Pet1/Anger.png")),
                    ImageIO.read(new File("src/Pet1/AngerFlip.png"))
                };
                deadImages = new Image[]{
                    ImageIO.read(new File("src/Pet1/Dead.png"))
                };
            }
        } catch (IOException e) {
            // Handle missing images gracefully
            e.printStackTrace();
            petImages = null;
        }

        // Set up the animation timer to switch pet images periodically
        animationTimer = new Timer(1000, new ActionListener() {
            /**
             * Action performed every second by the animation timer.
             * Updates the current animation frame and triggers a repaint.
             *
             * @param e the action event triggered by the timer
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Alternate the animation index
                currentAnimationIndex = (currentAnimationIndex + 1) % petImages.length;
                gamePanel.repaint(); // Repaint to update the displayed sprite
            }
        });
        animationTimer.start(); // Start the animation timer



    	
        try {
            // Load the background image for the gameplay screen
            this.backgroundImage = ImageIO.read(new File("src/resources/bedroom.png"));
        } catch (IOException e) {
            // Handle the exception and set the background image to null if loading fails
            e.printStackTrace();
            this.backgroundImage = null;
        }

        // Assign the provided pet and frame to class variables
        this.currentPet = pet;
        this.frame = frame; // Initialize the frame variable

        // Initialize the pet controller to manage pet stats and actions
        petControl = new PetController(pet);

        // Add a WindowListener to handle actions when the window is closing
        WindowListener listener = new WindowAdapter() {
            /**
             * Invoked when the user attempts to close the window.
             * Saves the current pet's state to a CSV file.
             *
             * @param e the window event
             */
            public void windowClosing(WindowEvent e) {
                FileWriter filewriter = null;
                try {
                    // Create a FileWriter to save the pet's state in append mode
                    filewriter = new FileWriter("src/pet_saves/" + name + ".csv", true);
                } catch (IOException e1) {
                    // Handle exceptions during FileWriter initialization
                    e1.printStackTrace();
                }

                // Use CSVWriter to write the pet's state to a CSV file
                CSVWriter csvwriter = new CSVWriter(filewriter);
                String[] row = {
                    pet.getDifficulty(),
                    name,
                    pet.getHealth() + "",
                    pet.getSleep() + "",
                    pet.getFullness() + "",
                    pet.getHappiness() + "",
                    pet.getPieCount() + "",
                    pet.getBananaCount() + "",
                    pet.getPizzaCount() + "",
                    pet.getBallCount() + "",
                    pet.getPlushCount() + "",
                    pet.getSwordCount() + "",
                    pet.getScore() + ""
                };
                csvwriter.writeNext(row); // Save the pet's stats to the CSV file

                try {
                    // Close the CSVWriter to release resources
                    csvwriter.close();
                } catch (IOException e1) {
                    // Handle exceptions during CSVWriter closing
                    e1.printStackTrace();
                }
            }
        };

        // Attach the WindowListener to the frame to capture window closing events
        this.frame.addWindowListener(listener);

        // Initialize other components and UI elements
        initialize();
    	}

        /**
         * Decreases the pet's health by a predefined amount, ensuring it doesn't drop below 0.
         */
        public void decreaseHealth() {
            currentPet.setHealth(Math.max(0, currentPet.getHealth() - currentPet.getHealthLoss()));
        }

        /**
         * Decreases the pet's sleep level by a predefined amount, ensuring it doesn't drop below 0.
         */
        public void decreaseSleep() {
            currentPet.setSleep(Math.max(0, currentPet.getSleep() - currentPet.getSleepLoss()));
        }

        /**
         * Decreases the pet's fullness level by a predefined amount, ensuring it doesn't drop below 0.
         */
        public void decreaseFullness() {
            currentPet.setFullness(Math.max(0, currentPet.getFullness() - currentPet.getFullnessLoss()));
        }

        /**
         * Decreases the pet's happiness level by a predefined amount, ensuring it doesn't drop below 0.
         */
        public void decreaseHappiness() {
            currentPet.setHappiness(Math.max(0, currentPet.getHappiness() - currentPet.getHappinessLoss()));
        }

        /**
         * Increases the pet's score by a fixed amount (15 points).
         */
        private void increaseScore() {
            currentPet.increaseScore(15);
        }


    

        @SuppressWarnings("serial")
        /**
         * Initializes timers and game logic to manage the decay of pet stats and update the UI periodically.
         */
        public void initialize() {

            // Timer to decrease general pet stats at regular intervals (every 5 seconds)
            regularDecayTimer = new Timer(5000, new ActionListener() { // 5000 ms = 5 seconds
                /**
                 * Action performed by the regular decay timer.
                 * Checks the pet's status and applies general stat decay.
                 *
                 * @param e the action event triggered by the timer
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check pet's status for various conditions
                    petControl.checkAngry(); // Check if the pet is angry
                    petControl.checkDead();  // Check if the pet is dead
                    petControl.checkHungry(); // Check if the pet is hungry
                    petControl.checkSleep(); // Check if the pet needs sleep

                    // If the pet is sleeping, stop regular decay and start the sleep-specific decay
                    if (currentPet.isSleeping()) {
                        regularDecayTimer.stop();
                        currentPet.setHealth(currentPet.getHealth() - currentPet.getHealthLoss()); // Reduce health
                        sleepDecayTimer.start(); // Start sleep decay
                    }

                    // If the pet is hungry, stop regular decay and start the hunger-specific decay
                    if (currentPet.isHungry()) {
                        regularDecayTimer.stop();
                        hungerDecayTimer.start(); // Start hunger decay
                    }

                    // Apply regular stat decay
                    decreaseFullness(); // Decrease fullness stat
                    decreaseSleep();    // Decrease sleep stat
                    decreaseHappiness(); // Decrease happiness stat
                    increaseScore();    // Increase the player's score
                    updateStats();      // Update the stats displayed on the UI
                }
            });

            // Timer to manage hunger-specific decay (every 5 seconds)
            hungerDecayTimer = new Timer(5000, new ActionListener() {
                /**
                 * Action performed by the hunger decay timer.
                 * Decreases pet stats more aggressively when the pet is hungry.
                 *
                 * @param e the action event triggered by the timer
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check pet's status for various conditions
                    petControl.checkAngry(); // Check if the pet is angry
                    petControl.checkDead();  // Check if the pet is dead
                    petControl.checkHungry(); // Check if the pet is hungry
                    petControl.checkSleep(); // Check if the pet needs sleep

                    // Apply more aggressive happiness decay when hungry
                    currentPet.setHappiness(Math.max(0, currentPet.getHappiness() - (currentPet.getHappinessLoss() + 5)));

                    // Apply additional stat decay
                    decreaseSleep(); // Decrease sleep stat
                    decreaseHealth(); // Decrease health stat
                    updateStats();   // Update the stats displayed on the UI

                    // If the pet is no longer hungry, stop hunger decay and resume regular decay
                    if (!currentPet.isHungry()) {
                        hungerDecayTimer.stop();
                        regularDecayTimer.start();
                    }
                }
            });
        


        
         // Timer for managing sleep-specific stat gains and decay
            sleepDecayTimer = new Timer(1000, new ActionListener() { // 1000 ms = 1 second
                /**
                 * Action performed by the sleep decay timer.
                 * Increases the pet's sleep level and manages transitions out of sleep mode.
                 *
                 * @param e the action event triggered by the timer
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check the pet's current status
                    petControl.checkAngry();
                    petControl.checkDead();
                    petControl.checkHungry();
                    petControl.checkSleep();

                    // Set the pet to sleeping mode
                    currentPet.setSleeping(true);

                    // Increase the pet's sleep level while ensuring it doesn't exceed the maximum
                    currentPet.setSleep(Math.max(0, currentPet.getSleep() + currentPet.getSleepGain()));

                    // Apply additional stat decay during sleep
                    decreaseHappiness();
                    decreaseFullness();
                    updateStats(); // Update the displayed stats

                    // If the pet reaches the maximum sleep level, stop sleeping and resume regular decay
                    if (currentPet.getSleep() == currentPet.getMaxSleep()) {
                        currentPet.setSleeping(false); // Pet is no longer sleeping
                        sleepDecayTimer.stop(); // Stop the sleep decay timer

                        if (sleepSound != null) {
                            sleepSound.stopSound(); // Stop the sleep sound if playing
                        }

                        regularDecayTimer.start(); // Resume regular decay
                    }
                }
            });

            // Start the regular decay timer to initiate the gameplay loop
            regularDecayTimer.start();

            // Initialize the game panel for rendering the game UI and pet animations
            gamePanel = new JPanel() {
                /**
                 * Paints the components of the game panel, including the background and pet sprite.
                 *
                 * @param g the Graphics object used for drawing
                 */
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);

                    // Draw the background image
                    if (backgroundImage != null) {
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    }

                    // Draw the pet sprite based on the current state and animation frame
                    if (petImages != null && petImages.length > 0 && !currentPet.isSleeping() && 
                        !currentPet.isAngry() && !currentPet.isDead() && !currentPet.isHungry()) {
                        Image petImage = petImages[currentAnimationIndex];
                        int spriteWidth = 100; // Set sprite dimensions
                        int spriteHeight = 100;
                        int spriteX = getWidth() / 2 - spriteWidth / 2; // Center horizontally
                        int spriteY = getHeight() / 2 - spriteHeight / 2; // Center vertically
                        g.drawImage(petImage, spriteX, spriteY, spriteWidth, spriteHeight, this);
                    } else if (deadImages != null && deadImages.length > 0 && currentPet.isDead()) {
                        // Draw dead pet sprite
                        Image petImage = deadImages[0];
                        int spriteWidth = 100;
                        int spriteHeight = 100;
                        int spriteX = getWidth() / 2 - spriteWidth / 2;
                        int spriteY = getHeight() / 2 - spriteHeight / 2;
                        g.drawImage(petImage, spriteX, spriteY, spriteWidth, spriteHeight, this);
                    } else if (sleepImages != null && sleepImages.length > 0 && currentPet.isSleeping()) {
                        // Draw sleeping pet sprite
                        Image petImage = sleepImages[currentAnimationIndex];
                        int spriteWidth = 100;
                        int spriteHeight = 100;
                        int spriteX = getWidth() / 2 - spriteWidth / 2;
                        int spriteY = getHeight() / 2 - spriteHeight / 2;
                        g.drawImage(petImage, spriteX, spriteY, spriteWidth, spriteHeight, this);
                    } else if (hungryImages != null && hungryImages.length > 0 && currentPet.isHungry()) {
                        // Draw hungry pet sprite
                        Image petImage = hungryImages[currentAnimationIndex];
                        int spriteWidth = 100;
                        int spriteHeight = 100;
                        int spriteX = getWidth() / 2 - spriteWidth / 2;
                        int spriteY = getHeight() / 2 - spriteHeight / 2;
                        g.drawImage(petImage, spriteX, spriteY, spriteWidth, spriteHeight, this);
                    } else if (angryImages != null && angryImages.length > 0 && currentPet.isAngry()) {
                        // Draw angry pet sprite
                        Image petImage = angryImages[currentAnimationIndex];
                        int spriteWidth = 100;
                        int spriteHeight = 100;
                        int spriteX = getWidth() / 2 - spriteWidth / 2;
                        int spriteY = getHeight() / 2 - spriteHeight / 2;
                        g.drawImage(petImage, spriteX, spriteY, spriteWidth, spriteHeight, this);
                    }
                }
            };

        
        

         gamePanel.setLayout(new BorderLayout(5, 5));
         
         
         buttonPanel = new JPanel();
         statPanel = new JPanel();
         statPanel.setLayout(new BoxLayout(statPanel, BoxLayout.Y_AXIS));
         statPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

         topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Initializing buttons
        feed = new JButton("FEED");
        play = new JButton("PLAY");
        exercise = new JButton("EXERCISE");
        sleep = new JButton("SLEEP");
        vet = new JButton("VET");
        gift = new JButton("GIFT");
        inventory = new JButton("INVENTORY");
        settings = new JButton("SETTINGS");

        // Initializing JLabels
        score = new JLabel("Score: " + currentPet.getScore());
        health = new JLabel ("Health: " + currentPet.getHealth());
        sleepLevel = new JLabel("Sleep: " + currentPet.getSleep());
        fullness = new JLabel("Fullness: " + currentPet.getFullness());
        happiness = new JLabel("Happiness: " + currentPet.getHappiness());

        // Filepath should be variable
        ImageIcon normalState = new ImageIcon("Pet 3 States/Normal.png");
        petSprite = new JLabel(normalState);

        exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cl.show(panelCont,"1");
        	}
    });
        
        // Adding panels together
        frame.add(gamePanel);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        gamePanel.add(statPanel, BorderLayout.WEST);
        gamePanel.add(topPanel, BorderLayout.EAST);
        gamePanel.add(petSprite, BorderLayout.CENTER);

        // Adding functional buttons
        buttonPanel.add(feed);
        buttonPanel.add(play);
        buttonPanel.add(exercise);
        buttonPanel.add(sleep);
        buttonPanel.add(gift);
        buttonPanel.add(vet);
        buttonPanel.add(inventory);

        // Adding score and stats
        score.setAlignmentX(Component.LEFT_ALIGNMENT);
        statPanel.add(score);

        health.setAlignmentX(Component.LEFT_ALIGNMENT);
        statPanel.add(health);

        fullness.setAlignmentX(Component.LEFT_ALIGNMENT);
        statPanel.add(fullness);

        sleepLevel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statPanel.add(sleepLevel);

        happiness.setAlignmentX(Component.LEFT_ALIGNMENT);
        statPanel.add(happiness);

        
        // Adding settings button
        this.setUpButtonListeners();
        topPanel.add(settings);
        topPanel.add(exit);
        
        /**
         * Adds a KeyListener to the game panel to handle specific key presses.
         * Simulates button clicks for various pet actions based on the pressed key.
         */
        gamePanel.addKeyListener(new KeyAdapter() {
            /**
             * Handles key press events and triggers the corresponding pet action.
             *
             * @param e the KeyEvent triggered by a key press
             */
            @Override
            public void keyPressed(KeyEvent e) {
                // Switch statement to map specific keys to actions
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_P: // Play (P key)
                        play.doClick(); // Simulate the "Play" button click
                        break;
                    case KeyEvent.VK_E: // Exercise (E key)
                        exercise.doClick(); // Simulate the "Exercise" button click
                        break;
                    case KeyEvent.VK_V: // Take to Vet (V key)
                        vet.doClick(); // Simulate the "Vet" button click
                        break;
                    case KeyEvent.VK_S: // Sleep (S key)
                        sleep.doClick(); // Simulate the "Sleep" button click
                        break;
                    default:
                        // Log invalid key presses for debugging purposes
                }
            }
        });

        // Ensure the game panel is focusable and can receive keyboard input
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();



        
    }

        /**
         * Sets up button listeners for various pet actions, including feeding the pet.
         * Handles interactions and updates based on the pet's state and available resources.
         */
        public void setUpButtonListeners() {
            // Listener for the "Feed" button
            ActionListener feedListener = new ActionListener() {
                /**
                 * Handles the "Feed" button action to allow feeding the pet.
                 * Checks the pet's state (e.g., sleeping or angry) and available food options.
                 *
                 * @param ae the ActionEvent triggered by the button
                 */
                @Override
                public void actionPerformed(ActionEvent ae) {
                    // Check if the pet is sleeping
                    if (currentPet.isSleeping()) {
                        JOptionPane.showMessageDialog(frame, "Your pet is sleeping right now!");
                    } 
                    // Check if the pet is too angry
                    else if (currentPet.isAngry()) {
                        JOptionPane.showMessageDialog(frame, "Your pet is too angry for that!");
                    } 
                    // Handle feeding the pet
                    else {
                        FileWriter filewriter = null;
                        FileReader filereader = null;
                        try {
                            // Open the save file to read the current pet data
                            filereader = new FileReader("src/pet_saves/" + currentPet.getName() + ".csv");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try (CSVReader csvreader = new CSVReader(filereader)) {
                            List<String[]> rows = null;
                            try {
                                // Read all rows from the CSV file
                                rows = csvreader.readAll();
                            } catch (IOException | CsvException e) {
                                e.printStackTrace();
                            }

                            try {
                                filereader.close(); // Close the file reader
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                // Open the save file in append mode to update data
                                filewriter = new FileWriter("src/pet_saves/" + currentPet.getName() + ".csv", true);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try (CSVWriter csvwriter = new CSVWriter(filewriter)) {
                                // Get the last row for current food counts
                                String[] finalRow = rows.get(rows.size() - 1);
                                Object[] options = {
                                    "Pie x" + finalRow[6], 
                                    "Banana x" + finalRow[7], 
                                    "Pizza x" + finalRow[8]
                                };

                                // Prompt the user to choose a food option
                                int option = JOptionPane.showOptionDialog(frame, 
                                    "What would you like to feed your pet?", 
                                    "Food Options", 
                                    JOptionPane.YES_NO_CANCEL_OPTION, 
                                    JOptionPane.QUESTION_MESSAGE, 
                                    null, 
                                    options, 
                                    options[2]);

                                // Handle feeding based on the selected food option
                                if (option == 0 && Integer.parseInt(finalRow[6]) > 0) {
                                    petControl.feedPet();
                                    petControl.feedPet();
                                    currentPet.decrementPieCount();
                                    csvwriter.writeNext(new String[]{
                                        finalRow[0], finalRow[1], finalRow[2], finalRow[3], 
                                        finalRow[4], finalRow[5], 
                                        Integer.parseInt(finalRow[6]) - 1 + "", 
                                        finalRow[7], finalRow[8], 
                                        finalRow[9], finalRow[10], finalRow[11], 
                                        currentPet.getScore() + ""
                                    });
                                    Sound eatSound = new Sound("src/resources/eating.wav");
                                    eatSound.playSound();
                                } else if (option == 1 && Integer.parseInt(finalRow[7]) > 0) {
                                    petControl.feedPet();
                                    currentPet.decrementBananaCount();
                                    csvwriter.writeNext(new String[]{
                                        finalRow[0], finalRow[1], finalRow[2], finalRow[3], 
                                        finalRow[4], finalRow[5], 
                                        finalRow[6], Integer.parseInt(finalRow[7]) - 1 + "", 
                                        finalRow[8], finalRow[9], finalRow[10], finalRow[11], 
                                        currentPet.getScore() + ""
                                    });
                                    Sound eatSound = new Sound("src/resources/eating.wav");
                                    eatSound.playSound();
                                } else if (option == 2 && Integer.parseInt(finalRow[8]) > 0) {
                                    petControl.feedPet();
                                    petControl.feedPet();
                                    petControl.feedPet();
                                    currentPet.decrementPizzaCount();
                                    csvwriter.writeNext(new String[]{
                                        finalRow[0], finalRow[1], finalRow[2], finalRow[3], 
                                        finalRow[4], finalRow[5], 
                                        finalRow[6], finalRow[7], 
                                        Integer.parseInt(finalRow[8]) - 1 + "", 
                                        finalRow[9], finalRow[10], finalRow[11], 
                                        currentPet.getScore() + ""
                                    });
                                    Sound eatSound = new Sound("src/resources/eating.wav");
                                    eatSound.playSound();
                                } else {
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            if (filewriter != null) filewriter.close(); // Close the file writer
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Update the pet stats and UI
                        updateStats();
                        regainFocus(gamePanel); // Regain focus for keyboard interactions
                        coolDown(feed, 1000); // Apply cooldown to the "Feed" button
                    }
                }
            };


        ActionListener playListener = new ActionListener(){
            @Override
            /**
             * Method that determines the action for playing, checks if the pet is in an unresponsive state, if not adjusts stats, plays the animation
             * @param ae
             */

            public void actionPerformed(ActionEvent ae){
                // Informs the player if they are trying to do an action that is unavailable due to their pet's state
                if(currentPet.isSleeping()){
                    JOptionPane.showMessageDialog(frame, "Your pet is sleeping right now!");
                }
                else{
                petControl.playWithPet();
                updateStats();
                regainFocus(gamePanel);
                coolDown(play, 1000);
                }
                
            }
        };

        ActionListener exerciseListener = new ActionListener(){
            @Override
            /**
             * Method that determines the action for exercise, checks if the pet is in an unresponsive state, if not adjusts stats, and plays the necessary animation
             * @param ae
             */

            public void actionPerformed(ActionEvent ae){
                // Informs the player if they are trying to do an action that is unavailable due to their pet's state
                if(currentPet.isSleeping()){
                    JOptionPane.showMessageDialog(frame, "Your pet is sleeping right now!");
                }
                else if(currentPet.isAngry()){
                    JOptionPane.showMessageDialog(frame, "Your pet is too angry for that!");
                }
                else{
                petControl.exerciseWithPet();
                updateStats();
                regainFocus(gamePanel);
                coolDown(exercise,1000);
                }
                
            }
        };


        ActionListener sleepListener = new ActionListener() {
            @Override
            /** Method that determines the action for sleeping, checks if the pet is in an unresponsive state, if not adjusts stats, and plays the necessary animation
            * @param ae
            */

            public void actionPerformed(ActionEvent ae) {
                // Informs the player if they are trying to do an action that is unavailable due to their pet's state
                if (currentPet.isSleeping()) {
                    JOptionPane.showMessageDialog(frame, "Your pet is already sleeping!");
                } else if (currentPet.isAngry()) {
                    JOptionPane.showMessageDialog(frame, "Your pet is too angry for that!");
                } else {
                    sleepSound = new Sound("src/resources/sleeping.wav");
                    sleepSound.playSound(); // Start playing the sleeping sound
                    sleepDecayTimer.start();
                    regainFocus(gamePanel);
                    updateStats();
                    coolDown(sleep, 1000);
                }
            }
        };



        ActionListener giftListener = new ActionListener(){
            @Override
            /**
             * Method that determines the action for gifting, checks if the pet is in an unresponsive state, if not adjusts stats and plays the necessary animation
             * @param ae
             */

            public void actionPerformed(ActionEvent ae){
                // Informs the player if they are trying to do an action that is unavailable due to their pet's state
                if(currentPet.isSleeping()){
                    JOptionPane.showMessageDialog(frame, "Your pet is sleeping right now!");

                }
                else{
                	FileWriter filewriter = null;
                	FileReader filereader = null;
					try {
						filereader = new FileReader("src/pet_saves/"+currentPet.getName()+".csv"); // Reading in the pet's save file
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	try (CSVReader csvreader = new CSVReader(filereader)) {
						List<String[]> rows = null;
						try {
							rows = csvreader.readAll(); // Get all the rows
						} catch (IOException | CsvException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							filereader.close(); // Close the filereader
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						try {
							filewriter = new FileWriter("src/pet_saves/"+currentPet.getName()+".csv",true); // Create a filewriter to write to the pet's save file
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						// Write the updated gift inventory amounts to the pet's save file
						try (CSVWriter csvwriter = new CSVWriter(filewriter)) {
							String[] finalRow = rows.get(rows.size()-1);
							Object[] options = {"Ball x"+finalRow[9],"Plush x"+finalRow[10],"Sword x"+finalRow[11]};
							int option = JOptionPane.showOptionDialog(frame,"What would you like to gift your pet?","Gift Options",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
							if (option==0 && Integer.parseInt(finalRow[9])>0) {
								petControl.giftPet();
								currentPet.decrementBallCount();
								csvwriter.writeNext(new String[] {finalRow[0],finalRow[1],finalRow[2],finalRow[3],finalRow[4],finalRow[5],finalRow[6],finalRow[7],finalRow[8],(Integer.parseInt(finalRow[9])-1)+"",finalRow[10],finalRow[11],currentPet.getScore()+""});
							
							}
							else if (option==1 && Integer.parseInt(finalRow[10])>0) {
								petControl.giftPet();
								petControl.giftPet();
								petControl.giftPet();
								currentPet.decrementPlushCount();
								csvwriter.writeNext(new String[] {finalRow[0],finalRow[1],finalRow[2],finalRow[3],finalRow[4],finalRow[5],finalRow[6],finalRow[7],finalRow[8],finalRow[9],(Integer.parseInt(finalRow[10])-1)+"",finalRow[11],currentPet.getScore()+""});
							}
							else if (option==2 && Integer.parseInt(finalRow[11])>0){
								petControl.giftPet();
								petControl.giftPet();
								currentPet.decrementSwordCount();
								csvwriter.writeNext(new String[] {finalRow[0],finalRow[1],finalRow[2],finalRow[3],finalRow[4],finalRow[5],finalRow[6],finalRow[7],finalRow[8],finalRow[9],finalRow[10],(Integer.parseInt(finalRow[11])-1)+"",currentPet.getScore()+""});
							}
							else {
							}
						}
					} catch (HeadlessException | NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

            		try {
						filewriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	regainFocus(gamePanel);
                updateStats();
                coolDown(gift, 1000);
                }
                
            }
        };

        ActionListener vetListener = new ActionListener(){
            @Override
            /** 
             * Method that determines the action for taking the pet to the vet, checks if the pet is in an unresponsive state, if not adjusts stats, and plays the necessary animation
             * @param ae
            */

            public void actionPerformed(ActionEvent ae){
                // Informs the player if they are trying to do an action that is unavailable due to their pet's state
                if(currentPet.isSleeping()){
                    JOptionPane.showMessageDialog(frame, "Your pet is sleeping right now!");
                }
                else if(currentPet.isAngry()){
                    JOptionPane.showMessageDialog(frame, "Your pet is too angry for that!");
                }
                else{
                
                petControl.petToVet();
                coolDown(vet, 10000);
                updateStats();
                regainFocus(gamePanel);
                }
                
            }
        };


        /**
         * Listener for the inventory button.
         * Logs the pet's happiness level and updates the stats.
         */
        ActionListener inventoryListener = new ActionListener() {
            @Override
            /**
             * 
             * @param ae
             */
            public void actionPerformed(ActionEvent ae) {
                updateStats(); // Update the UI with the latest pet stats
            }
        };

        /**
         * Listener for the settings button.
         * Updates the pet stats without additional logic.
         */
        ActionListener settingsListener = new ActionListener() {
        	/**
        	 * @param ae
        	 */
            @Override
            public void actionPerformed(ActionEvent ae) {
                updateStats(); // Update the UI with the latest pet stats
            }
        };

        // Adding the listeners to the buttons themselves
        feed.addActionListener(feedListener);
        play.addActionListener(playListener);
        exercise.addActionListener(exerciseListener);
        sleep.addActionListener(sleepListener);
        gift.addActionListener(giftListener);
        vet.addActionListener(vetListener);
        inventory.addActionListener(inventoryListener);
        settings.addActionListener(settingsListener);

    }


        /**
         * Updates the pet's stats displayed in the GUI.
         * Sets text for the score, health, sleep level, fullness, and happiness labels,
         * and changes their color to red if the values are below 25% of the maximum.
         */
    private void updateStats() {
        score.setText("Score: " + currentPet.getScore());
        health.setText("Health: " + currentPet.getHealth());
        sleepLevel.setText("Sleep: " + currentPet.getSleep());
        fullness.setText("Fullness: " + currentPet.getFullness());
        happiness.setText("Happiness: " + currentPet.getHappiness());
        health.setName("Health");
        sleepLevel.setName("Sleep");
        fullness.setName("Hunger");
        happiness.setName("Happiness");
        score.setName("Score");
        frame.repaint(); // Ensure the GUI reflects the changes

        //Updates stats so that they turn red when below 25%
        updateStatLabel(health, currentPet.getHealth(), currentPet.getMaxHealth());
        updateStatLabel(sleepLevel, currentPet.getSleep(), currentPet.getMaxSleep());
        updateStatLabel(fullness, currentPet.getFullness(), currentPet.getMaxFullness());
        updateStatLabel(happiness, currentPet.getHappiness(), currentPet.getMaxHappiness());
    }

    

    /**
     * Returns the game panel for use in the application.
     *
     * @return the game panel as a JPanel
     */
    public JPanel getGamePanel() {
        return this.gamePanel;
    }

    /**
     * Disables a button for a specified duration, then re-enables it.
     *
     * @param button the JButton to disable
     * @param duration the cooldown duration in milliseconds
     */
    private void coolDown(JButton button, int duration) {
        button.setEnabled(false); // Disable the button

        Timer cooldownTimer = new Timer(duration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setEnabled(true); // Re-enable the button after the cooldown
            }
        });
        cooldownTimer.setRepeats(false); // Ensure the timer only runs once
        cooldownTimer.start(); // Start the cooldown timer
    }

    /**
     * Updates the appearance of a stat label based on its value.
     * Changes the label's text color to red if below 25% of the maximum value.
     *
     * @param label the JLabel to update
     * @param value the current value of the stat
     * @param maxValue the maximum value of the stat
     */
    private void updateStatLabel(JLabel label, int value, int maxValue) {
        if (value < maxValue * 0.25) { // If below 25%
            label.setForeground(Color.RED); // Change text color to red
            label.setFont(new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize())); // Make it bold
        } else {
            label.setForeground(Color.BLACK); // Default text color
            label.setFont(new Font(label.getFont().getName(), Font.BOLD, label.getFont().getSize())); // Default font
        }
        label.setText(label.getName() + ": " + value); // Update label text
    }

    /**
     * Regains focus for a specific panel to ensure keyboard interactions work.
     *
     * @param panel the JPanel to request focus for
     */
    private void regainFocus(JPanel panel) {
        panel.requestFocusInWindow();
    }
}