package uet.oop.bomberman.untility;

public class FrameCount {
    private final int MAX_COUNT = 1200;
    private int frame = 0;

    public int getFrame() {
        return frame;
    }

    public void update() {
        if (this.frame++ > this.MAX_COUNT) {
            this.frame = 0;
        }
    }
}
