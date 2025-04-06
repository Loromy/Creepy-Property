package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import theCreepyProperty.checker.FileCheck;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.LevelSelectScene;

public class LevelSelect {
    private final GUI gui;

    // UI components
    private final Pane backgroundLevelSelect = new Pane();
    private final Pane pLevelSelect = new Pane();
    private final VBox vBoxLevelSelect = new VBox();
    private final HBox hBoxLine1 = new HBox();
    private final HBox hBoxLine2 = new HBox();
    private final HBox hBoxLine3 = new HBox();
    private final VBox vBoxLevel = new VBox();
    private final HBox hBoxLevelButton = new HBox();

    // Labels and buttons
    private final Label title;
    private final Label level;
    private final Button bTutorial;
    private final Button bL1, bL2, bL3, bL4, bL5, bL6, bL7, bL8, bL9;
    private final Label levelTime;
    private final Button startButton;
    private final Button backButton;

    public LevelSelect(GUI gui, LevelSelectScene scene) {
        System.out.println(".............................LevelSelect..............................");
        this.gui = gui;

        // Initialize labels and buttons
        this.title = new Label("Level Select");
        this.level = new Label("Level: _");
        this.levelTime = new Label("Highscore: --h : --m : --s : --ms || - : Deaths");
        this.bTutorial = new Button("Tutorial");
        this.bL1 = new Button("Level 1");
        this.bL2 = new Button("Level 2");
        this.bL3 = new Button("Level 3");
        this.bL4 = new Button("Level 4");
        this.bL5 = new Button("Level 5");
        this.bL6 = new Button("Level 6");
        this.bL7 = new Button("Level 7");
        this.bL8 = new Button("Level 8");
        this.bL9 = new Button("Level 9");
        this.startButton = new Button("Play");
        this.backButton = new Button("Back");

        // Set IDs for styling (CSS)
        this.title.setId("name");
        this.level.setId("text");
        this.levelTime.setId("text");
        this.bTutorial.setId("tutorial-button");

        // Set background image
        BackgroundImage backgroundImage = getBackgroundImage();
        backgroundLevelSelect.setBackground(new Background(backgroundImage));

        // Apply blur effect to the scene
        scene.setBlur(15);

        // Disable all level buttons and start button initially
        disableAllButtons();

        // Organize buttons into layout
        setupLayout();
    }

    // Disables all level buttons and the start button
    private void disableAllButtons() {
        this.bL1.setDisable(true);
        this.bL2.setDisable(true);
        this.bL3.setDisable(true);
        this.bL4.setDisable(true);
        this.bL5.setDisable(true);
        this.bL6.setDisable(true);
        this.bL7.setDisable(true);
        this.bL8.setDisable(true);
        this.bL9.setDisable(true);
        this.startButton.setDisable(true);
    }

    // Organizes UI components into layout
    private void setupLayout() {
        // Group level buttons into rows
        this.hBoxLine1.getChildren().addAll(bL1, bL2, bL3);
        this.hBoxLine2.getChildren().addAll(bL4, bL5, bL6);
        this.hBoxLine3.getChildren().addAll(bL7, bL8, bL9);
        this.vBoxLevel.getChildren().addAll(hBoxLine1, hBoxLine2, hBoxLine3);

        // Add start, back, and tutorial buttons
        this.hBoxLevelButton.getChildren().addAll(startButton, backButton, bTutorial);
        this.vBoxLevelSelect.getChildren().addAll(title, level, levelTime, vBoxLevel, hBoxLevelButton);

        // Set styles
        this.vBoxLevelSelect.setId("background");

        // Add the menu layout to the panel
        this.pLevelSelect.getChildren().add(vBoxLevelSelect);

        // Set layout sizes and positions
        this.pLevelSelect.setPrefSize(this.gui.getWidth(), this.gui.getHeight());
        this.backgroundLevelSelect.setPrefSize(this.gui.getWidth(), this.gui.getHeight());
        this.vBoxLevel.setSpacing(20);
        this.vBoxLevel.setAlignment(Pos.CENTER);

        // Set positions
        setLevelSelectPosition(vBoxLevelSelect);
        setLevelSelectPositionLR(hBoxLine1);
        setLevelSelectPositionLR(hBoxLine2);
        setLevelSelectPositionLR(hBoxLine3);
        setLevelButton(hBoxLevelButton);
    }

    // Positions the level selection box
    private void setLevelSelectPosition(VBox vBox) {
        vBox.setPrefSize(600, 500);
        vBox.setLayoutX((double) (gui.getWidth() - 600) / 2);
        vBox.setLayoutY((double) (gui.getHeight() - 500) / 2);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
    }

    // Positions rows of buttons
    private void setLevelSelectPositionLR(HBox hBox) {
        hBox.setPrefSize(300, 50);
        hBox.setLayoutX((double) (gui.getWidth() - 300) / 2);
        hBox.setLayoutY((double) (gui.getHeight() - 300) / 2);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
    }

    // Sets spacing and alignment for the level selection buttons
    private void setLevelButton(HBox hBox) {
        hBox.setPrefSize(200, 100);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
    }

    // Loads the background image for the menu
    private BackgroundImage getBackgroundImage() {
        Image image = new Image(new FileCheck().checkImage("LevelSelect", "file:src/resources/textures/background/Creepy Property Background.png"));

        return new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
    }

    // Highlights the selected button
    public void selectButton(Button buttonToSelect) {
        if (buttonToSelect != null) {
            resetButtonId(); // Reset previous selections

            if (buttonToSelect == bTutorial) {
                buttonToSelect.setId("tutorial-button_selected");
            } else {
                buttonToSelect.setId("selected-button");
            }
        } else {
            resetButtonId();
        }
    }

    // Resets all button styles
    private void resetButtonId() {
        this.bTutorial.setId("tutorial-button");
        this.bL1.setId("");
        this.bL2.setId("");
        this.bL3.setId("");
        this.bL4.setId("");
        this.bL5.setId("");
        this.bL6.setId("");
        this.bL7.setId("");
        this.bL8.setId("");
        this.bL9.setId("");
    }

    // Getter Methods
    public Label getLevelTime() { return this.levelTime; }
    public Label getLevel() { return this.level; }
    public Button get_bTutorial() { return this.bTutorial; }
    public Button get_bL1() { return this.bL1; }
    public Button get_bL2() { return this.bL2; }
    public Button get_bL3() { return this.bL3; }
    public Button get_bL4() { return this.bL4; }
    public Button get_bL5() { return this.bL5; }
    public Button get_bL6() { return this.bL6; }
    public Button get_bL7() { return this.bL7; }
    public Button get_bL8() { return this.bL8; }
    public Button get_bL9() { return this.bL9; }
    public Button getStartButton() { return startButton; }
    public Button getBackButton() { return backButton; }
    public Pane get_pLevelSelect() { return pLevelSelect; }
    public Pane getBackgroundLevelSelect() { return backgroundLevelSelect; }
}