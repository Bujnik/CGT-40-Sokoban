package main.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level){
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<StorageLocation> storageLocations = new HashSet<>();
        Player player;

        //This is temp implementation
        int halfBoardCellSize = Model.BOARD_CELL_SIZE / 2;

        walls.add(new Wall(halfBoardCellSize, halfBoardCellSize));
        walls.add(new Wall(2 * halfBoardCellSize, halfBoardCellSize));
        walls.add(new Wall(3 * halfBoardCellSize, halfBoardCellSize));

        boxes.add(new Box(2 * halfBoardCellSize, 2 * halfBoardCellSize));
        storageLocations.add(new StorageLocation(3 * halfBoardCellSize, 3 * halfBoardCellSize));

        player = new Player(4 * halfBoardCellSize, 4 * halfBoardCellSize);

        return new GameObjects(walls, boxes, storageLocations, player);
    }
}
