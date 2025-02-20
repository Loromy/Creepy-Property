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
import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;

public class Audio {
    private final GUI gui;
    private final GameScene scene;
    private final Settings settings;
    private ReadWriteSettings readWriteSettings;

    private final Pane pMenuAudio = new Pane();
    private final VBox vBoxAudio = new VBox();
    private final HBox hBoxAudioLR = new HBox();
    private final VBox vBoxAudioL = new VBox();
    private final VBox vBoxAudioR = new VBox();
    private Label label1;
    private Slider slider1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button backButton;

    private boolean audio_on = false;
    private float master = 3;

    public Audio(GUI gui, GameScene scene, Settings settings) {
        this.gui = gui;
        this.scene = scene;
        this.settings = settings;

        this.readWriteSettings = new ReadWriteSettings(this.scene.getGuiComponents());

        this.label1 = new Label();
        this.slider1 = new Slider();
        this.button2 = new Button();
        this.button3 = new Button();
        this.button4 = new Button();
        this.button5 = new Button();
        this.button6 = new Button();
        this.backButton = new Button();

        pMenuAudio.setVisible(false);

        // Button text
        this.backButton.setText("Back");
        this.label1.setText("Master: " + Math.round(this.master * 100f) / 100f);
        this.button2.setText("");
        this.button3.setText("");
        this.button4.setText("");
        this.button5.setText("");
        this.button6.setText("");

        this.button2.setDisable(true);
        this.button3.setDisable(true);
        this.button4.setDisable(true);
        this.button5.setDisable(true);
        this.button6.setDisable(true);

        // Label
        this.label1.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(this.label1, Priority.ALWAYS);
        this.label1.setAlignment(Pos.BOTTOM_LEFT);
        this.label1.setId("text");

        // Slider
        this.slider1.setValue(this.master);
        this.slider1.setMax(6);
        this.slider1.setMin(1);

        Platform.runLater(() -> {
            double percent = (slider1.getValue() - slider1.getMin()) / (slider1.getMax() - slider1.getMin());
            String style = String.format(
                    "-fx-background-color: linear-gradient(to right, #454242 0%%, #509974 %.0f%%, #454242 %.0f%%, #454242 100%%);",
                    percent * 100, percent * 100
            );
            slider1.lookup(".track").setStyle(style);
        });

        // getChildren
        this.vBoxAudioL.getChildren().addAll(label1, slider1, button3, button5);
        this.vBoxAudioR.getChildren().addAll(button2, button4, button6);
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
        backButton.setOnAction(e -> onBack());
        slider1.valueProperty().addListener((obs, oldVal, newVal) -> onSlider1(oldVal, newVal));
        button2.setOnAction(e -> onButton2());
        button3.setOnAction(e -> onButton3());
        button4.setOnAction(e -> onButton4());
        button5.setOnAction(e -> onButton5());
        button6.setOnAction(e -> onButton6());
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
        System.out.println("[Audio]: Back ✔");
    }

    private void onSlider1(Number oldVal, Number newVal) {
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
        float value = newVal.floatValue();
        this.master = Math.round(value * 10000f) / 10000f;


        readWriteSettings.updateSetting("master",this.master);

        this.label1.setText("Master: " + Math.round(this.master * 100f) / 100f);

        //System.out.println("[Audio]: slider1 ✔");
    }

    private void onButton2() {
        System.out.println("[Audio]: button2 ✔");
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

    // Getter Methoden
    public Pane getMenuAudio() {
        return pMenuAudio;
    }

    public boolean getAudioOn() {
        return audio_on;
    }

    public Slider getSlider1() {
        return slider1;
    }

    public Button getButton2() {
        return button2;
    }

    public Button getButton3() {
        return button3;
    }

    public Button getButton4() {
        return button4;
    }

    public Button getButton5() {
        return button5;
    }

    public Button getButton6() {
        return button6;
    }

    public float getMaster() {
        return master;
    }

    public void setMaster(float master) {
        this.master = master;
        this.slider1.setValue(master);
    }

    // Setter Methoden
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
}
