package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

/**
 * Entity can not be movable, changeable.
 */
public abstract class Tile extends Entity {
    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param xUnit     position in predefined
     * @param yUnit     position in predefined
     * @param spriteImg image
     */
    public Tile(int xUnit, int yUnit, Image spriteImg) {
        super(xUnit, yUnit, spriteImg);
    }

    @Override
    public void kill() {
        this.setInvisible(true);
    }

    @Override
    public void update() {

    }
}
