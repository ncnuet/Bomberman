package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class FlameItem extends Item {
    private static final Image flame_item = Sprite.powerup_flames.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public FlameItem(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), flame_item);
    }
}
