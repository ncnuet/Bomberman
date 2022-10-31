package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIHigh;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class Pontan extends Enemy {
    private static final Image[] left_sprites = new Image[]{
            Sprite.pontan_left1.getFxImage(),
            Sprite.pontan_left2.getFxImage(),
            Sprite.pontan_left3.getFxImage()};
    private static final Image[] right_sprites = new Image[]{
            Sprite.pontan_right1.getFxImage(),
            Sprite.pontan_right2.getFxImage(),
            Sprite.pontan_right3.getFxImage()};

    private static final Image pontan_dead = Sprite.pontan_dead.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public Pontan(int crdX, int crdY, Context context) {
        super(
                new Coordinate(crdX, crdY),
                left_sprites[0], context);

        this.setDeadSprite(pontan_dead);
        this.setLeftSprites(left_sprites);
        this.setRightSprites(right_sprites);

        this.setSpeed(2);
        this.setAI(new AIHigh(this, context));
        this.setScore(200);
    }
}
