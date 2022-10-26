package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class SpeedItem extends Item {
    private static final Image speed_item = Sprite.powerup_speed.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public SpeedItem(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), speed_item);
    }
}
