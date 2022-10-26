package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class Portal extends Item {
    private static final Image portal = Sprite.portal.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public Portal(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), portal);
    }
}
