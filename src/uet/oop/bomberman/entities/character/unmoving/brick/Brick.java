package uet.oop.bomberman.entities.character.unmoving.brick;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.unmoving.Explosion;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Explosion {
    /**
     * Sprite image.
     */
    private static final Image brick = Sprite.brick.getFxImage();
    private static final Image brick_exploded = Sprite.brick_exploded.getFxImage();
    private static final Image brick_exploded_1 = Sprite.brick_exploded1.getFxImage();
    private static final Image brick_exploded_2 = Sprite.brick_exploded2.getFxImage();

    private boolean exploding;

    public boolean isExploding() {
        return exploding;
    }

    public void setExploding(boolean exploding) {
        this.exploding = exploding;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param xUnit position in predefined
     * @param yUnit position in predefined
     */
    public Brick(int xUnit, int yUnit) {
        super(xUnit, yUnit, brick);
        this.setExploding(false);
    }

    @Override
    public void update() {
        if (this.isExploding()) {
            selectSprite();
            super.update();
        }
    }

    @Override
    protected void selectSprite() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 30,
                brick_exploded, brick_exploded_1, brick_exploded_2));
    }
}
