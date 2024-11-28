package theCreepyProperty.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import theCreepyProperty.main.GUI;

public class Player extends Entity{
    private final GUI gui;
    private double controlSpeed = 0; // speed if strg pressed
//    public final int player_screen_X;
//    public final int player_screen_Y;
    public Image playerImage;
    public ImageView i_player = new ImageView();


    public Player(GUI gui)  {
        this.gui = gui;
        setDefaultValues();
        this.i_player.setX(entity_world_X);
        this.i_player.setY(entity_world_Y);

        this.i_player.setFitHeight(48);
        this.i_player.setFitWidth(48);

        createPlayerImage();
    }

    // set methode

    public void setPlayer_world_X(double player_world_X){
        this.entity_world_X = player_world_X;
        i_player.setX(player_world_X); // update player
    }

    public void setPlayer_world_Y(double player_world_Y){
        this.entity_world_Y = player_world_Y;
        i_player.setY(player_world_Y); // update player
    }

    public void setControlSpeed(double speed) {
        this.controlSpeed = speed;
    }

    public void setShiftSpeed() {
        this.controlSpeed = (this.speed - 2) * (-1);
    }

    public void setDirection(String direction) {
        this.direction = direction;
        this.draw();
    }

    public void setDefaultValues() {
        entity_world_X = 500;
        entity_world_Y = 50;
        speed = 3; // 3
        direction = "down";
        System.out.println("[System]: Player defaultValues set ✔"); //✖
    }

    public void createPlayerImage(){
        // Bilder für die Bewegungen nach oben
        up1 = new Image("file:src/resources/player/up_1.png");
        up2 = new Image("file:src/resources/player/up_2.png");
        up3 = new Image("file:src/resources/player/up_3.png");
        up4 = new Image("file:src/resources/player/up_4.png");

        // Bilder für die Bewegungen nach unten
        down1 = new Image("file:src/resources/player/down_1.png");
        down2 = new Image("file:src/resources/player/down_2.png");
        down3 = new Image("file:src/resources/player/down_3.png");
        down4 = new Image("file:src/resources/player/down_4.png");

        // Bilder für die Bewegungen nach links
        left1 = new Image("file:src/resources/player/left_1.png");
        left2 = new Image("file:src/resources/player/left_2.png");
        left3 = new Image("file:src/resources/player/left_3.png");
        left4 = new Image("file:src/resources/player/left_4.png");

        // Bilder für die Bewegungen nach rechts
        right1 = new Image("file:src/resources/player/right_1.png");
        right2 = new Image("file:src/resources/player/right_2.png");
        right3 = new Image("file:src/resources/player/right_3.png");
        right4 = new Image("file:src/resources/player/right_4.png");

        System.out.println("[System]: Player image created ✔"); //✖

    }

    // get methode
    public ImageView draw() {
        playerImage = null;

        switch (direction) {
            case "up":
                if (sprite_num == 1) {
                    playerImage = up1;
                } else if (sprite_num == 2) {
                    playerImage = up2;
                } else if (sprite_num == 3) {
                    playerImage = up3;
                } else if (sprite_num == 4) {
                    playerImage = up4;
                }
                break;

            case "down":
                if (sprite_num == 1) {
                    playerImage = down1;
                } else if (sprite_num == 2) {
                    playerImage = down2;
                } else if (sprite_num == 3) {
                    playerImage = down3;
                } else if (sprite_num == 4) {
                    playerImage = down4;
                }
                break;

            case "left":
                if (sprite_num == 1) {
                    playerImage = left1;
                } else if (sprite_num == 2) {
                    playerImage = left2;
                } else if (sprite_num == 3) {
                    playerImage = left3;
                } else if (sprite_num == 4) {
                    playerImage = left4;
                }
                break;

            case "right":
                if (sprite_num == 1) {
                    playerImage = right1;
                } else if (sprite_num == 2) {
                    playerImage = right2;
                } else if (sprite_num == 3) {
                    playerImage = right3;
                } else if (sprite_num == 4) {
                    playerImage = right4;
                }
                break;
        }

        this.i_player.setImage(playerImage);
        return i_player;

    }

    public double getPlayer_world_X(){
        return entity_world_X;
    }

    public double getPlayer_world_Y(){
        return entity_world_Y;
    }

    public double getSpeed(){
        return this.speed + this.controlSpeed;
    }
}