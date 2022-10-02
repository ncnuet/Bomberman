package uet.oop.bomberman.entities.character.moving;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.untility.Direction;
import uet.oop.bomberman.untility.Distance;

public abstract class MovingCharacter extends Character {

    private static final int timeToExplode = 60;
    private boolean alive;
    private Direction direction;
    private boolean moving;
    private boolean exploded;

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
        this.getFrameCount().reset();
    }

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
        this.setAlive(true);
        this.setExploded(false);
        this.setMoving(false);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    protected void selectSprite() {
    }

    protected void explode() {
        if (this.getFrameCount().getFrame() > timeToExplode) {
            this.setExploded(true);
        }
    }

    /**
     * Move.
     */
    protected abstract void move(Distance distance);

    protected abstract void kill();
}
