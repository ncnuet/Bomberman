package uet.oop.bomberman.entities.spriteEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.utils.Direction;
import uet.oop.bomberman.utils.Distance;

public abstract class MovableEntity extends ChangeableSpriteEntity {
    private Direction direction; // direction of entity
    private boolean moving; // moving state
    protected final Context context;

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
     * @param crd       coordinate
     * @param spriteImg image
     */
    public MovableEntity(Coordinate crd, Image spriteImg, Context context) {
        super(crd, spriteImg);
        this.context = context;

        this.setMoving(false);
        this.setDirection(Direction.RIGHT);
    }

    @Override
    public void update() {
        super.update();
    }

    /**
     * Move.
     */
    protected abstract void moveEntity(Distance distance);

    /**
     * Check collide
     *
     * @param distance distance
     */
    protected abstract void collide(Distance distance);
}