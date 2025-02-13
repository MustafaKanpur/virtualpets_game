// Import necessary libraries for UI components, event handling, file I/O, and CSV handling
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import java.awt.CardLayout;
import java.awt.Color;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

/**
 * The ParentalControlScreen class represents a UI panel for managing parental controls in the application.
 * Users can set time restrictions for gameplay, reset statistics, and enable or disable parental controls.
 * @author Teagan Rand Martins
 */
@SuppressWarnings("serial")
public class ParentalControlScreen extends JPanel {

    // UI components for the parental control screen
    private JButton exit;         // Button to exit the screen
    private JButton reset;        // Button to reset player statistics
    private CSVReader csvreader;  // Reader for reading CSV data
    private FileReader filereader; // FileReader for accessing CSV file
    private JSpinner hourBegin;   // Spinner for setting start hour
    private JSpinner hourEnd;     // Spinner for setting end hour
    private JSpinner minuteBegin; // Spinner for setting start minute
    private JSpinner minuteEnd;   // Spinner for setting end minute
    private JButton confirm;      // Button to confirm time restrictions
    private JToggleButton on;     // Toggle button to enable/disable parental control

    /**
     * Constructs a new ParentalControlScreen.
     *
     * @param cl         the CardLayout for switching between panels
     * @param panelCont  the container JPanel holding the different screens
     * @param mainMenu   the MainMenu instance to reset states or display messages
     * @throws IOException if there is an error accessing the parental control CSV file
     * @throws CsvException if there is an error reading the CSV data
     */
    public ParentalControlScreen(CardLayout cl, JPanel panelCont, MainMenu mainMenu) throws IOException, CsvException {
        // Initialize UI components
        this.exit = new JButton("Exit");
        this.reset = new JButton("Reset Statistics");
        this.confirm = new JButton("Confirm");
        this.on = new JToggleButton("On/Off");

        // Initialize spinners for time settings
        this.hourBegin = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1)); // Hour spinner (0-23)
        this.hourEnd = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));   // End hour spinner (0-23)
        this.minuteBegin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1)); // Minute spinner (0-59)
        this.minuteEnd = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));   // End minute spinner (0-59)

        // Read parental control data from a CSV file
        this.filereader = new FileReader("src/resources/parental_controls.csv");
        this.csvreader = new CSVReader(this.filereader);
        List<String[]> rows = csvreader.readAll(); // Get all rows from the CSV

        /**
         * ActionListener for the Exit button.
         * Switches back to the main menu or another designated screen.
         */
        this.exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cl.show(panelCont, "2"); // Show panel with identifier "2"
            }
        });

        /**
         * ActionListener for the Reset button.
         * Resets player statistics after user confirmation.
         */
        this.reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int answer = JOptionPane.showConfirmDialog(mainMenu, "Are you sure?"); // Confirmation dialog
                if (answer == 0) { // If "Yes" is selected
                    try {
                        mainMenu.resetStart(); // Reset the main menu state
                        CSVReader csvreader = new CSVReader(new FileReader("src/resources/parental_controls.csv"));
                        List<String[]> rows = csvreader.readAll();

                        // Append a new row with reset statistics
                        FileWriter filewriter = new FileWriter("src/resources/parental_controls.csv", true);
                        CSVWriter csvwriter = new CSVWriter(filewriter);
                        csvwriter.writeNext(new String[]{0 + "", 0 + "", 0 + "", rows.get(rows.size() - 1)[3] + "", rows.get(rows.size() - 1)[4] + "", rows.get(rows.size() - 1)[5] + "", rows.get(rows.size() - 1)[6] + ""});
                        csvwriter.close();
                    } catch (IOException | CsvException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        /**
         * ActionListener for the Confirm button.
         * Validates and applies the specified time restrictions to the parental control settings.
         */
        this.confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (((int) hourBegin.getValue() == (int) hourEnd.getValue() && ((int) minuteBegin.getValue() < (int) minuteEnd.getValue())) || ((int) hourBegin.getValue() < (int) hourEnd.getValue())) {
                    String[] lastRow = null;
                    try {
                        CSVReader reader = new CSVReader(new FileReader("src/resources/parental_controls.csv"));
                        List<String[]> rows = reader.readAll();
                        lastRow = rows.get(rows.size() - 1);

                        FileWriter filewriter = new FileWriter("src/resources/parental_controls.csv", true);
                        CSVWriter csvwriter = new CSVWriter(filewriter);
                        csvwriter.writeNext(new String[]{lastRow[0], lastRow[1], lastRow[2], hourBegin.getValue() + "", minuteBegin.getValue() + "", hourEnd.getValue() + "", minuteEnd.getValue() + ""});
                        csvwriter.close();
                    } catch (IOException | CsvException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(mainMenu, "Time Restriction Successfully Applied");
                } else {
                    JOptionPane.showMessageDialog(mainMenu, "Starting time must be BEFORE Ending time", "Invalid Time Restriction", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add components to the panel layout
        this.add(this.exit); // Add the Exit button to the panel

        // Populate layout with components and labels
        for (int i = 0; i < 100; i++) {
        	if (i==7) {
        		this.add(new JLabel("Total Playtime: " + Integer.parseInt(rows.get(rows.size()-1)[1])/3600 + " h " + (Integer.parseInt(rows.get(rows.size()-1)[1])%3600)/60 + " m " + Integer.parseInt(rows.get(rows.size()-1)[1])%60 + " s "));
        	}
        	if (i==14) {
        		this.add(new JLabel("Average Playtime: " + Integer.parseInt(rows.get(rows.size()-1)[2])/3600 + " h " + (Integer.parseInt(rows.get(rows.size()-1)[2])%3600)/60 + " m " + Integer.parseInt(rows.get(rows.size()-1)[2])%60 + " s "));
        	}
        	if (i==21) {
        		this.add(this.reset);
        	}
        	if (i==76) {
        		this.add(hourBegin);
        	}
        	if (i==76) {
        		this.add(minuteBegin);
        	}
        	if (i==76) {
        		this.add(new JLabel("-"));
        	}
        	if(i==76) {
        		this.add(hourEnd);
        	}
        	if (i==76) {
        		this.add(minuteEnd);
        	}
        	if (i==81) {
        		this.add(confirm);
        	}
        	if (i==88) {
        		this.add(on);
        	}
        	else {
            	this.add(new JLabel(" "));	
        	}
        }

        

        // Set layout and background color
        this.setLayout(new GridLayout(0, 8)); // Grid layout with 8 columns
        this.setBackground(Color.white); // Set background to white
    }
}
