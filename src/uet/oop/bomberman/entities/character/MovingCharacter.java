package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.untility.Distance;

public abstract class MovingCharacter extends Character {
    public enum Direction {LEFT, RIGHT, UP, DOWN}

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
    public MovingCharacter(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
        this.setDirection(Direction.RIGHT);
        this.setMoving(false);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    protected void selectSprite() {
    }

    /**
     * Move.
     */
    protected abstract void move(Distance distance);
}
