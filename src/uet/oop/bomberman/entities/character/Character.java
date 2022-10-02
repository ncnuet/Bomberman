package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.untility.FrameCount;

public abstract class Character extends Entity {
    private boolean alive;
    private final FrameCount frameCount;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public FrameCount getFrameCount() {
        return frameCount;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Character(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
        this.frameCount = new FrameCount();
    }

    /**
     * Update count time
     */
    @Override
    public void update() {
        this.frameCount.update();
    }

    /**
     * Select display sprite
     */
    protected abstract void selectSprite();

}
