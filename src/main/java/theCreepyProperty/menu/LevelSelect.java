package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import theCreepyProperty.Save.ReadWriteSpielstand;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.LevelSelectScene;

public class LevelSelect {
    private final GUI gui;
    private LevelSelectScene scene;
    private ReadWriteSpielstand readWriteSpielstand;

    private final Pane backgroundLevelSelect = new Pane(); // Background
    private final Pane pLevelSelect = new Pane(); // Menu Items
    private final VBox vBoxLevelSelect = new VBox();
    private final HBox vBoxLevel1 = new HBox();
    private final HBox vBoxLevel2 = new HBox();
    private final HBox vBoxLevel3 = new HBox();
    private final VBox hBoxLevel = new VBox();
    private final HBox hBoxLevelButton = new HBox();

    private Label title;
    private final Button bL1;
    private final Button bL2;
    private final Button bL3;
    private final Button bL4;
    private final Button bL5;
    private final Button bL6;
    private final Button bL7;
    private final Button bL8;
    private final Button bL9;
    private final Button startButton;
    private final Button backButton;

    public LevelSelect(GUI gui, LevelSelectScene scene) {
        this.gui = gui;
        this.scene = scene;

        this.readWriteSpielstand = new ReadWriteSpielstand(this.gui.getGameScene().getGuiComponents());

        this.title = new Label();
        this.bL1 = new Button();
        this.bL2 = new Button();
        this.bL3 = new Button();
        this.bL4 = new Button();
        this.bL5 = new Button();
        this.bL6 = new Button();
        this.bL7 = new Button();
        this.bL8 = new Button();
        this.bL9 = new Button();
        this.startButton = new Button();
        this.backButton = new Button();

        // Lade das Bild
        BackgroundImage backgroundImage = getBackgroundImage();
        backgroundLevelSelect.setBackground(new Background(backgroundImage));
        this.scene.setBlur(15); //Menu blur

        this.backgroundLevelSelect.setVisible(true);
        this.pLevelSelect.setVisible(true);

        // Add menu items
        this.title.setText("Level Select");
        this.title.setId("name");
        this.bL1.setText("Level 1");
        this.bL2.setText("Level 2");
        this.bL3.setText("Level 3");
        this.bL4.setText("Level 4");
        this.bL5.setText("Level 5");
        this.bL6.setText("Level 6");
        this.bL7.setText("Level 7");
        this.bL8.setText("Level 8");
        this.bL9.setText("Level 9");
        this.startButton.setText("Play");
        this.startButton.setStyle("-fx-pref-width: 200px; -fx-min-width: 200px;");
        this.backButton.setText("Back");
        this.backButton.setStyle("-fx-pref-width: 200px; -fx-min-width: 200px;");

        // disable
        this.bL2.setDisable(true);
        this.bL3.setDisable(true);
        this.bL4.setDisable(true);
        this.bL5.setDisable(true);
        this.bL6.setDisable(true);
        this.bL7.setDisable(true);
        this.bL8.setDisable(true);
        this.bL9.setDisable(true);
        this.startButton.setDisable(true);

        // Add buttons to the VBox
        this.vBoxLevel1.getChildren().addAll(bL1, bL2, bL3);
        this.vBoxLevel2.getChildren().addAll(bL4, bL5, bL6);
        this.vBoxLevel3.getChildren().addAll(bL7, bL8, bL9);
        this.hBoxLevel.getChildren().addAll(vBoxLevel1, vBoxLevel2, vBoxLevel3);
        this.hBoxLevelButton.getChildren().addAll(startButton, backButton);
        this.vBoxLevelSelect.getChildren().addAll(title, hBoxLevel, hBoxLevelButton);
        this.vBoxLevelSelect.setId("background");
        this.pLevelSelect.getChildren().add(vBoxLevelSelect);

        this.pLevelSelect.setPrefSize(this.gui.getWidth(), this.gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundLevelSelect.setPrefSize(gui.getWidth(), gui.getHeight());
        this.hBoxLevel.setSpacing(20);
        this.hBoxLevel.setAlignment(Pos.CENTER);
        setLevelSelectPosition(vBoxLevelSelect); //630
        setLevelSelectPositionLR(vBoxLevel1);
        setLevelSelectPositionLR(vBoxLevel2);
        setLevelSelectPositionLR(vBoxLevel3);
        setPlayBack(hBoxLevelButton);

        //setLevelFortschritt();
    }

    public void setLevelFortschritt(int level, boolean unlocked, boolean completed) {
        //TODO Fortschritt setzen
    }

    private void setLevelSelectPosition(VBox vBox) {
        vBox.setPrefSize(600, 500);
        vBox.setLayoutX((gui.getWidth() - (double) 600) / 2);
        vBox.setLayoutY((gui.getHeight() - (double) 500) / 2);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
    }

    private void setLevelSelectPositionLR(HBox vBox) {
        vBox.setPrefSize(300, 50);
        vBox.setLayoutX((gui.getWidth() - (double) 300) / 2);
        vBox.setLayoutY((gui.getHeight() - (double) 300) / 2);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
    }

    private void setPlayBack(HBox hBox) {
        hBox.setPrefSize(200,100);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
    }

    private BackgroundImage getBackgroundImage() {
        Image image = new Image("file:src/resources/background/Creepy Property background1.png");

        // Setze das Hintergrundbild
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        return backgroundImage;
    }

    // Getter Methoden
    public  Button getbL1() {
        return this.bL1;
    }

    public  Button getbL2() {
        return this.bL2;
    }

    public  Button getbL3() {
        return this.bL3;
    }

    public  Button getbL4() {
        return this.bL4;
    }

    public  Button getbL5() {
        return this.bL5;
    }

    public  Button getbL6() {
        return this.bL6;
    }

    public  Button getbL7() {
        return this.bL7;
    }

    public  Button getbL8() {
        return this.bL8;
    }

    public  Button getbL9() {
        return this.bL9;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Pane getpLevelSelect() {
        return pLevelSelect;
    }

    public Pane getBackgroundLevelSelect() {
        return backgroundLevelSelect;
    }
}