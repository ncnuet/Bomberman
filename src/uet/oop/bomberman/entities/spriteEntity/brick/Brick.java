package uet.oop.bomberman.entities.spriteEntity.brick;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.spriteEntity.UnmovableEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public class Brick extends UnmovableEntity {
    /**
     * Sprite image.
     */
    private static final Image brick = Sprite.brick.getFxImage();
    private static final Image brick_exploded = Sprite.brick_exploded.getFxImage();
    private static final Image brick_exploded_1 = Sprite.brick_exploded1.getFxImage();
    private static final Image brick_exploded_2 = Sprite.brick_exploded2.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public Brick(int crdX, int crdY) {
        super(new Coordinate(crdX, crdY), brick);
    }

    @Override
    protected void updateSpriteOnDead() {
        this.setSprite(Sprite.selectSprite(
                this.getFrameCounter().getFrame(), RENDER_TIME,
                brick_exploded, brick_exploded_1, brick_exploded_2));
    }
}
