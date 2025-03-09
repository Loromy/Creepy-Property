package theCreepyProperty.checker;

import javafx.scene.image.Image;

public class ImageCheck {

    public ImageCheck() {
        System.out.println(".............................ImageCheck..............................");
    }

    public String checkImage(String className, String path) {
        Image image = new Image(path);
        if (image.isError()) {
            System.err.println("✖ [" + className + "]: loadImage Failed to load image: " + path);
        }
        return path;
    }
}
