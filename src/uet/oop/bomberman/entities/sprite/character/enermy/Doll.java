package uet.oop.bomberman.entities.sprite.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Distance;

public final class Doll extends Enermy {
    private static final Image doll_left1 = Sprite.doll_left1.getFxImage();
    private static final Image doll_left2 = Sprite.doll_left2.getFxImage();
    private static final Image doll_left3 = Sprite.doll_left3.getFxImage();

    private static final Image doll_right1 = Sprite.doll_right1.getFxImage();
    private static final Image doll_right2 = Sprite.doll_right2.getFxImage();
    private static final Image doll_right3 = Sprite.doll_right3.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     */
    public Doll(int crdX, int crdY) {
        super(crdX, crdY, doll_left1, doll_left1);
    }

    @Override
    protected void selectSprite() {

    }

    @Override
    protected void moveSprite(Distance distance) {
    }
}
