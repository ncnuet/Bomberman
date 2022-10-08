package uet.oop.bomberman.entities.changeable.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enermy{
    private static final Image oneal_left1 = Sprite.oneal_left1.getFxImage();
    private static final Image oneal_left2 = Sprite.oneal_left2.getFxImage();
    private static final Image oneal_left3 = Sprite.oneal_left3.getFxImage();

    private static final Image oneal_right1 = Sprite.oneal_right1.getFxImage();
    private static final Image oneal_right2 = Sprite.oneal_right2.getFxImage();
    private static final Image oneal_right3 = Sprite.oneal_right3.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Oneal(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }
}
