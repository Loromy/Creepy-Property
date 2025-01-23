package theCreepyProperty.Map;

import theCreepyProperty.blocks.Wall;
import theCreepyProperty.main.GUI;

public class MapCreate {

    private Wall wall;
    private GUI gui;
    private LevelData levelData;

    public void createMap(GUI gui, LevelData levelData){

        this.gui = gui;
        this.wall = gui.getWall();
        this.levelData = levelData;

        for (int i = 0; i < this.levelData.getWalls().size(); i++) {
            System.out.println("[Map Creator]: Wall id " + i);

            this.wall = new Wall(levelData.getWalls().get(i).getX(), levelData.getWalls().get(i).getY(), levelData.getWalls().get(i).getWidth(), levelData.getWalls().get(i).getHeight(), levelData.getWalls().get(i).getTexture());
            this.gui.pGameChildren(this.wall.getRWall());
        }
    }

    public Wall getWall() {
        return this.wall;
    }
}
