package uet.oop.bomberman.utils;

import uet.oop.bomberman.graphics.Sprite;

public final class Size {
    private final int width, height; // tile size

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
