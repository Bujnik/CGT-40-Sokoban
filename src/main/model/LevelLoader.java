package main.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
        Player player = null;

        /*
          The levels should repeat in a loop. If the user has completed all 60 levels and gets to level 61,
          then the program takes the user back to level 1. And instead of level 62, the player is taken to level 2, etc.
         */
        int trueLevel = level == 60 ? 60 : level % 60;
        //Initial coordinates, variable c is the increment
        int c = Model.BOARD_CELL_SIZE;
        int x = c / 2;
        int y = c / 2;

        try(BufferedReader br = new BufferedReader(new FileReader(levels.toFile()))){
            String line;
            boolean isMap = false;
            int mapLevel = 0;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Maze:")) {
                    //Line starting with "Maze:" has format "Maze: <level>"
                    mapLevel = Integer.parseInt(line.split(" ")[1]);
                    continue;
                }
                if (mapLevel == trueLevel) {
                                    /* There are few lines of info data, not to be parsed
                    Variable isMap is used to secure not parsing the data.
                    Every part has two empty lines, we will use them to change values of isMap variable
                 */
                    if (line.length() == 0) {
                    /*
                    First walk through will change value of isMap to true, isEnd will end up as false --> continue
                    Second walk through will change value of isMap to false, isEnd will end up as true --> break
                     */
                        boolean isEnd = isMap;
                        isMap = !isMap;

                        if (isEnd) {
                            break;
                        }
                        else continue;
                    }
                    //After first walk through we will be sure that we are parsing the map
                    if (isMap) {
                        //Reset x value, new line
                        x = c /2 ;
                        for (Character character : line.toCharArray()) {
                            switch (character) {
                                case 'X':
                                    walls.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxes.add(new Box(x, y));
                                    break;
                                case '.':
                                    storageLocations.add(new StorageLocation(x, y));
                                    break;
                                case '&':
                                    storageLocations.add(new StorageLocation(x, y));
                                    boxes.add(new Box(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                                    break;
                            }
                            //Increment horizontal
                            x += c;
                        }
                        //Increment vertical
                        y += c;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(walls, boxes, storageLocations, player);
    }
}
