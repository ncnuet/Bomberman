package uet.oop.bomberman.graphics;

import uet.oop.bomberman.utils.PathFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Save only one sprite SHEET that can get a special sprite from here
 */
public final class SpriteSheet extends Image {
    // Constant class value
    public static final String PATH = "/textures/classic.png";
    public static final int SIZE = 256;
    public static final SpriteSheet SHEET = new SpriteSheet(PATH, SIZE);

    // Object value
    private final String path;

    /**
     * Constructor.
     *
     * @param path path to SHEET
     * @param size size of square SHEET
     */
    private SpriteSheet(String path, int size) {
        super(size);
        this.path = path;

        this.load();
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
            System.exit(0); // If crash, break exit game.
        }
    }
}
