package main.model;

import main.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int BOARD_CELL_SIZE = 20;
    private EventListener eventListener;

    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("F:\\nauka\\CodeGymTasks\\4.JavaCollections\\src\\com\\codegym\\task\\task34\\task3410\\res\\levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects(){
        return gameObjects;
    }

    public void restartLevel(int level){
        currentLevel = level;
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel(){
        currentLevel++;
        restartLevel(currentLevel);
    }
}
