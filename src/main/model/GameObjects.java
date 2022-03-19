package main.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Class designed to store GameObject objects
 */
public class GameObjects {
    private Set<Wall> walls;
    private Set<Box> boxes;
    private Set<StorageLocation> storageLocations;
    private Player player;

    public GameObjects(Set<Wall> walls, Set<Box> boxes, Set<StorageLocation> storageLocations, Player player) {
        this.walls = walls;
        this.boxes = boxes;
        this.storageLocations = storageLocations;
        this.player = player;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<StorageLocation> getStorageLocations() {
        return storageLocations;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<GameObject> getAll(){
        Set<GameObject> allObjects = new HashSet<>();
        allObjects.addAll(walls);
        allObjects.addAll(boxes);
        allObjects.addAll(storageLocations);
        allObjects.add(player);
        return allObjects;
    }
}
