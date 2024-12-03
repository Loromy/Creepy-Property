package theCreepyProperty.entity;

import theCreepyProperty.main.GUI;
import theCreepyProperty.main.GamePanel;
import theCreepyProperty.main.KeyHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import theCreepyProperty.menu.Menu;

import java.util.Objects;


public class Player extends Entity{
    private final GamePanel gp;
    private final GUI gui;
    private final KeyHandler keyHandler;
    private final Menu menu;
    private double controlSpeed = 0; // speed if strg pressed
    public Image playerImage;
    public ImageView i_player = new ImageView();


    public Player(GamePanel gp, GUI gui, KeyHandler keyHandler, Menu menu)  {
        this.gp = gp;
        this.gui = gui;
        this.keyHandler = keyHandler;
        this.menu = menu;
        setDefaultValues();
        this.i_player.setX(entity_world_X);
        this.i_player.setY(entity_world_Y);

        this.i_player.setFitHeight(entity_size_Y);
        this.i_player.setFitWidth(entity_size_X);

        createPlayerImage();

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

    public void setDefaultValues() {
        entity_size_X = 48;
        entity_size_Y = 48;
        entity_world_X = ((double) gp.getScreen_width() / 2) - (entity_size_X / 2);
        entity_world_Y = ((double) gp.getScreen_height() / 2) - ((entity_size_Y / 2 ) + 19);
        speed = 3; // 3
        direction = "down";
        System.out.println("[System]: Player defaultValues set ✔"); //✖
    }

    public void update() {

        if (keyHandler.wPressed || keyHandler.sPressed || keyHandler.aPressed || keyHandler.dPressed) {

            if (keyHandler.wPressed) {
                direction = "up";
            }
            else if (keyHandler.sPressed) {
                direction = "down";
            }
            else if (keyHandler.aPressed) {
                direction = "left";
            }
            else if (keyHandler.dPressed) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collision_on = false;
            //gp.Checker.checkTile(this);

            //CHECK OBJECT COLLISION
            //int objIndex = gp.Checker.check_object(this, true);
            //pickUpObject(objIndex);

            //IF COLLISION IS FALSE; PLAYER CAN MOVE
            handleMovement();

            if (this.keyHandler.escPressed) {
                this.keyHandler.escPressed = false;
                if (!menu.getSettings().getSettingOn()) {
                    this.menu.triggerMenu();
                } else {
                    this.menu.getSettings().triggerSettings();
                    this.menu.getpMenu().setVisible(true);
                }
            }

            sprite_counter++;
            if (sprite_counter > 13) {

                if (sprite_num == 1) {
                    sprite_num = 2;
                }
                else if (sprite_num == 2) {
                    sprite_num = 3;
                }
                else if (sprite_num == 3) {
                    sprite_num = 4;
                }
                else if (sprite_num == 4) {
                    sprite_num = 1;
                }

                sprite_counter = 0;
            }

        } else {
            if (Objects.equals(direction, "up")) {
                sprite_num = 1;
            }else if (Objects.equals(direction, "down")) {
                sprite_num = 1;
            }else if (Objects.equals(direction, "left")) {
                sprite_num = 1;
            }else if (Objects.equals(direction, "right")) {
                sprite_num = 1;
            }
        }
    }

    // Bewegung basierend auf Geschwindigkeits- und Bewegungsrichtung
    public void move(double dx, double dy) {
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        this.setPlayer_world_X(this.getPlayer_world_X() + dx * this.getSpeed());
        this.setPlayer_world_Y(this.getPlayer_world_Y() + dy * this.getSpeed());
    }

    public void handleMovement() {
        if (!this.menu.getMenu_on() && !collision_on) {
            double dx = 0;
            double dy = 0;

            if (this.keyHandler.wPressed) {
                dy -= 1;
                this.setDirection("up");
            }
            if (this.keyHandler.sPressed) {
                dy += 1;
                this.setDirection("down");
            }
            if (this.keyHandler.aPressed) {
                dx -= 1;
                this.setDirection("left");
            }
            if (this.keyHandler.dPressed) {
                dx += 1;
                this.setDirection("right");
            }

            if (this.keyHandler.ctrlPressed) {
                this.setControlSpeed(2);
                this.gui.getGuiComponents().getL_speed().setText("Speed: " + this.getSpeed());
            } else if (this.keyHandler.shiftPressed) {
                this.setShiftSpeed();
                this.gui.getGuiComponents().getL_speed().setText("Speed: " + this.getSpeed());
            } else {
                this.setControlSpeed(0);
                this.gui.getGuiComponents().getL_speed().setText("Speed: " + this.getSpeed());
            }

            if (dx != 0 || dy != 0) {
                move(dx, dy);
            }
            animation();
        }
    }

    public void animation(){
        if (this.keyHandler.wPressed || this.keyHandler.sPressed || this.keyHandler.aPressed || this.keyHandler.dPressed) {

            this.sprite_counter++;
            if (this.sprite_counter > 13) { // animation speed

                if (this.sprite_num == 1) {
                    this.sprite_num = 2;
                }
                else if (this.sprite_num == 2) {
                    this.sprite_num = 3;
                }
                else if (this.sprite_num == 3) {
                    this.sprite_num = 4;
                }
                else if (this.sprite_num == 4) {
                    this.sprite_num = 1;
                }

                this.sprite_counter = 0;
            }
        }
    }

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

    // get methode
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