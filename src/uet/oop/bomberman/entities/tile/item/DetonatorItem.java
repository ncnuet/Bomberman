package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class DetonatorItem extends Item {
    private static final Image detonator_item = Sprite.powerup_detonator.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public DetonatorItem(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), detonator_item);
    }
}
