package uet.oop.bomberman.entities.changeable.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Kondaria extends Enermy {
    private static final Image kondaria_left1 = Sprite.kondoria_left1.getFxImage();
    private static final Image kondaria_left2 = Sprite.kondoria_left2.getFxImage();
    private static final Image kondaria_left3 = Sprite.kondoria_left3.getFxImage();

    private static final Image kondaria_right1 = Sprite.kondoria_right1.getFxImage();
    private static final Image kondaria_right2 = Sprite.kondoria_right2.getFxImage();
    private static final Image kondaria_right3 = Sprite.kondoria_right3.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Kondaria(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }
}
