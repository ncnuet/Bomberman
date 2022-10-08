package uet.oop.bomberman.entities.changeable;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.untility.FrameCount;

public abstract class ChangeableObject extends Entity {

    private final FrameCount frameCount;

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
    public ChangeableObject(int crdX, int crdY, Image spriteImg) {
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
