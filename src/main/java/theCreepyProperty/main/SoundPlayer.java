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

    public void setVolume(int volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            float min = gainControl.getMinimum(); // z. B. -80 dB
            float max = gainControl.getMaximum(); // z. B. +6 dB

            // Sicherstellen, dass der Wert zwischen 1 und 100 liegt
            volume = Math.max(1, Math.min(100, volume));

            // Umwandlung des Wertes (1-100) in einen logarithmischen dB-Wert
            float gain = (float) (min + (max - min) * (Math.log10(volume) / Math.log10(100)));

            // Lautstärke setzen
            gainControl.setValue(gain);

            System.out.println("[SoundPlayer]: Lautstärke gesetzt auf: " + volume + "% (" + gain + " dB)");
        }
    }
}