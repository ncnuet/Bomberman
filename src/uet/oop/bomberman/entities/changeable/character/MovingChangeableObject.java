package uet.oop.bomberman.entities.changeable.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.untility.Direction;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.entities.changeable.ChangeableObject;

public abstract class MovingChangeableObject extends ChangeableObject {

    private static final int timeToExplode = 60;
    private boolean alive;
    private Direction direction;
    private boolean moving;

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
    public MovingChangeableObject(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
        this.setDirection(Direction.RIGHT);
        this.setAlive(true);
        this.setInvisible(false);
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
            this.setInvisible(true);
        }
    }

    /**
     * Move.
     */
    protected abstract void moveSprite(Distance distance);

    protected abstract void kill();
}
