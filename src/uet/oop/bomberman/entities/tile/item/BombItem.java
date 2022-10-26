package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class BombItem extends Item {
    private static final Image bomb_item = Sprite.powerup_bombs.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public BombItem(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), bomb_item);
    }
}
