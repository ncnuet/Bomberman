package uet.oop.bomberman.graphics;

public abstract class RawImage {
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    protected final int size;
    protected int[] pixels; // save all value argb pixel. 2D matrix value to 1D

    /**
     * Get pixel value at position (x,y).
     *
     * @param x posX
     * @param y posY
     * @return pixel value
     */
    protected int getPixel(int x, int y) {
        return this.pixels[x + y * size];
    }

    /**
     * Set pixel value at position (x,y).
     *
     * @param x     posX
     * @param y     posY
     * @param value pixel value
     */
    protected void setPixel(int x, int y, int value) {
        this.pixels[x + y * size] = value == TRANSPARENT_COLOR ? 0 : value;
    }

    /**
     * Constructor.
     *
     * @param size size of image
     */
    public RawImage(int size) {
        this.size = size;
        this.pixels = new int[this.size * this.size];
    }
}
