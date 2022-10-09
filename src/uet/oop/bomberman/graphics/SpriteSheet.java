package uet.oop.bomberman.graphics;

import uet.oop.bomberman.util.PathFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Save only one sprite sheet that can get a special sprite from here
 */
public final class SpriteSheet extends RawImage {
    // Constant class value
    public static final String PATH = "/textures/classic.png";
    public static final int SIZE = 256;
    public static final SpriteSheet sheet = new SpriteSheet(PATH, SIZE);

    // Object value
    private final String path;

    /**
     * Constructor.
     *
     * @param path path to sheet
     * @param size size of square sheet
     */
    public SpriteSheet(String path, int size) {
        super(size);
        this.path = path;

        load();
    }

    /**
     * Load resource and fill argb value to pixel array.
     */
    private void load() {
        try {
            URL url = PathFile.getURL(this.path);
            BufferedImage image = ImageIO.read(Objects.requireNonNull(url));

            int w = image.getWidth();
            int h = image.getHeight();

            // Fill 1-D pixel array with color code
            image.getRGB(0, 0, w, h, this.pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0); // Crash
        }
    }
}
