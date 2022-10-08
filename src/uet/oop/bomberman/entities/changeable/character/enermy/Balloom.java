package uet.oop.bomberman.entities.changeable.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Enermy {
    private static final Image balloom_left1 = Sprite.balloom_left1.getFxImage();
    private static final Image balloom_left2 = Sprite.balloom_left2.getFxImage();
    private static final Image balloom_left3 = Sprite.balloom_left3.getFxImage();

    private static final Image balloom_right1 = Sprite.balloom_right1.getFxImage();
    private static final Image balloom_right2 = Sprite.balloom_right2.getFxImage();
    private static final Image balloom_right3 = Sprite.balloom_right3.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Balloom(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }
}
