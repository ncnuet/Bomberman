package uet.oop.bomberman.entities.spriteEntity.obstacle.brick;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.obstacle.StaticSprite;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends StaticSprite {
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
     * @param xUnit position in predefined
     * @param yUnit position in predefined
     */
    public Brick(int xUnit, int yUnit) {
        super(xUnit, yUnit, brick);
        this.setTimeToExplode(30);
    }

    @Override
    protected void selectSpriteOnDead() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 30,
                brick_exploded, brick_exploded_1, brick_exploded_2));
    }
}
