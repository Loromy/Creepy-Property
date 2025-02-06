package theCreepyProperty.main;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import theCreepyProperty.entity.Player;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.scenes.LevelSelectScene;

public class GuiComponents {
    private final Player player;
    private final GUI gui;
    private final LevelSelectScene levelSelectScene;
    private final GameScene gameScene;

    private final Label l_speed ;
    private final Label l_keys;
    private final Label l_fps;
    private final Label l_level;
    private HBox hBox_keys;
    private HBox hBox_Level;
    private VBox vBox_anzeige;
    private ImageView i_keys;

    private boolean anzeige_on = false;

    public GuiComponents(GUI gui, Player player, GameScene gameScene){
        this.gui = gui;
        this.player = player;
        this.levelSelectScene = gui.getSelectScene();
        this.gameScene = gameScene;

        // HBox
        this.hBox_Level = new HBox();
        this.hBox_Level.setLayoutX(((double) gui.getWidth() / 2) - 50);
        this.hBox_Level.setId("gui-components-background-mitte");

        this.hBox_keys = new HBox();
        this.hBox_keys.setId("gui-components-background-keys");

        // VBox
        this.vBox_anzeige = new VBox();
        this.vBox_anzeige.setLayoutY(60);
        this.vBox_anzeige.setId("gui-components-background-anzeige");
        this.vBox_anzeige.setVisible(false);

        // Label
        this.l_level = new Label("Level " + this.levelSelectScene.getMapSelected());
        this.l_level.setStyle("-fx-font-size: 40px; -fx-alignment: center;");
        this.l_level.setId("gui-components");
        this.hBox_Level.getChildren().add(getL_level());

        this.l_speed = new Label("Speed: " + player.getSpeed());
        this.l_speed.setId("gui-components");
        this.vBox_anzeige.getChildren().add(l_speed);

        this.l_fps = new Label("FPS: 0");
        this.l_fps.setId("gui-components");
        this.vBox_anzeige.getChildren().add(l_fps);

        this.l_keys = new Label("Keys: " + player.getKeyEingesammelt());
        this.l_keys.setId("gui-components");
        this.vBox_anzeige.getChildren().add(l_keys);

        // Keys Display
        for (int i = 0; i < this.gameScene.getMapCreate().getItemList().size(); i++) {
            this.i_keys = new ImageView(new Image("file:src/resources/textures/items/Key_blank.png"));
            this.i_keys.setFitWidth(40);
            this.i_keys.setFitHeight(40);
            hBox_keys.getChildren().add(this.i_keys);
        }
    }

    public void collectKey(int keysToCollected) {
        int collectedKeys = this.player.getKeyEingesammelt();

        for (int i = 0; i < keysToCollected;i++) {
            this.hBox_keys.getChildren().removeFirst();
        }

        for (int v = 0; v < collectedKeys ;v++) {
            this.i_keys = new ImageView(new Image("file:src/resources/textures/items/Key.png"));
            this.i_keys.setFitWidth(40);
            this.i_keys.setFitHeight(40);
            hBox_keys.getChildren().add(this.i_keys);
        }

        for (int n = 0; n < keysToCollected - collectedKeys ; n++) {
            this.i_keys = new ImageView(new Image("file:src/resources/textures/items/Key_blank.png"));
            this.i_keys.setFitWidth(40);
            this.i_keys.setFitHeight(40);
            hBox_keys.getChildren().add(this.i_keys);
        }
    }

    public void triggerAnzeige() {
        if (!anzeige_on) {
            this.vBox_anzeige.setVisible(true);
            this.gui.getGameScene().getMenu().getSettings().getButton4().setText("Anzeige [ON]");
            this.anzeige_on = true;
        } else {
            this.vBox_anzeige.setVisible(false);
            this.gui.getGameScene().getMenu().getSettings().getButton4().setText("Anzeige [OFF]");
            this.anzeige_on = false;
        }
    }

    public void updateFPS(double fps) {
        l_fps.setText("FPS: " + (int) fps);
    }

    public Label getL_level() {
        return l_level;
    }

    public Label getL_speed(){
        return l_speed;
    }

    public Label getL_keys() {
        return l_keys;
    }

    public Label getL_fps() {
        return  l_fps;
    }

    public HBox gethBox_keys() {
        return hBox_keys;
    }

    public HBox gethBox_Level() {
        return hBox_Level;
    }

    public VBox getvBox_anzeige() {
        return vBox_anzeige;
    }
}