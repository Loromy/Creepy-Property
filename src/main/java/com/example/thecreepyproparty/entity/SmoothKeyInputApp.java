import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SmoothKeyInputApp extends Application {

    private double speed = 5;  // Geschwindigkeit der Bewegung
    private double dx = 0, dy = 0;  // Richtung der Bewegung

    @Override
    public void start(Stage primaryStage) {
        // Erstelle ein Rechteck
        Rectangle rect = new Rectangle(100, 50, Color.BLUE);

        // Setze das Rechteck in die Mitte
        StackPane root = new StackPane();
        root.getChildren().add(rect);

        // Szene erstellen
        Scene scene = new Scene(root, 600, 400);

        // Timeline für kontinuierliche Bewegung
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(10), event -> {
                // Update der Position basierend auf der Richtung
                rect.setX(rect.getX() + dx);
                rect.setY(rect.getY() + dy);
            })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Tasteneingabe erfassen und Richtung ändern
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                dy = -speed;  // Bewege nach oben
            } else if (event.getCode() == KeyCode.DOWN) {
                dy = speed;   // Bewege nach unten
            } else if (event.getCode() == KeyCode.LEFT) {
                dx = -speed;  // Bewege nach links
            } else if (event.getCode() == KeyCode.RIGHT) {
                dx = speed;   // Bewege nach rechts
            }
        });

        // Wenn die Taste losgelassen wird, stoppe die Bewegung in der jeweiligen Richtung
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                dy = 0;  // Stoppe vertikale Bewegung
            }
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                dx = 0;  // Stoppe horizontale Bewegung
            }
        });

        // Stage konfigurieren und anzeigen
        primaryStage.setTitle("Flüssige Steuerung mit der Tastatur");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
