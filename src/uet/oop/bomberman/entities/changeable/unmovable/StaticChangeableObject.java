package uet.oop.bomberman.entities.changeable.unmovable;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.changeable.ChangeableObject;

public abstract class StaticChangeableObject extends ChangeableObject {
    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public StaticChangeableObject(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }

    @Override
    public void update() {
        super.update();
    }
}
