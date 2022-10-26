package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AILow;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

/**
 * Balloon enemy entity
 */
public final class Balloon extends Enemy {
    private static final Image[] left_sprites = new Image[]{
            Sprite.balloon_left1.getFxImage(),
            Sprite.balloon_left2.getFxImage(),
            Sprite.balloon_left3.getFxImage()};
    private static final Image[] right_sprites = new Image[]{
            Sprite.balloon_right1.getFxImage(),
            Sprite.balloon_right2.getFxImage(),
            Sprite.balloon_right3.getFxImage()};

    private static final Image balloon_dead = Sprite.balloon_dead.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX    position in predefined
     * @param crdY    position in predefined
     * @param context context
     */
    public Balloon(int crdX, int crdY, Context context) {
        super(
                new Coordinate(crdX, crdY),
                left_sprites[0], context);

        this.setDeadSprite(balloon_dead);
        this.setLeftSprites(left_sprites);
        this.setRightSprites(right_sprites);

        this.setSpeed(1);
        this.setAI(new AILow(this, context));
    }
}
