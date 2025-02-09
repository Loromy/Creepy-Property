package theCreepyProperty.DeveloperMode;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import theCreepyProperty.scenes.GameScene;

public class DevMode {
    private final PasswordHandler passwordHandler;
    private GameScene gameScene;

    private final StackPane pane;
    private final Scene scene;
    private final VBox layout;

    private ImageView background;
    private final String adminPasswort = "123";

    public DevMode(PasswordHandler passwordHandler) {
        this.passwordHandler = passwordHandler;
        this.background = new ImageView(new Image("file:src/resources/background/DevBackground.png"));

        this.pane = new StackPane();
        this.scene = new Scene(pane, 300, 200);
        this.layout = new VBox();

        this.pane.getStylesheets().add("file:src/resources/style/style.css");
    }

    public void show(GameScene gameScene) {
        this.gameScene = gameScene;

        Stage passwordStage = new Stage();  // Neues Fenster
        PasswordField passwordField = new PasswordField();
        Button bestaetigenButton = getButton(passwordField, passwordStage);

        this.layout.getChildren().addAll(passwordField, bestaetigenButton);
        this.layout.setAlignment(Pos.CENTER);
        this.layout.setSpacing(10);
        this.layout.setStyle("-fx-padding: 30px");

        this.pane.getChildren().addAll(this.background, this.layout);



        passwordStage.setTitle("Passwort eingeben");
        passwordStage.setScene(scene);
        passwordStage.show();
    }

    private Button getButton(PasswordField passwordField, Stage passwordStage) {
        Button bestaetigenButton = new Button("Bestätigen");

        bestaetigenButton.setOnAction(e -> {
            String password = passwordField.getText();

            if (password.equals(adminPasswort)) {
                passwordHandler.handlePassword(password);  // Passwort an den Handler übergeben
                passwordStage.close();

                this.gameScene.getMenu().getSettings().getButton3().setText("DevMode [ON]");

                this.gameScene.getMapCreate().triggerCollision();
                this.gameScene.getPlayer().triggerOverlay();
            } else {
                passwordStage.close();
                this.gameScene.getMenu().getSettings().getButton3().setText("DevMode [OFF]");
            }
        });
        return bestaetigenButton;
    }
}
