package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class BombpassItem extends Item {
    private static final Image bom_pass_item = Sprite.powerup_bombpass.getFxImage();

    /**
     * Constructor
     *
     * @param crdX coordinate X
     * @param crdY coordinate Y
     */
    public BombpassItem(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), bom_pass_item);
    }
}
