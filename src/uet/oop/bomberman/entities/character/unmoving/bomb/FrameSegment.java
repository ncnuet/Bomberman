package uet.oop.bomberman.entities.character.unmoving.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.unmoving.Explosion;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Direction;

public final class FrameSegment extends Explosion {
    /**
     * Sprite image.
     */
    private static final Image ver_first = Sprite.explosion_vertical_top_last.getFxImage();
    private static final Image ver_first_1 = Sprite.explosion_vertical_top_last1.getFxImage();
    private static final Image ver_first_2 = Sprite.explosion_vertical_top_last2.getFxImage();
    private static final Image ver = Sprite.explosion_vertical.getFxImage();
    private static final Image ver_1 = Sprite.explosion_vertical1.getFxImage();
    private static final Image ver_2 = Sprite.explosion_vertical2.getFxImage();

    private static final Image ver_last = Sprite.explosion_vertical_down_last.getFxImage();
    private static final Image ver_last_1 = Sprite.explosion_vertical_down_last1.getFxImage();
    private static final Image ver_last_2 = Sprite.explosion_vertical_down_last2.getFxImage();

    private static final Image hor_left = Sprite.explosion_horizontal_left_last.getFxImage();
    private static final Image hor_left_1 = Sprite.explosion_horizontal_left_last1.getFxImage();
    private static final Image hor_left_2 = Sprite.explosion_horizontal_left_last2.getFxImage();

    private static final Image hor = Sprite.explosion_horizontal.getFxImage();
    private static final Image hor_1 = Sprite.explosion_horizontal1.getFxImage();
    private static final Image hor_2 = Sprite.explosion_horizontal2.getFxImage();

    private static final Image hor_right = Sprite.explosion_horizontal_right_last.getFxImage();
    private static final Image hor_right_1 = Sprite.explosion_horizontal_right_last1.getFxImage();
    private static final Image hor_right_2 = Sprite.explosion_horizontal_right_last2.getFxImage();
    private final Direction direction;
    private final boolean bodySegment;

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public FrameSegment(int crdX, int crdY, Direction direction, boolean bodySegment) {
        super(crdX, crdY, hor);
        this.direction = direction;
        this.bodySegment = bodySegment;
    }

    @Override
    public void update() {
        selectSprite();
        super.update();
    }

    @Override
    protected void selectSprite() {
        if (this.bodySegment) {
            switch (direction) {
                case LEFT, RIGHT -> this.setSpriteImg(
                        Sprite.selectSprite(
                                this.getFrameCount().getFrame(),
                                30, hor, hor_1, hor_2));
                case UP, DOWN -> this.setSpriteImg(
                        Sprite.selectSprite(
                                this.getFrameCount().getFrame(),
                                30, ver, ver_1, ver_2));
            }
        } else {
            switch (direction) {
                case LEFT -> this.setSpriteImg(
                        Sprite.selectSprite(
                                this.getFrameCount().getFrame(),
                                30, hor_left, hor_left_1, hor_left_2));
                case RIGHT -> this.setSpriteImg(
                        Sprite.selectSprite(
                                this.getFrameCount().getFrame(),
                                30, hor_right, hor_right_1, hor_right_2));
                case UP -> this.setSpriteImg(
                        Sprite.selectSprite(
                                this.getFrameCount().getFrame(),
                                30, ver_first, ver_first_1, ver_first_2));
                case DOWN -> this.setSpriteImg(
                        Sprite.selectSprite(
                                this.getFrameCount().getFrame(),
                                30, ver_last, ver_last_1, ver_last_2));
            }
        }
    }
}