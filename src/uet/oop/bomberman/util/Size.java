package uet.oop.bomberman.util;

import uet.oop.bomberman.graphics.Sprite;

public final class Size {
    private final int width;
    private final int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidthAsPixel() {
        return width * Sprite.SCALED_SIZE;
    }

    public int getHeightAsPixel() {
        return height * Sprite.SCALED_SIZE;
    }

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
