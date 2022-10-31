package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIHigh;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class Ovapi extends Enemy {
    private static final Image[] left_sprites = new Image[]{
            Sprite.ovapi_left1.getFxImage(),
            Sprite.ovapi_left2.getFxImage(),
            Sprite.ovapi_left3.getFxImage()};
    private static final Image[] right_sprites = new Image[]{
            Sprite.ovapi_right1.getFxImage(),
            Sprite.ovapi_right2.getFxImage(),
            Sprite.ovapi_right3.getFxImage()};

    private static final Image ovapi_dead = Sprite.ovapi_dead.getFxImage();

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
                left_sprites[0], context);

        this.setDeadSprite(ovapi_dead);
        this.setLeftSprites(left_sprites);
        this.setRightSprites(right_sprites);

        this.setSpeed(4);
        this.setAI(new AIHigh(this, context));
        this.setScore(30);
    }
}
