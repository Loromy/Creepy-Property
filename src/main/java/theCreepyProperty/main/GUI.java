package theCreepyProperty.main;

import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import theCreepyProperty.entity.Player;
import theCreepyProperty.menu.Menu;

public class GUI {
//    private final int width = 1000;
//    private final int height = 600;

    private final Pane root = new Pane(); //main
    private final Pane pMenu = new Pane(); //Menu
    private final Pane pGame = new Pane(); //game stuff

    private final GamePanel gp;
    private final Menu menu;
//    private KeyHandler keyHandler = new KeyHandler(this, this.menu);
//    private final Player player = new Player(this, this.keyHandler, this.menu);
    //private Menu menu;
    private Player player;
    private KeyHandler keyHandler;
    private final GuiComponents guiComponents;

    public GUI(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.menu = new Menu(this.gp, this);;
        this.keyHandler = keyHandler;
        this.player = new Player(this.gp, this, this.keyHandler, this.menu);
        this.guiComponents = new GuiComponents(this.player, this.menu);
    }

    public void start(Stage primaryStage) {
        // Create the scene with the specified width and height values
        root.getChildren().addAll(pGame,pMenu);
        Scene scene = new Scene(root, gp.getScreen_width(), this.gp.getScreen_height());

        //Styles
        root.getStylesheets().add(("file:src/resources/style/style.css"));

        // Additional GUI components could be added here
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));
        pGame.getChildren().add(this.player.draw());
        pGame.getChildren().add(this.guiComponents.getL_speed());
        pMenu.getChildren().add(this.menu.getBackgroundMenu());
        pMenu.getChildren().add(this.menu.getpMenu());
        pMenu.getChildren().add(this.menu.getSettings().getMenuSettings());

        // Add the KeyHandler for keyboard input

        keyHandler.addKeyListener(scene);

        // Set the settings for the stage
        primaryStage.setTitle("The Creepy Proparty");
        primaryStage.getIcons().add(new Image("file:src/resources/player/down_1.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        // Close the application when the window is closed
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        // Show primaryStage
        primaryStage.show();
    }


    // Getter methods
//    public int getWidth() {
//        return width;
//    }
//
//    public int getHeight() {
//        return height;
//    }

    public GuiComponents getGuiComponents() {
        return guiComponents;
    }

    public Menu getMenu() {
        return menu;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public Player getPlayer() {
        return player;
    }

    // Setter methods
    public void setBlur(int strange){
        pGame.setEffect(new GaussianBlur(strange));
    }
}
