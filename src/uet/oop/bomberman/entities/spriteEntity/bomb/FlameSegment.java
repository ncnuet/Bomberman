package uet.oop.bomberman.entities.spriteEntity.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.utils.Direction;
import uet.oop.bomberman.graphics.Sprite;

public final class FlameSegment extends ExploitableEntity {
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
     */
    public FlameSegment(Coordinate crd, Direction direction, boolean bodySegment) {
        super(crd, hor);
        this.direction = direction;
        this.bodySegment = bodySegment;

        this.setAlive(false);
    }

    @Override
    protected void updateSpriteOnDead() {
        if (this.bodySegment) {
            switch (direction) {
                case LEFT, RIGHT -> this.setSprite(
                        Sprite.selectSprite(
                                this.getFrameCounter().getFrame(),
                                RENDER_TIME, hor, hor_1, hor_2));
                case UP, DOWN -> this.setSprite(
                        Sprite.selectSprite(
                                this.getFrameCounter().getFrame(),
                                RENDER_TIME, ver, ver_1, ver_2));
            }
        } else {
            switch (direction) {
                case LEFT -> this.setSprite(
                        Sprite.selectSprite(
                                this.getFrameCounter().getFrame(),
                                RENDER_TIME, hor_left, hor_left_1, hor_left_2));
                case RIGHT -> this.setSprite(
                        Sprite.selectSprite(
                                this.getFrameCounter().getFrame(),
                                RENDER_TIME, hor_right, hor_right_1, hor_right_2));
                case UP -> this.setSprite(
                        Sprite.selectSprite(
                                this.getFrameCounter().getFrame(),
                                RENDER_TIME, ver_first, ver_first_1, ver_first_2));
                case DOWN -> this.setSprite(
                        Sprite.selectSprite(
                                this.getFrameCounter().getFrame(),
                                RENDER_TIME, ver_last, ver_last_1, ver_last_2));
            }
        }
    }
}
