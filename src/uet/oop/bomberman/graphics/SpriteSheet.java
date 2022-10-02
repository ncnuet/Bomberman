package uet.oop.bomberman.graphics;

import uet.oop.bomberman.untility.PathFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Save only one sprite sheet that can get a special sprite from there
 */
public final class SpriteSheet {
    // Constant class value
    public static final String PATH = "/textures/classic.png";
    public static final int SIZE = 256;
    public static final SpriteSheet tiles = new SpriteSheet(SpriteSheet.PATH, SpriteSheet.SIZE);

    // Object value
    private final String path;
    private final int size;
    public int[] pixels;

    public int getSize() {
        return size;
    }

    /**
     * Constructor.
     *
     * @param path path to sheet
     * @param size size of square sheet
     */
    public SpriteSheet(String path, int size) {
        this.path = path;
        this.size = size;
        this.pixels = new int[size * size];

        load();
    }

    /**
     * Load resource and fill rgb value to pixels array.
     */
    private void load() {
        try {
            URL url = PathFile.getURL(this.path);
            BufferedImage image = ImageIO.read(Objects.requireNonNull(url));

            int w = image.getWidth();
            int h = image.getHeight();

            // Fill pixel 1-D array with color code
            image.getRGB(0, 0, w, h, this.pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
