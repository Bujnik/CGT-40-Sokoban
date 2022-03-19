package main.model;

import main.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;

public class Model {
    public static final int BOARD_CELL_SIZE = 20;
    private EventListener eventListener;

    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("F:\\nauka\\Git projekty\\CGT-40-Sokoban\\src\\main\\res\\levels.txt"));

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

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        boolean isBlocked;
        isBlocked = checkWallCollision(player, direction);
        if (isBlocked) return;
        isBlocked = checkBoxCollisionAndMoveIfAvailable(direction);
        if (isBlocked) return;
        moveDirection(direction, player);
        checkCompletion();
    }

    //This method checks for collision with wall
    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        Set<Wall> walls = gameObjects.getWalls();
        for (Wall wall : walls) {
            if(gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }

    /**
     * This method checks for player movement
     * Checks for collision with box and checks if given box can move (no wall behind)
     * @return false if player can move (target is empty cell, storage location or can move the box)
     * If move can be conducted, new coordinates needs to be assigned
     */
    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction){
        Player player = gameObjects.getPlayer();
        for (Box box : gameObjects.getBoxes()) {
            if (player.isCollision(box, direction)){
                boolean hasCollided = checkWallCollision(box, direction);
                if (!hasCollided) {
                    //Box may collide also with another box
                    for (Box box1 : gameObjects.getBoxes()) {
                        if (box.isCollision(box1, direction)) return true;
                    }
                    //Move box in desired direction
                    moveDirection(direction, box);
                }
                return hasCollided;
            }
        }
        //If we do not collide with boxes, we can move (wall collision is checked in move() method)
        return false;
    }

    public void moveDirection(Direction direction, Movable movable) {
        switch (direction) {
            case UP:
                movable.move(0, -BOARD_CELL_SIZE);
                break;
            case DOWN:
                movable.move(0, BOARD_CELL_SIZE);
                break;
            case LEFT:
                movable.move(-BOARD_CELL_SIZE, 0);
                break;
            case RIGHT:
                movable.move(BOARD_CELL_SIZE, 0);
                break;
        }
    }

    //This method checks if all boxes are on storageLocation
    public void checkCompletion(){
        boolean allBoxesDone = true;
        Set<Box> boxes = gameObjects.getBoxes();
        Set<StorageLocation> storageLocations = gameObjects.getStorageLocations();
        for (Box box : boxes) {
            //Check if box matches any storage location,
            //if so - switch flag to true and remove storageLocation from the list)
            //else - switch flag to false
            for (StorageLocation storageLocation : storageLocations) {
                if (box.getX() == storageLocation.getX()
                        && box.getY() == storageLocation.getY()) {
                    allBoxesDone = true;
                    storageLocations.remove(storageLocation);
                    break;
                }
                else allBoxesDone = false;
            }
            //After passing the loop, if flag is marked as false, exit the loop
            //- box is not on any storageLocation field
            if (!allBoxesDone) break;
        }

        if (allBoxesDone) {
            eventListener.levelCompleted(currentLevel);
        }
    }
}
