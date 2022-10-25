package uet.oop.bomberman.entities.spriteEntity.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Distance;

public final class Minvo extends Enemy {
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
     */
    public Minvo(int crdX, int crdY, Playground playground) {
        super(crdX, crdY, minvo_left1, minvo_left1, playground);
    }

    @Override
    protected void selectSprite() {

    }

    @Override
    protected void moveSprite(Distance distance) {

    }
}
