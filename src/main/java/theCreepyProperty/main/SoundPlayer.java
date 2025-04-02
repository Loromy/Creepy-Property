package theCreepyProperty.main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip clip;
    private final String filePath;

    // SoundPlayer class for handling audio-file
    public SoundPlayer(String filePath) {
        System.out.println(".............................SoundPlayer..............................");
        this.filePath = filePath;
        try {
            // Load audio file
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("✖ [SoundPlayer]: Error loading audio file: " + e.getMessage());
        }
    }

    // Play sound from the beginning
    public void play() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    // Stop sound if it is playing
    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Adjust volume (1-100 scale converted to dB)
    public void setVolume(int volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            float min = gainControl.getMinimum(); // e.g., -80 dB
            float max = gainControl.getMaximum(); // e.g., +6 dB

            // Ensure volume stays between 1 and 100
            volume = Math.max(1, Math.min(100, volume));

            // Convert linear scale (1-100) to logarithmic dB scale
            float gain = (float) (min + (max - min) * (Math.log10(volume) / Math.log10(100)));

            // Set volume
            gainControl.setValue(gain);

            // Extract and print sound file name
            String fileNameWithExtension = this.filePath.substring(this.filePath.lastIndexOf("/") + 1);
            String soundName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));

            System.out.println("\uD83D\uDD6A [SoundPlayer]: Volume for [" + soundName + "] set to: " + volume + "% (" + gain + " dB).");
        }
    }

    // Getter Methoden
    public Clip getClip() {
        return this.clip;
    }
}