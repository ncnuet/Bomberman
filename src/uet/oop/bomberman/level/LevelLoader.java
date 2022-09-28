package uet.oop.bomberman.level;

import uet.oop.bomberman.untility.Size;

public abstract class LevelLoader {
    private final int levelNo;
    private final int time; // in second
    private final Size size;

    public int getLevelNo() {
        return levelNo;
    }

    public int getTime() {
        return time;
    }

    public Size getSize() {
        return size;
    }

    public LevelLoader(int levelNo, int time, Size size) {
        this.levelNo = levelNo;
        this.time = time;
        this.size = size;
    }
}
