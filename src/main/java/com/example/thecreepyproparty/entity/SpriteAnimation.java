package com.example.thecreepyproparty.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation {
    private Player player;

//    private ImageView imageView;
    private int frameCount;
    private int frameWidth;
    private int frameHeight;
    private int currentFrame = 0;
    private Timeline animation;

    public SpriteAnimation(Player player, int frameCount, int frameWidth, int frameHeight) {
        this.player = player;

        // Lade das Sprite-Sheet
        //Image spriteSheet = new Image(imagePath);
//        this.imageView = new ImageView(player.getSpriteSheet());
        this.frameCount = frameCount;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;

        // Initialer Viewport
        //imageView.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        //imageView.setViewport(player.getPlayer());

        // Animation einrichten
        animation = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> updateFrame()));
        animation.setCycleCount(Timeline.INDEFINITE);  // Endlos-Wiederholung
    }

    // Startet die Animation
    public void play() {
        animation.play();
    }

    // Stoppt die Animation
    public void stop() {
        animation.stop();
    }

//    // Gibt das ImageView zurück, damit es in anderen Klassen verwendet werden kann
//    public ImageView getImageView() {
//        return imageView;
//    }

    // Methode zur Aktualisierung des Frames
    private void updateFrame() {
        int x = currentFrame * frameWidth;
        player.getImageView().setViewport(new Rectangle2D(x, 0, frameWidth, frameHeight));
        currentFrame = (currentFrame + 1) % frameCount;
    }
}
