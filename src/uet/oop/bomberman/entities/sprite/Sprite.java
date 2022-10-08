package uet.oop.bomberman.entities.sprite;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Sprite extends Entity {

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Sprite(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);

    }

    /**
     * Select display sprite
     */
    protected abstract void selectSprite();

}
 //TODO: move explode to this