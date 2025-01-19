package theCreepyProperty.main.Map;

import theCreepyProperty.blocks.Wall;
import theCreepyProperty.main.GUI;

public class MapCreate {

    private Wall wall;
    private GUI gui;
    private LevelData levelData;

    public void createMap(GUI gui, LevelData levelData){
//        System.out.println("Wände:");
//        for (LevelData.LevelDataWall wall : levelData.getWalls()) {
//            System.out.printf("LevelDataWall: x=%d, y=%d, width=%d, height=%d , texture=%s%n", wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight(), wall.getTexture());
//
//            this.wall = new LevelDataWall(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight(), wall.getTexture());
//
//        }

        //this.gui = gui;
        this.wall = gui.getWall();
        this.levelData = levelData;


        //this.wall = new LevelDataWall(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight(), wall.getTexture());
        //this.wall.setR_Wall(levelData.getWalls().get(i).getX());

        System.out.println("Walls levelData liste: " + this.levelData.getWalls().size()); //TODO warum 0???????????????????

        for (int i = 0; i < this.levelData.getWalls().size(); i++) {
            System.out.println("i: " + i);

            this.wall = new Wall(levelData.getWalls().get(i).getX(), levelData.getWalls().get(i).getY(), levelData.getWalls().get(i).getWidth(), levelData.getWalls().get(i).getHeight(), levelData.getWalls().get(i).getTexture());

            //
            // System.out.println(this.wall.getWallList().get(i));

            //this.gui.getpGame().getChildren().add(wall);//todo ractange in die GUI übergeben
        }

    }
}
