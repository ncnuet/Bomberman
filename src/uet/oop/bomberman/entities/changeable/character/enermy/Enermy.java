package uet.oop.bomberman.entities.changeable.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.changeable.character.MovingChangeableObject;
import uet.oop.bomberman.untility.Distance;

public abstract class Enermy extends MovingChangeableObject {
    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Enermy(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }

    @Override
    protected void moveSprite(Distance distance) {

    }

    @Override
    protected void kill() {

    }
}
