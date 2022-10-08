package uet.oop.bomberman.entities.sprite.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.untility.Direction;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.entities.sprite.Sprite;

public abstract class Character extends Sprite {
    private Direction direction;
    private boolean moving;

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
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
    public Character(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
        this.setDirection(Direction.RIGHT);
        this.setMoving(false);
    }

    @Override
    public void update() {
        super.update();
    }

    /**
     * Move.
     */
    protected abstract void moveSprite(Distance distance);

    public abstract void kill();
}