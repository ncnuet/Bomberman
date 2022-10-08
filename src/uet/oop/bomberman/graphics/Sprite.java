package uet.oop.bomberman.graphics;

import javafx.scene.image.*;

/**
 * Manage a special sprite.
 */
public final class Sprite extends RawImage implements SpritePack {
    private final int x, y;
    private final SpriteSheet sheet;

    /**
     * Constructor.
     *
     * @param size  size of sprite
     * @param crdX  tile coordinate in sheet
     * @param crdY  tile coordinate in sheet
     * @param sheet sheet map image
     */
    public Sprite(int size, int crdX, int crdY, SpriteSheet sheet) {
        super(size);
        this.x = crdX * this.size; // convert from coordinate to pixel position
        this.y = crdY * this.size; // convert from coordinate to pixel position
        this.sheet = sheet;

        load();
    }

    /**
     * Constructor.
     *
     * @param size size of sprite
     * @param crdX tile coordinate in sheet
     * @param crdY tile coordinate in sheet
     */
    public Sprite(int size, int crdX, int crdY) {
        super(size);
        this.x = crdX * this.size; // convert from coordinate to pixel position
        this.y = crdY * this.size; // convert from coordinate to pixel position
        this.sheet = SpriteSheet.sheet;

        load();
    }

    /**
     * Constructor.
     *
     * @param crdX tile coordinate in sheet
     * @param crdY tile coordinate in sheet
     */
    public Sprite(int crdX, int crdY) {
        super(DEFAULT_SIZE);
        this.x = crdX * this.size; // convert from coordinate to pixel position
        this.y = crdY * this.size; // convert from coordinate to pixel position
        this.sheet = SpriteSheet.sheet;

        load();
    }

    /**
     * Copy image sheet area to sprite image area.
     */
    private void load() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                this.setPixel(x, y, sheet.getPixel(x + this.x, y + this.y));
            }
        }
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
                final int pixel = this.getPixel(x, y);

                // Fill pixel color
                for (int dy = 0; dy < scaleFactor; dy++) {
                    for (int dx = 0; dx < scaleFactor; dx++) {
                        pixelWriter.setArgb(
                                x * scaleFactor + dx,
                                y * scaleFactor + dy,
                                pixel);
                    }
                }
            }
        }

        return new ImageView(writableImage).getImage();
    }

    /**
     * Choose suitable sprite according to time passed
     *
     * @param frameCount            time passed by frame unit
     * @param numberFramesForSprite total time frame unit for all sprite
     * @param sprites               sprites
     * @return suitable sprite
     */
    @SafeVarargs
    public static <T> T selectSprite(int frameCount, int numberFramesForSprite, T... sprites) {
        int numObject = sprites.length;

        // each frame display in the same span of time
        int session = numberFramesForSprite / numObject;

        if (frameCount == numberFramesForSprite) return sprites[numObject - 1];
        return sprites[(frameCount % numberFramesForSprite) / session];
    }
}
