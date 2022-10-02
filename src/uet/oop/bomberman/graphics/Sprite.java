package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

/**
 * Manage a special sprite.
 */
public final class Sprite implements SpritePack {
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    private final int size;
    private final int x, y;
    public int[] pixels;
    private final int realWidth;
    private final int realHeight;
    private final SpriteSheet sheet;

    /**
     * Constructor.
     *
     * @param size       size of sprite
     * @param crdX       tile coordinate in sheet
     * @param crdY       tile coordinate in sheet
     * @param sheet      sheet map image
     * @param realWidth  real width
     * @param realHeight real height
     */
    public Sprite(int size, int crdX, int crdY, SpriteSheet sheet, int realWidth, int realHeight) {
        this.size = size;
        this.x = crdX * this.size;
        this.y = crdY * this.size;
        this.sheet = sheet;
        this.realWidth = realWidth;
        this.realHeight = realHeight;
        this.pixels = new int[this.size * this.size];

        load();
    }

    /**
     * Copy image sheet area to sprite image area.
     */
    private void load() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.getSize()];
            }
        }
    }

    /**
     * Get pixel value at position (x,y).
     *
     * @param x posX
     * @param y posY
     * @return pixel value
     */
    private int getPixel(int x, int y) {
        return this.pixels[x + y * size];
    }

    /**
     * Get display pixel as Image Node.
     *
     * @return Image Node
     */
    public Image getFxImage() {
        final int scaleFactor = SCALED_SIZE / DEFAULT_SIZE;

        // Create empty frame with given size
        WritableImage writableImage = new WritableImage(
                this.size * scaleFactor,
                this.size * scaleFactor);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        // Copy image
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                // read pixel and remove background color
                final int pixel = this.getPixel(x, y);
                final int finalPixel = pixel == TRANSPARENT_COLOR ? 0 : pixel;

                // Fill pixel color
                for (int dy = 0; dy < scaleFactor; dy++) {
                    for (int dx = 0; dx < scaleFactor; dx++) {
                        pixelWriter.setArgb(
                                x * scaleFactor + dx,
                                y * scaleFactor + dy,
                                finalPixel);
                    }
                }

            }
        }

        return new ImageView(writableImage).getImage();
    }

    /**
     * choose suitable sprite according to time passed
     *
     * @param frameCount      time passed by frame unit
     * @param framesForSprite total time frame unit for all sprite
     * @param sprites         sprites
     * @return suitable sprite
     */
    @SafeVarargs
    public static <T> T selectSprite(int frameCount, int framesForSprite, T... sprites) {
        int numObject = sprites.length;
        int session = framesForSprite / numObject;
        return sprites[(frameCount % framesForSprite) / session];
    }
}
