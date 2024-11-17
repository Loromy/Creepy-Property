package main;

public class GameLoop {
    private boolean running = true;


    public GameLoop(){

    }

    public void startGameLoop(){
        while (running) {
            System.out.println("[System]: gameLoop");
        }
    }
}
