package uet.oop.bomberman.map;

import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.EntityGroup;
import uet.oop.bomberman.entities.character.unmoving.brick.Brick;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.exceptions.LoadMapException;
import uet.oop.bomberman.exceptions.ParseMapException;
import uet.oop.bomberman.untility.PathFile;
import uet.oop.bomberman.untility.Size;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileMapLoader extends MapLoader {
    private final List<List<String>> map = new ArrayList<>();

    /**
     * Load a map.
     *
     * @param mapID      string map id
     * @param playground game board manager
     */
    public FileMapLoader(String mapID, Playground playground) throws LoadMapException {
        super(mapID);

        loadMap(mapID);
        this.generateMap(playground);
    }

    /**
     * This function used to test only
     */
    private void logFile() {
        System.out.println(this.getMapID());
        System.out.println(this.getTime());
        System.out.println(this.getSize().getWidth() + " " + this.getSize().getHeight());

        for (List<String> row : map) {
            for (String s : row) {
                System.out.print(s);
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
    private void readEntityText(String input) throws ParseMapException {
        try {
            String[] entityChar = input.split("");
            List<String> entitiesInRow = new ArrayList<>(Arrays.asList(entityChar));

            this.map.add(entitiesInRow);
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
            String row = "";

            readSpecs(buffer.readLine());
            do {
                row = buffer.readLine();
                if (!row.equals("")) readEntityText(row);
            } while (!row.equals(""));

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
     * @param playground game board manager
     */
    @Override
    protected void generateMap(Playground playground) {
        int width = this.getWidth();
        int height = this.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pos = x + y * width;
                String s = this.map.get(y).get(x);

                switch (s) {
                    case " " -> // Grass
                            playground.addEntity(new Grass(x, y));
                    case "#" -> // Wall
                            playground.addEntity(new Wall(x, y));
                    case "*" -> // Brick
                            playground.addEntity(new EntityGroup(x, y,
                                    new Grass(x, y),
                                    new Brick(x, y)
                            ));
                    default -> // Default Grass
                            playground.addEntity(new Grass(x, y));
                }
            }
        }
    }
}
