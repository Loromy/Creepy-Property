package theCreepyProperty.checker;

import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileCheck {

    public FileCheck() {
        System.out.println(".............................FileCheck..............................");
    }

    public String checkImage(String className, String path) {
        Image image = new Image(path);
        if (image.isError()) {
            System.err.println("✖ [" + className + "]: loadImage Failed to load image: " + path);
        }
        return path;
    }

    public String checkPath(String className, String path) {
        boolean exists = Files.exists(Paths.get(path));
        if (!exists) {
            System.err.println("✖ [" + className + "]: loadPath Failed to load Filepath: " + path);
        }
        path = "file:" + path;
        return path;
    }
}
