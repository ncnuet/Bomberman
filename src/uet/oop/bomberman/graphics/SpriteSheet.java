package uet.oop.bomberman.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Tất cả sprite (hình ảnh game) được lưu trữ vào một ảnh duy nhất
 * Class này giúp lấy ra các sprite riêng từ 1 ảnh chung duy nhất đó
 */
public class SpriteSheet {
	public static String PATH  = "/textures/classic.png";
	public static int SIZE = 256;
	private final String _path;
	private final int _size;
	public int[] _pixels;
	public BufferedImage image;

	public static SpriteSheet tiles = new SpriteSheet(SpriteSheet.PATH, SpriteSheet.SIZE);

	public String get_path() {
		return _path;
	}

	public int get_size() {
		return _size;
	}

	/**
	 * Constructor.
	 *
	 * @param path path to sheet
	 * @param size size of square sheet
	 */
	public SpriteSheet(String path, int size) {
		this._path = path;
		this._size = size;
		this._pixels = new int[size * size];
		load();
	}

	/**
	 * Load resource and fill rgb value to pixels array.
	 */
	private void load() {
		try {
			URL url = SpriteSheet.class.getResource(_path);
			image = ImageIO.read(Objects.requireNonNull(url));

			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, this._pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
