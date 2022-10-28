package uet.oop.bomberman.map;

import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.StackEntity;
import uet.oop.bomberman.entities.spriteEntity.CharacterType;
import uet.oop.bomberman.entities.spriteEntity.brick.Brick;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.item.*;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.exceptions.LoadMapException;
import uet.oop.bomberman.exceptions.ParseMapException;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.utils.PathFile;
import uet.oop.bomberman.utils.Size;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileMapLoader extends MapLoader {
    private final List<List<Character>> map = new ArrayList<>();

    /**
     * Load a map.
     *
     * @param mapID string map id
     */
    public FileMapLoader(String mapID) throws LoadMapException {
        super(mapID);
        loadMap(mapID);
    }

    /**
     * This function used to test only
     */
    private void logFile() {
        for (List<Character> row : map) {
            for (Character c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /**
     * Read specification of map.
     *
     * @param input String line input
     */
    private void readSpecs(String input) throws ParseMapException {
        try {
            String[] props = input.split(" ");
            this.setMapID(props[0]);
            this.setTime(Integer.parseInt(props[1]));
            this.setSize(new Size(
                    Integer.parseInt(props[3]),
                    Integer.parseInt(props[2])
            ));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ParseMapException();
        }
    }

    /**
     * Parse string input to special character corresponding to an entity.
     *
     * @param input String line input
     */
    private void readEntity(String input) throws ParseMapException {
        try {
            List<Character> entities
                    = Arrays.stream(input.split(""))
                    .map((String s) -> s.charAt(0)).toList();

            this.map.add(entities);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new ParseMapException();
        }
    }

    /**
     * Read all lines in map file and decode it.
     *
     * @param mapID map ID
     */
    @Override
    protected void loadMap(String mapID) throws LoadMapException {
        final String path = PathFile.getPath("/levels/" + mapID + ".txt");

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buffer = new BufferedReader(fileReader);
            String row;

            readSpecs(buffer.readLine());
            do {
                row = buffer.readLine();
                if (!row.equals("")) readEntity(row);
            } while (!row.equals(""));

            logFile();

        } catch (IOException | ParseMapException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new LoadMapException();
        }
    }

    /**
     * Load map with optional result log
     *
     * @param mapID String map id
     * @param isLog is log
     */
    protected void loadMap(String mapID, boolean isLog) throws LoadMapException {
        this.loadMap(mapID);
        if (isLog) {
            this.logFile();
        }
    }

    /**
     * Generate Entity from loaded map.
     *
     * @param context game board manager
     */
    @Override
    public void generateMap(Context context) {
        int width = this.getWidth();
        int height = this.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (this.map.get(y).get(x)) {
                    case ' ' -> // Grass
                            context.addEntity(new Grass(x, y));
                    case '#' -> // Wall
                            context.addEntity(new Wall(x, y));
                    case '*' -> // Brick
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new Brick(x, y)
                            ));
                    case 'x' -> // Portal
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new Portal(x, y),
                                    new Brick(x, y)
                            ));
                    /*
                     * MovableEntity
                     */
                    case 'p' -> {  // Bomber
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.BOMBER);
                    }
                    case '1' -> {
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.BALLOON);
                    }
                    case '2' -> {
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.ONEAL);
                    }
                    case '3' -> {
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.DOLL);
                    }
                    case '4' -> {
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.MINVO);
                    }
                    case '5' -> {
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.KONDRARIA);
                    }
                    case '6' -> {
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.OVAPI);
                    }
                    case '7' -> {
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.PASS);
                    }
                    case '8' -> {
                        context.addEntity(new Grass(x, y));
                        context.addCharacter(x, y, CharacterType.PONTAN);
                    }

                    /*
                     * Power-up Item
                     */
                    case 's' -> // SpeedItem
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new SpeedItem(x, y),
                                    new Brick(x, y)
                            ));
                    case 'b' -> // BombItem
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new BombItem(x, y),
                                    new Brick(x, y)
                            ));
                    case 'f' -> // FlameItem
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new FlameItem(x, y),
                                    new Brick(x, y)
                            ));
                    case 'h' -> // BombpassItem
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new BombpassItem(x, y),
                                    new Brick(x, y)
                            ));
                    case 'j' -> // FlamePassItem
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new FlamepassItem(x, y),
                                    new Brick(x, y)
                            ));
                    case 'k' -> // WallPassItem
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new WallpassItem(x, y),
                                    new Brick(x, y)
                            ));
                    case 'd' -> // DetonatorItem
                            context.addEntity(new StackEntity(
                                    new Coordinate(x, y),
                                    new Grass(x, y),
                                    new DetonatorItem(x, y),
                                    new Brick(x, y)
                            ));
                    default -> // Default Grass
                            context.addEntity(new Grass(x, y));
                }
            }
        }
    }
}
