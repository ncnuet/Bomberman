package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

/**
 * Wall entity.
 */
public final class Wall extends Tile {
    private static final Image wall = Sprite.wall.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public Wall(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), wall);
    }
}
