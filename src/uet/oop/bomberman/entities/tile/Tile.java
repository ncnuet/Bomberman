package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.utils.Coordinate;

/**
 * Entity can not be movable, just one sprite.
 * Wrapper class.
 */
public abstract class Tile extends Entity {
    /**
     * Constructor.
     * Create new Entity with position and it's image.
     *
     * @param crd       coordinate
     * @param spriteImg image
     */
    public Tile(Coordinate crd, Image spriteImg) {
        super(crd, spriteImg);
    }

    /* Because of tile is a static entity (not change sprite or moving),
    so it does not need to update
     */
    @Override
    public void update() {

    }

    /*
    Default tile entity is immortal
     */
    @Override
    public void kill() {

    }
}
