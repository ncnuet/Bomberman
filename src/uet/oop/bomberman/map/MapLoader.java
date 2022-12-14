package uet.oop.bomberman.map;

import uet.oop.bomberman.Context;
import uet.oop.bomberman.exceptions.LoadMapException;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Size;

public abstract class MapLoader {
    private String mapID;
    private int time; // in second
    private Size size;

    public String getMapID() {
        return mapID;
    }

    public void setMapID(String mapID) {
        this.mapID = mapID;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getWidth() {
        return this.size.getWidth();
    }

    public int getHeight() {
        return this.size.getHeight();
    }

    public int getWidthByPixel() {
        return this.getWidth() * Sprite.SCALED_SIZE;
    }

    public int getHeightByPixel() {
        return this.getHeight() * Sprite.SCALED_SIZE;
    }

    /**
     * Constructor.
     *
     * @param mapID mapID
     */
    public MapLoader(String mapID) {
        this.mapID = mapID;
        this.time = 0;
        this.size = new Size(0, 0);
    }

    /**
     * Load map from file.
     *
     * @param mapID map ID
     */
    protected abstract void loadMap(String mapID) throws LoadMapException;

    /**
     * Generate Entity from loaded map.
     *
     * @param context game board manager
     */
    public abstract void generateMap(Context context);
}
