package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

/**
 * Grass entity.
 */
public final class Grass extends Tile {
    private static final Image grass = Sprite.grass.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public Grass(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), grass);
    }
}
