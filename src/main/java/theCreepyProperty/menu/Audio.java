package theCreepyProperty.menu;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import theCreepyProperty.save.ReadWriteSettings;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;
import theCreepyProperty.scenes.GameScene;

public class Audio {
    private final GUI gui;
    private final GameScene gameScene;
    private final Settings settings;
    private ReadWriteSettings readWriteSettings;
    private SoundPlayer soundPlayer;

    private Pane pMenuAudio = new Pane();
    private VBox vBoxAudio = new VBox();
    private HBox hBoxAudioLR = new HBox();
    private VBox vBoxAudioL = new VBox();
    private VBox vBoxAudioR = new VBox();
    private Label label1;
    private Slider slider1;
    private Label label2;
    private Slider slider2;

    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button backButton;

    private boolean audio_on = false;
    private int master = 50;
    private int background = 50;

    private String soundButtonClick = "src/resources/sounds/button click.wav";

    public Audio(GUI gui, GameScene gameScene, Settings settings) {
        System.out.println(".............................Audio..............................");
        this.gui = gui;
        this.gameScene = gameScene;
        this.settings = settings;

        this.readWriteSettings = new ReadWriteSettings(this.gameScene.getGuiComponents());

        this.label1 = new Label();
        this.slider1 = new Slider();
        this.label2 = new Label();
        this.slider2 = new Slider();
        this.button3 = new Button();
        this.button4 = new Button();
        this.button5 = new Button();
        this.button6 = new Button();
        this.backButton = new Button();

        pMenuAudio.setVisible(false);

        // Button text
        this.backButton.setText("Back");
        this.label1.setText("Master: " + Math.round(this.master * 100f) / 100f);
        this.label2.setText("Background: " + Math.round(this.master * 100f) / 100f);
        this.button3.setText("");
        this.button4.setText("");
        this.button5.setText("");
        this.button6.setText("");

        this.button3.setDisable(true);
        this.button4.setDisable(true);
        this.button5.setDisable(true);
        this.button6.setDisable(true);

        // Label
        this.label1.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(this.label1, Priority.ALWAYS);
        this.label1.setAlignment(Pos.BOTTOM_LEFT);
        this.label1.setId("text");

        this.label2.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(this.label2, Priority.ALWAYS);
        this.label2.setAlignment(Pos.BOTTOM_LEFT);
        this.label2.setId("text");

        // Slider
        this.slider1.setValue(this.master);
        this.slider1.setMax(100);
        this.slider1.setMin(1);

        this.slider2.setValue(this.background);
        this.slider2.setMax(100);
        this.slider2.setMin(1);

        Platform.runLater(() -> {
            double percent1 = (slider1.getValue() - slider1.getMin()) / (slider1.getMax() - slider1.getMin());
            String style1 = String.format(
                    "-fx-background-color: linear-gradient(to right, #454242 0%%, #509974 %.0f%%, #454242 %.0f%%, #454242 100%%);",
                    percent1 * 100, percent1 * 100
            );
            slider1.lookup(".track").setStyle(style1);

            double percent2 = (slider2.getValue() - slider2.getMin()) / (slider2.getMax() - slider2.getMin());
            String style2 = String.format(
                    "-fx-background-color: linear-gradient(to right, #454242 0%%, #509974 %.0f%%, #454242 %.0f%%, #454242 100%%);",
                    percent2 * 100, percent2 * 100
            );
            slider2.lookup(".track").setStyle(style2);
        });

        // getChildren
        this.vBoxAudioL.getChildren().addAll(label1, slider1, button3, button5);
        this.vBoxAudioR.getChildren().addAll(label2, slider2, button4, button6);
        this.hBoxAudioLR.getChildren().addAll(vBoxAudioL, vBoxAudioR);
        this.vBoxAudio.getChildren().add(hBoxAudioLR);
        this.vBoxAudio.getChildren().add(backButton);
        this.vBoxAudio.setId("background");
        this.pMenuAudio.getChildren().add(vBoxAudio);

        // Set size and position
        this.pMenuAudio.setPrefSize(this.gui.getWidth(), this.gui.getHeight());
        this.hBoxAudioLR.setSpacing(20);
        this.hBoxAudioLR.setAlignment(Pos.CENTER);
        setMenuAudioPosition(vBoxAudio); //630
        setMenuAudioPositionLR(vBoxAudioL);
        setMenuAudioPositionLR(vBoxAudioR);

        // Event-Listener für Wertänderungen
        backButton.setOnAction(_ -> onBack());
        slider1.valueProperty().addListener((_, _, newVal) -> onSlider1(newVal));
        slider2.valueProperty().addListener((_, _, newVal) -> onSlider2(newVal));
        button3.setOnAction(_ -> onButton3());
        button4.setOnAction(_ -> onButton4());
        button5.setOnAction(_ -> onButton5());
        button6.setOnAction(_ -> onButton6());
    }

    public void triggerAudio(){
        if (!audio_on) {
            this.pMenuAudio.setVisible(true);
            this.audio_on = true;
        } else {
            this.pMenuAudio.setVisible(false);
            this.audio_on = false;
        }
    }

    private void onBack() {
        triggerAudio();
        this.settings.getMenuSettings().setVisible(true);

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster()); // Standard 2
        this.soundPlayer.play();

        System.out.println("[Audio]: Back ✔");
    }

    private void onSlider1(Number newVal) {
        // Style update
        double percent = (newVal.doubleValue() - slider1.getMin()) / (slider1.getMax() - slider1.getMin());
        String style = String.format(
                "-fx-background-color: linear-gradient(to right, #454242 0%%, #509974 %.0f%%, #454242 %.0f%%, #454242 100%%);",
                percent * 100, percent * 100
        );
        if (slider1.lookup(".track") != null) {
            slider1.lookup(".track").setStyle(style);
        }

        // sound anpassen
        this.master = newVal.intValue();

        readWriteSettings.updateSetting("master",this.master);

        this.label1.setText("Master: " + this.master + "%");

        //System.out.println("[Audio]: slider1 ✔");
    }

    private void onSlider2(Number newVal) {
        // Style update
        double percent = (newVal.doubleValue() - slider2.getMin()) / (slider2.getMax() - slider2.getMin());
        String style = String.format(
                "-fx-background-color: linear-gradient(to right, #454242 0%%, #509974 %.0f%%, #454242 %.0f%%, #454242 100%%);",
                percent * 100, percent * 100
        );
        if (slider2.lookup(".track") != null) {
            slider2.lookup(".track").setStyle(style);
        }

        // sound anpassen
        this.background = newVal.intValue();

        readWriteSettings.updateSetting("background",this.background);

        this.label2.setText("Background: " + this.background + "%");

    }

    private void onButton3() {
        System.out.println("[Audio]: button3 ✔");
    }

    private void onButton4() {
        System.out.println("[Audio]: button4 ✔");
    }

    private void onButton5() {
        System.out.println("[Audio]: button5 ✔");
    }

    private void onButton6() {
        System.out.println("[Audio]: button6 ✔");
    }

    // Set Position
    private void setMenuAudioPosition(VBox vBoxSettings) {
        vBoxSettings.setPrefSize(700, 500);
        vBoxSettings.setLayoutX((gui.getWidth() - (double) 700) / 2);
        vBoxSettings.setLayoutY((gui.getHeight() - (double) 500) / 2);
        vBoxSettings.setSpacing(10);
        vBoxSettings.setAlignment(Pos.CENTER);
    }

    private void setMenuAudioPositionLR(VBox vBoxSettings) {
        vBoxSettings.setPrefSize(300, 300);
        vBoxSettings.setLayoutX((gui.getWidth() - (double) 300) / 2);
        vBoxSettings.setLayoutY((gui.getHeight() - (double) 300) / 2);
        vBoxSettings.setSpacing(10);
        vBoxSettings.setAlignment(Pos.CENTER);
    }

    // Getter Methoden
    public Pane getMenuAudio() {
        return pMenuAudio;
    }

    public boolean getAudioOn() {
        return audio_on;
    }

    public int getMaster() {
        return master;
    }

    public int getBackground() {
        return background;
    }

    // Setter Methoden
    public void setMaster(int master) {
        this.master = master;
        this.slider1.setValue(master);
    }

    public void setBackground(int background) {
        this.background = background;
        this.slider2.setValue(background);
    }

    public void deleteAudio() {
        System.out.println("⚠ [Settings]: Alle Referenzen werden gelöscht...");

        if (this.pMenuAudio != null) {
            this.pMenuAudio = null;
        }
        if (this.vBoxAudio != null) {
            this.vBoxAudio = null;
        }
        if (this.hBoxAudioLR != null) {
            this.hBoxAudioLR = null;
        }
        if (this.vBoxAudioL != null) {
            this.vBoxAudioL = null;
        }
        if (this.vBoxAudioR != null) {
            this.vBoxAudioR = null;
        }

        // Labels
        if (this.label1 != null) {
            this.label1 = null;
        }
        if (this.slider1 != null) {
            this.slider1.valueProperty().removeListener((_, _, newVal) -> onSlider1(newVal));
            this.slider1 = null;
        }
        if (this.label2 != null) {
            this.label2 = null;
        }
        if (this.slider2 != null) {
            this.slider2.valueProperty().removeListener((_, _, newVal) -> onSlider2(newVal));
            this.slider2 = null;
        }

        // Buttons
        if (this.button3 != null) {
            this.button3 = null;
        }
        if (this.button4 != null) {
            this.button4 = null;
        }
        if (this.button5 != null) {
            this.button5 = null;
        }
        if (this.button6 != null) {
            this.button6 = null;
        }
        if (this.backButton != null) {
            this.backButton = null;
        }

        if (this.soundPlayer != null) {
            this.soundPlayer = null;
        }
        if (this.readWriteSettings != null) {
            this.readWriteSettings = null;
        }

        this.audio_on = false;
        this.master = 50;
        this.background = 50;

        // Sound-file-paths
        this.soundButtonClick = null;

        System.gc();
        System.out.println("✔ [Settings]: Speicherbereinigung durchgeführt.");
    }
}