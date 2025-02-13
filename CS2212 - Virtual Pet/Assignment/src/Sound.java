// Import necessary libraries for audio handling
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The Sound class manages audio playback functionality using Java's sound APIs.
 * It supports loading an audio file, playing it, and stopping the playback.
 * @author Allen Ismail
 */
public class Sound {

    private Clip clip; // Audio clip to play the sound

    /**
     * Constructs a Sound object and initializes the audio clip with the specified file.
     *
     * @param filePath the path to the audio file
     */
    public Sound(String filePath) {
        try {
            // Specify the file path to the audio file
            File audioFile = new File(filePath);

            // Create an AudioInputStream to read the audio file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get an audio Clip object from the AudioSystem
            clip = AudioSystem.getClip();

            // Open the clip with the audio stream, preparing it for playback
            clip.open(audioStream);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // Handle exceptions for unsupported audio formats, I/O errors, or unavailable audio lines
            System.out.println("Error initializing audio: " + e.getMessage());
        }
    }

    /**
     * Plays the audio clip from the beginning.
     * If the clip is not initialized, logs a message to the console.
     */
    public void playSound() {
        if (clip != null) { // Check if the clip is initialized
            clip.start(); // Start playback
        } else {
            // Notify if the audio clip is not properly initialized
            System.out.println("Audio clip is not initialized.");
        }
    }

    /**
     * Stops the audio playback and releases resources associated with the audio clip.
     * If the clip is not initialized, logs a message to the console.
     */
    public void stopSound() {
        if (clip != null) { // Check if the clip is initialized
            clip.stop(); // Stop playback
            clip.close(); // Release the clip resources
        } else {
            // Notify if the audio clip is not properly initialized
            System.out.println("Audio clip is not initialized.");
        }
    }
}
