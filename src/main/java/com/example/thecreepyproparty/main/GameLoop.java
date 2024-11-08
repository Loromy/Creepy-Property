package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;

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
