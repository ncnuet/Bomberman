package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIHigh;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class Ovapi extends Enemy {
    //TODO: create this
    private static final Image img = Sprite.ovapi.getFxImage();


    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public Ovapi(int crdX, int crdY, Context context) {
        super(
                new Coordinate(crdX, crdY),
                img, context);

        this.setDeadSprite(img);
        this.setSpeed(4);
        this.setAI(new AIHigh(this,context));
        this.setScore(30);
    }
}
