package uet.oop.bomberman.entities.spriteEntity.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.spriteEntity.SpriteEntity;
import uet.oop.bomberman.util.Direction;
import uet.oop.bomberman.util.Distance;

public abstract class Character extends SpriteEntity {
    private Direction direction;
    private boolean moving;
    protected final Playground playground;

    protected void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

    public Direction getDirection() {
        return direction;
    }

    protected void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Character(int crdX, int crdY, Image spriteImg, Playground playground) {
        super(crdX, crdY, spriteImg);
        this.setDirection(Direction.RIGHT);
        this.setMoving(false);
        this.playground = playground;
    }

    @Override
    public void update() {
        super.update();
    }

    /**
     * Move.
     */
    protected abstract void moveSprite(Distance distance);
}