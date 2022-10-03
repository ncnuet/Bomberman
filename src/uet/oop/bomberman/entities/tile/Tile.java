package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Tile extends Entity {
    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param xUnit     position in predefined
     * @param yUnit     position in predefined
     * @param spriteImg image
     */
    private boolean invisible;

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public Tile(int xUnit, int yUnit, Image spriteImg) {
        super(xUnit, yUnit, spriteImg);
    }

    /**
     * Update.
     */
    @Override
    public void update() {
    }
}
