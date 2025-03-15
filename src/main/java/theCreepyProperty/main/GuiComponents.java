package theCreepyProperty.main;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.checker.ImageCheck;
import theCreepyProperty.entity.Player;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.scenes.LevelSelectScene;

public class GuiComponents {
    private final Player player;
    private final GUI gui;
    private final LevelSelectScene levelSelectScene;
    private final GameScene gameScene;

    private Label l_speed ;
    private Label l_sprint;
    private Label l_keys;
    private Label l_fps;
    private Label l_level;
    private Label l_time;
    private HBox hBox_keys;
    private HBox hBox_Level;
    private VBox vBox_anzeige;
    private VBox vBox_Time;
    private VBox vBox_sprint;
    private ImageView i_keys;

    private boolean anzeige_on = false;

    public GuiComponents(GUI gui, Player player, GameScene gameScene){
        System.out.println(".............................GuiComponents..............................");
        this.gui = gui;
        this.player = player;
        this.levelSelectScene = gui.getSelectScene();
        this.gameScene = gameScene;

        // HBox
        this.hBox_Level = new HBox();
        this.hBox_Level.setId("gui-components-background-mitte");
        this.hBox_Level.setStyle("-fx-alignment: center;");
        this.hBox_Level.setPrefWidth(300);
        this.hBox_Level.setLayoutX((double) gui.getWidth() / 2 - (this.hBox_Level.getPrefWidth() / 2));

        this.hBox_keys = new HBox();
        this.hBox_keys.setId("gui-components-background-keys");

        this.vBox_Time = new VBox();
        this.vBox_Time.setLayoutY(60);
        this.vBox_Time.setMinSize(130,20);
        this.vBox_Time.setId("gui-components-background-links");

        // VBox
        this.vBox_sprint = new VBox();
        this.vBox_sprint.setLayoutY(100);
        this.vBox_sprint.setId("gui-components-background-links");

        this.vBox_anzeige = new VBox();
        this.vBox_anzeige.setLayoutY(140);
        this.vBox_anzeige.setId("gui-components-background-links");
        this.vBox_anzeige.setVisible(false);

        // Label
        // Level
        if (this.levelSelectScene.getMapSelected() == 0) {
            this.l_level = new Label("Level Tutorial");
        } else {
            this.l_level = new Label("Level " + this.levelSelectScene.getMapSelected());
        }
        this.l_level.setStyle("-fx-font-size: 40px; -fx-alignment: center;");
        this.l_level.setId("gui-components");
        this.hBox_Level.getChildren().add(l_level);

        // Keys Display
        for (int i = 0; i < this.gameScene.getMapCreate().getItemList().size(); i++) {
            this.i_keys = new ImageView(new Image(new ImageCheck().checkImage("GuiComponents","file:src/resources/textures/items/Key_blank.png")));
            this.i_keys.setFitWidth(40);
            this.i_keys.setFitHeight(40);
            hBox_keys.getChildren().add(this.i_keys);
        }

        // Time
        this.l_time = new Label("Time: -- : -- : -- : --");
        this.l_time.setId("gui-components");
        this.vBox_Time.getChildren().add(l_time);

        // Sprint
        this.l_sprint = new Label("Sprint: ");
        this.l_sprint.setId("gui-components");
        this.vBox_sprint.getChildren().add(l_sprint);

        // Anzeige
        this.l_speed = new Label("Speed: " + player.getSpeed());
        this.l_speed.setId("gui-components");
        this.vBox_anzeige.getChildren().add(l_speed);

        this.l_fps = new Label("FPS: 0");
        this.l_fps.setId("gui-components");
        this.vBox_anzeige.getChildren().add(l_fps);

        this.l_keys = new Label("Keys: " + player.getKeyEingesammelt());
        this.l_keys.setId("gui-components");
        this.vBox_anzeige.getChildren().add(l_keys);
    }

    public void collectKey(int keysToCollected) {
        int collectedKeys = this.player.getKeyEingesammelt();

        for (int i = 0; i < keysToCollected;i++) {
            this.hBox_keys.getChildren().removeFirst();
        }

        for (int v = 0; v < collectedKeys ;v++) {
            this.i_keys = new ImageView(new Image(new ImageCheck().checkImage("GuiComponents","file:src/resources/textures/items/Key.png")));
            this.i_keys.setFitWidth(40);
            this.i_keys.setFitHeight(40);
            hBox_keys.getChildren().add(this.i_keys);
        }

        for (int n = 0; n < keysToCollected - collectedKeys ; n++) {
            this.i_keys = new ImageView(new Image(new ImageCheck().checkImage("GuiComponents","file:src/resources/textures/items/Key_blank.png")));
            this.i_keys.setFitWidth(40);
            this.i_keys.setFitHeight(40);
            hBox_keys.getChildren().add(this.i_keys);
        }
    }

    public void triggerAnzeigeRWSettings() {
        if (!anzeige_on) {
            this.vBox_anzeige.setVisible(true);
            gameScene.getMenu().getSettings().getB_Anzeige().setText("Anzeige [ON]");
            this.anzeige_on = true;
        } else {
            this.vBox_anzeige.setVisible(false);
            gameScene.getMenu().getSettings().getB_Anzeige().setText("Anzeige [OFF]");
            this.anzeige_on = false;
        }
    }

    public void triggerAnzeige(ReadWriteSettings readWriteSettings) {
        if (!anzeige_on) {
            this.vBox_anzeige.setVisible(true);
            gameScene.getMenu().getSettings().getB_Anzeige().setText("Anzeige [ON]");
            readWriteSettings.updateSetting("anzeige", 1);
            this.anzeige_on = true;
        } else {
            this.vBox_anzeige.setVisible(false);
            gameScene.getMenu().getSettings().getB_Anzeige().setText("Anzeige [OFF]");
            readWriteSettings.updateSetting("anzeige", 0);
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

    public Label getL_sprint() {
        return l_sprint;
    }

    public Label getL_keys() {
        return l_keys;
    }

    public Label getL_fps() {
        return  l_fps;
    }

    public Label getL_time() {
        return l_time;
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

    public VBox getvBox_Time() {
        return vBox_Time;
    }

    public VBox getvBox_sprint() {
        return vBox_sprint;
    }

    public boolean getAnzeige_on() {
        return anzeige_on;
    }

    public void setAnzeige_on(boolean value) {
        this.anzeige_on = value;
    }

    public void deleteGuiComponents() {
        System.out.println("⚠ [GuiComponents]: Alle Referenzen werden gelöscht...");

        // Entferne HBox, VBox und Label Objekte
        if (this.hBox_Level != null) {
            this.hBox_Level.getChildren().clear();
            this.hBox_Level = null;
        }

        if (this.hBox_keys != null) {
            this.hBox_keys.getChildren().clear();
            this.hBox_keys = null;
        }

        if (this.vBox_Time != null) {
            this.vBox_Time.getChildren().clear();
            this.vBox_Time = null;
        }

        if (this.vBox_sprint != null) {
            this.vBox_sprint.getChildren().clear();
            this.vBox_sprint = null;
        }

        if (this.vBox_anzeige != null) {
            this.vBox_anzeige.getChildren().clear();
            this.vBox_anzeige = null;
        }

        // Entferne alle Labels
        if (this.l_level != null) {
            this.l_level = null;
        }

        if (this.l_speed != null) {
            this.l_speed = null;
        }

        if (this.l_sprint != null) {
            this.l_sprint = null;
        }

        if (this.l_keys != null) {
            this.l_keys = null;
        }

        if (this.l_fps != null) {
            this.l_fps = null;
        }

        if (this.l_time != null) {
            this.l_time = null;
        }

        // Entferne ImageViews
        if (this.i_keys != null) {
            this.i_keys = null;
        }

        // Setze den Status für die Anzeige zurück
        this.anzeige_on = false;

        // Führe Garbage Collection aus
        System.gc();
        System.out.println("✔ [GuiComponents]: Speicherbereinigung durchgeführt.");
    }

}