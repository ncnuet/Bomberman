package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class PontanPink extends Enemy {
    //TODO:
    private static final Image img = Sprite.balloon_left1.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public PontanPink(int crdX, int crdY, Context context) {
        super(
                new Coordinate(crdX, crdY),
                img, context);

        this.setDeadSprite(img);
    }
}
