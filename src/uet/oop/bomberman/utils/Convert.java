package uet.oop.bomberman.utils;

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

    public static Point pixelToTile(Point point) {
        int x = point.getX() / Sprite.SCALED_SIZE;
        int y = point.getY() / Sprite.SCALED_SIZE;
        return new Point(x, y);
    }

    public static Point tileToPixel(Point point) {
        int x = point.getX() * Sprite.SCALED_SIZE;
        int y = point.getY() * Sprite.SCALED_SIZE;
        return new Point(x, y);
    }
}
