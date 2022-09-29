package uet.oop.bomberman.map;

import uet.oop.bomberman.untility.Size;

public abstract class MapLoader {
    private final String mapID;
    private final int time; // in second
    private final Size size;

    public String getMapID() {
        return mapID;
    }

    public int getTime() {
        return time;
    }

    public Size getSize() {
        return size;
    }

    public MapLoader(String mapID, int time, Size size) {
        this.mapID = mapID;
        this.time = time;
        this.size = size;
    }

    /**
     * Load play map
     */
    protected abstract void loadMap(String mapID);

    /**
     * Generate ultimate map from input
     */
    protected abstract void generateMap();
}
