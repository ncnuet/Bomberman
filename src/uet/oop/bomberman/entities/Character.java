package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.untility.FrameCount;

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
    public Character(int xUnit, int yUnit, Image spriteImg) {
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
     * Move.
     */
    protected abstract void move(Distance distance);

    protected abstract void selectSprite();
}
