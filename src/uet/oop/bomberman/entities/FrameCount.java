package uet.oop.bomberman.entities;

public class FrameCount {
    private final int MAX_COUNT = 1200;
    private int frame = 0;

    public int getFrame() {
        return frame;
    }

    public void update() {
        if (this.frame++ > 1200) {
            this.frame = 0;
        }
    }
}
