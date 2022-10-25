package uet.oop.bomberman.util;

import uet.oop.bomberman.graphics.Sprite;

public class Convert {
    public static int DecibelToLinear(float db) {
        final float threshold = 20f;
        return (int) (Math.pow(10, db / threshold) * 100);
    }

    public static float LinearToDecibel(int linear) {
        final float threshold = 20f;
        return threshold * (float) Math.log10(linear / 100.0);
    }

    public static Point pixelToTile(Point pixel) {
        int x = pixel.x / Sprite.SCALED_SIZE;
        int y = pixel.y / Sprite.SCALED_SIZE;
        return new Point(x, y);
    }
}
