import javax.swing.*;
import java.awt.*;

/**
 * Instruction screen used to display instructions on how to play our Virtual
 * Pets game.
 * 
 * <br>
 * <br>
 * This screen displays instructions on how to play to the user. Instructions
 * are presented in text format
 * and can be scrolled through to read in their entirety.
 * <br>
 * <br>
 * 
 * @version 1.1
 * @author Libby Cook
 * @author Teagan Rand Martins
 */

@SuppressWarnings("serial")
public class InstructionScreen extends Screen {

    /**
     * Instruction screen constructor. Creates a new instruction screen.
     * 
     * <br>
     * <br>
     * Contains the code for displaying the instructions correctly on the screen, as
     * well as the
     * code for interaction via scrolling, and for navigation via buttons back to
     * the main menu.
     * <br>
     * <br>
     * 
     * @param gameWindow the window in which the screen resides
     * @param cardLayout the card layout of the window
     * @param cardPanel  the card panel of the window
     */
    public InstructionScreen(JFrame gameWindow, CardLayout cardLayout, JPanel cardPanel) {

        // Sets up the screen itself
        super(gameWindow, cardLayout, cardPanel);
        setBackground(new java.awt.Color(161, 204, 247));
        setLayout(new BorderLayout());

        // Creates the back panel, including the back button to the main menu
        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        JButton backButton = new JButton("Back to main menu");
        backPanel.add(backButton);

        // Creates the main panel for the body of the screen
        JPanel instructionPanel = new JPanel();
        instructionPanel.setOpaque(false);
        JTextArea textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 20));
        textArea.setText("Welcome to VPets, we will be teaching you how to play our game.\n\n\n" +
                "BEFORE PLAYING A GAME\n\n" +
                "If you want to start an entirely new game, go back to the main menu and click on New Game.\n" +
                "You will be able to choose from a selection of three diferent pet types, and name your pet.\n" +
                "Each pet type has slightly different characteristics that will impact gameplay.\n\n" +
                "If you want to play a previously saved game, go back to the main menu and click the Load Game button.\n"
                +
                "You will be able to see previously played games and click one of them to start playing again.\n\n"
                +
                "To change parental controls, click the Parental Controls button and enter your password to access the controls.\n\n"
                +
                "To turn sound on/off, go back to the main menu and click on the Settings button, from here you can change the volume.\n\n"
                +
                "The type of pet chosen will change how quickly its statistics decrease (see below)\n\n\n" +
                "STATISTICS \n\n" +
                "Your pet has statistics of health (how healthy it is), fullness (zero means very hungry, 100 means full),"
                +
                " happiness, and sleep (zero means it is asleep, 100 means very awake). These are displayed on the game screen.\n\n"
                +
                "During the game, your pet will slowly decrease in fullness, sleep, and happiness.\n\n" +
                "If your pet reaches zero fullness, it will become hungry. When this happens its happiness will start to decline faster,"
                +
                "and its health will also begin to decrease.\n" +
                "To increase your pets fullness, feed it treats.\n\n"
                + "If you pet reaches zero 'sleep', a health penalty is applied and the pet will fall asleep. " +
                "Whilst it is asleep, your pets sleep will increase again, but you cannot interact with it. Your " +
                "pets happiness and fullness will still decline whilst its asleep. Your pet will not wake "
                + "up until it reaches maximum sleep.\n\n"
                +
                "If your pet reaches zero happiness, it will become angry.\n" +
                "You will only be allowed to interact with you pet in ways that increase its happiness." +
                "It will stay angry until it reaches back to 50% happiness.\n\n" +
                "When a statistic is at less than 25%, you will get a warning as the statistic will go red. \n\n\n" +
                "COMMANDS\n\n" +
                "There are certain different commands you can do to affect the pets statistics. These are:\n\n" +
                "Go to sleep: your pet will sleep until it reaches maximum sleep again.\n\n" +
                "Feed pet: your pets fullness will increase by a certain amount depending on the food. You must have food in your inventory to do this.\n\n"
                +
                "Give gift: this will increase your pets happiness. You must have a gift in your inventory.\n\n" +
                "Take to vet: pets health increases by a certain amount. This has a cool down of 1 hour.\n\n" +
                "Play: play with your pet, increasing its happiness. This has a 10 minute cool down.\n\n" +
                "Exercise: take your pet on a walk. This will increase their health but decrease their sleep and fullness.\n\n"
                + "However these commands are not available in specific scenarios:\n\n" +
                "If your pet is dead no commands are available.\n" +
                "If your pet is asleep no commands are available.\n" +
                "If your pet is angry you can only give it a gift or play with it.\n" +
                "If your pet is hungry you can perform all commands.\n" +
                "If your pet is normal you can perform all commands.\n\n\n" +
                "INVENTORY\n\n" +
                "You will be able to see your inventory during the game.\n" +
                "This will display your current food and gift items that you have.\n" +
                "You will receive items into your inventory at specific time intervals.\n" +
                "You can only feed your pet/gift your pet using items from your inventory.\n\n\n" +
                "SCORE\n\n" +
                "During the game you will be able to see your current score.\n\n" +
                "Whenever you do a positive action (feeding your pet, exercising, etc.) your score will increase" +
                "Your score will also increase slowly over time\n\n\n" +
                "PARENTAL CONTROLS\n\n" +
                "Parents can navigate to the parental controls screen from the main menu to:\n\n" +
                "Set limitations on what times of day the game can be played.\n" +
                "Keep track of statistics like total time played.\n" +
                "Revive a pet: choose a save file and the pet will be revived to have maximum of all statistics.\n");
        textArea.setOpaque(false);

        // Creates a scroll pane for the instruction text
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setOpaque(false);
        instructionPanel.add(scrollPane);

        // Adds the panels to the screen
        add(backPanel, BorderLayout.NORTH);
        add(instructionPanel, BorderLayout.CENTER);

        // Adds action listeners to the button
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main Screen"));
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "1"));
    }
}
