package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIHigh;
import uet.oop.bomberman.AI.AILow;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class Oneal extends Enemy {
    private static final Image[] left_sprites = new Image[]{
            Sprite.oneal_left1.getFxImage(),
            Sprite.oneal_left2.getFxImage(),
            Sprite.oneal_left3.getFxImage()};
    private static final Image[] right_sprites = new Image[]{
            Sprite.oneal_right1.getFxImage(),
            Sprite.oneal_right2.getFxImage(),
            Sprite.oneal_right3.getFxImage()};

    private static final Image oneal_dead = Sprite.oneal_dead.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public Oneal(int crdX, int crdY, Context context) {
        super(new Coordinate(crdX, crdY),
                left_sprites[0], context);

        this.setDeadSprite(oneal_dead);
        this.setLeftSprites(left_sprites);
        this.setRightSprites(right_sprites);

        this.setSpeed(3);
        this.setAI(new AIHigh(this, context));
        this.setScore(200);
    }
}
