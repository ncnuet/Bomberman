package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class WallpassItem extends Item {
    private static final Image wall_pass_item = Sprite.powerup_wallpass.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public WallpassItem(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), wall_pass_item);
    }
}
