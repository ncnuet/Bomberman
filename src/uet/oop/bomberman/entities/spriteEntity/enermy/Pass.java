package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AIHigh;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class Pass extends Enemy { //TODO: create this
    private static final Image[] left_sprites = new Image[]{
            Sprite.pass_lef1.getFxImage(),
            Sprite.pass_lef2.getFxImage(),
            Sprite.pass_lef3.getFxImage()};
    private static final Image[] right_sprites = new Image[]{
            Sprite.pass_right1.getFxImage(),
            Sprite.pass_right2.getFxImage(),
            Sprite.pass_right3.getFxImage()};

    private static final Image pass_dead = Sprite.pass_dead.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public Pass(int crdX, int crdY, Context context) {
        super(
                new Coordinate(crdX, crdY),
                left_sprites[0], context);

        this.setDeadSprite(pass_dead);
        this.setLeftSprites(left_sprites);
        this.setRightSprites(right_sprites);

        this.setSpeed(4);
        this.setAI(new AIHigh(this, context));
        this.setScore(800);
    }
}
