package uet.oop.bomberman.entities.sprite.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Distance;

public final class PontanPink extends Enermy { //TODO:
    private static final Image img = Sprite.balloom_left1.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public PontanPink(int crdX, int crdY) {
        super(crdX, crdY, img, img);
    }

    @Override
    protected void selectSprite() {

    }

    @Override
    protected void moveSprite(Distance distance) {

    }
}
