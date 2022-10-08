package uet.oop.bomberman.entities.changeable.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Pass extends Enermy{ //TODO: create this

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Pass(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }
}
