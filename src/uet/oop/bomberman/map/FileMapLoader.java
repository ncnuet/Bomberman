package uet.oop.bomberman.map;

import uet.oop.bomberman.untility.PathFile;
import uet.oop.bomberman.untility.Size;

public class FileMapLoader extends MapLoader {

    public FileMapLoader(String mapID, int time, Size size) {
        super(mapID, time, size);
    }

    @Override
    protected void loadMap(String mapID) {
        final String path = PathFile.getPath("levels\\Level\\" + mapID + ".txt");

    }

    @Override
    protected void generateMap() {

    }
}
