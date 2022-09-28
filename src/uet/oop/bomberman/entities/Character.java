package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.untility.Distance;

public abstract class Character extends Entity {
    public enum Direction {LEFT, RIGHT, UP, DOWN}

    private boolean moving;
    private Direction direction;
    private boolean alive;
    private final FrameCount frameCount;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public FrameCount getFrameCount() {
        return frameCount;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param xUnit     position in predefined
     * @param yUnit     position in predefined
     * @param spriteImg image
     */
    Character(int xUnit, int yUnit, Image spriteImg) {
        super(xUnit, yUnit, spriteImg);
        this.setDirection(Direction.RIGHT);
        this.frameCount = new FrameCount();
    }

    /**
     * Update.
     */
    @Override
    public void update() {
    }

    /**
     * Collide.
     */
    @Override
    public void collide() {
    }

    /**
     * Check if character can move to direction.
     *
     * @return boolean
     */
    protected abstract boolean canMove(Distance distance);

    /**
     * Move.
     */
    protected abstract void move(Distance distance);

    /**
     * return expected distance if button pressed
     *
     * @return expected distance
     */
    protected abstract Distance listenMoving();

    protected abstract void selectSprite();
}
