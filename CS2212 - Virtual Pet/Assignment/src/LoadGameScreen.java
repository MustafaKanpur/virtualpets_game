import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

/**
 * The LoadGameScreen class represents a JPanel for loading a saved game.
 * It allows the user to select a saved file from a dropdown menu and load the game
 * or return to the main menu.
 * @author Teagan Rand Martins
 */
@SuppressWarnings("serial")
public class LoadGameScreen extends JPanel {

    private JComboBox<String> dropdown; // Dropdown menu for selecting save files
    private JButton start; // Button to load the selected save file
    private GamePlayScreen gamePlayScreen; // Screen to display after loading the game
    private JButton exitButton; // Button to exit back to the main menu

    /**
     * Constructs a LoadGameScreen panel.
     *
     * @param cl         the CardLayout for switching screens
     * @param panelCont  the JPanel container holding all screens
     * @param frame      the main JFrame of the application
     * @param music      the Sound object for background music
     */
    public LoadGameScreen(CardLayout cl, JPanel panelCont, JFrame frame, Sound music) {
        exitButton = new JButton("Exit"); // Initialize the exit button
        start = new JButton("Load"); // Initialize the load button

        // Initialize the dropdown for listing save files
        dropdown = new JComboBox<>();

        // Directory where save files are stored
        File dir = new File("src/pet_saves/");
        File[] directoryListing = dir.listFiles(); // Get the list of files in the directory

        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Add the file name to the dropdown if it's a file
                if (child.isFile()) {
                    dropdown.addItem(child.getName());
                }
            }
        } else {
            // If the directory does not exist, log a message
        }

        // Add the dropdown to the panel
        this.add(dropdown);

        /**
         * ActionListener for the "Load" button.
         * Loads the selected save file and creates a new game screen with the saved data.
         */
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileReader filereader = null;
                try {
                    // Open the selected save file
                    filereader = new FileReader("src/pet_saves/" + dropdown.getSelectedItem().toString());
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                try (CSVReader csvreader = new CSVReader(filereader)) {
                    Pet loadedPet; // Pet object to store the loaded data
                    List<String[]> rows = null;
                    try {
                        // Read all rows from the save file
                        rows = csvreader.readAll();
                    } catch (IOException | CsvException e1) {
                        e1.printStackTrace();
                    }

                    // Get the last row of the save file, which contains the most recent data
                    String[] lastRow = rows.get(rows.size() - 1);

                    // Create the Pet object based on the difficulty level in the save file
                    if (lastRow[0].equals("easy")) {
                        loadedPet = new Pet("easy",
                                Integer.parseInt(lastRow[2]), Integer.parseInt(lastRow[3]),
                                Integer.parseInt(lastRow[4]), Integer.parseInt(lastRow[5]),
                                100, 100, 100, 100, 1, 1, 1, 1,
                                10, 10, 10, 10,
                                Integer.parseInt(lastRow[6]), Integer.parseInt(lastRow[7]),
                                Integer.parseInt(lastRow[8]), Integer.parseInt(lastRow[9]),
                                Integer.parseInt(lastRow[10]), Integer.parseInt(lastRow[11]),
                                lastRow[1], Integer.parseInt(lastRow[12]));
                    } else if (lastRow[0].equals("medium")) {
                        loadedPet = new Pet("medium",
                                Integer.parseInt(lastRow[2]), Integer.parseInt(lastRow[3]),
                                Integer.parseInt(lastRow[4]), Integer.parseInt(lastRow[5]),
                                100, 100, 100, 100, 2, 2, 2, 2,
                                8, 8, 8, 8,
                                Integer.parseInt(lastRow[6]), Integer.parseInt(lastRow[7]),
                                Integer.parseInt(lastRow[8]), Integer.parseInt(lastRow[9]),
                                Integer.parseInt(lastRow[10]), Integer.parseInt(lastRow[11]),
                                lastRow[1], Integer.parseInt(lastRow[12]));
                    } else {
                        loadedPet = new Pet("hard",
                                Integer.parseInt(lastRow[2]), Integer.parseInt(lastRow[3]),
                                Integer.parseInt(lastRow[4]), Integer.parseInt(lastRow[5]),
                                100, 100, 100, 100, 3, 3, 3, 3,
                                6, 6, 6, 6,
                                Integer.parseInt(lastRow[6]), Integer.parseInt(lastRow[7]),
                                Integer.parseInt(lastRow[8]), Integer.parseInt(lastRow[9]),
                                Integer.parseInt(lastRow[10]), Integer.parseInt(lastRow[11]),
                                lastRow[1], Integer.parseInt(lastRow[12]));
                    }

                    try {
                        // Close the file reader
                        filereader.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    // Create a new GamePlayScreen with the loaded pet
                    gamePlayScreen = new GamePlayScreen(loadedPet, frame, lastRow[1], music, cl, panelCont);
                } catch (NumberFormatException | IOException e1) {
                    e1.printStackTrace();
                }

                // Add the new game screen to the panel container
                panelCont.add(gamePlayScreen.getGamePanel(), "8");
                // Show the game screen
                cl.show(panelCont, "8");
            }
        });

        // Add the "Load" button to the panel
        this.add(start);

        /**
         * ActionListener for the "Exit" button.
         * Returns the user to the main menu screen.
         */
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch back to the main menu screen
                cl.show(panelCont, "1");
            }
        });

        // Add the "Exit" button to the panel
        this.add(exitButton);
    }
}
