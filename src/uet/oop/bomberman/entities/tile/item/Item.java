package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.utils.Coordinate;

/**
 * Entity is an power-up item.
 * Wrapper Class.
 */
public abstract class Item extends Tile {
    public Item(Coordinate crd, Image spriteImg) {
        super(crd, spriteImg);
    }

    /**
     * If kill() triggered, is will be removed (disappeared) immediately.
     */
    @Override
    public void kill() {
        this.setRemoved(true);
    }
}
