package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    private static final Image spriteImg = Sprite.brick.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param xUnit position in predefined
     * @param yUnit position in predefined
     */
    public Brick(int xUnit, int yUnit) {
        super(xUnit, yUnit, spriteImg);
    }

    @Override
    public void update() {

    }

    @Override
    protected boolean collide(Entity entity) {
        return false;
    }
}
