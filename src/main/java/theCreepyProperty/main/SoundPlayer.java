package theCreepyProperty.main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip clip;

    public SoundPlayer(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("[SoundPlayer]: Fehler beim Laden der Audiodatei: " + e.getMessage() + " ️️✖");
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Startet den Sound von Anfang an
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = gainControl.getMinimum(); // z. B. -80.0 dB
            float max = gainControl.getMaximum(); // z. B. 6.0 dB

            // Verhindern, dass volume außerhalb des Bereichs liegt
            volume = Math.max(0.0f, Math.min(1.0f, volume));

            // Logarithmische Umrechnung für eine natürliche Lautstärkeanpassung
            float gain = (float) (min + (max - min) * Math.log10(1 + 9 * volume));

            gainControl.setValue(gain);
        }
    }
}



