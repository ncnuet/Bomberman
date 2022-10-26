package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class FlamepassItem extends Item {
    private static final Image flame_pass = Sprite.powerup_flamepass.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public FlamepassItem(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), flame_pass);
    }
}
