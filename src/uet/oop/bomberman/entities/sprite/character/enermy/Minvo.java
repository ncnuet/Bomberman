package uet.oop.bomberman.entities.sprite.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Distance;

public final class Minvo extends Enermy {
    private static final Image minvo_left1 = Sprite.minvo_left1.getFxImage();
    private static final Image minvo_left2 = Sprite.minvo_left2.getFxImage();
    private static final Image minvo_left3 = Sprite.minvo_left3.getFxImage();

    private static final Image minvo_right1 = Sprite.minvo_right1.getFxImage();
    private static final Image minvo_right2 = Sprite.minvo_right2.getFxImage();
    private static final Image minvo_right3 = Sprite.minvo_right3.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Minvo(int crdX, int crdY) {
        super(crdX, crdY, minvo_left1, minvo_left1);
    }

    @Override
    protected void selectSprite() {

    }

    @Override
    protected void moveSprite(Distance distance) {

    }
}
