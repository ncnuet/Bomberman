package uet.oop.bomberman.entities.spriteEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameValue;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.bomber.Bomber;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.utils.Counter;

/**
 * Entity can change through sprites.
 * Wrapper class.
 */
public abstract class ChangeableSpriteEntity extends Entity {
    protected static final int RENDER_TIME = 30;
    private boolean alive; // if false, start the disappeared effect
    private int timeToExplode; // Time to execute effect
    private final Counter counter; // count frame by time, track for sprite selector

    public boolean isAlive() {
        return alive;
    }

    protected void setAlive(boolean alive) {
        this.alive = alive;
        this.counter.reset();
    }

    public int getTimeToExplode() {
        return timeToExplode;
    }

    protected void setTimeToExplode(int timeToExplode) {
        this.timeToExplode = timeToExplode;
    }

    public Counter getFrameCounter() {
        return counter;
    }

    public int getFrame() {
        return counter.getFrame();
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crd       coordinate
     * @param spriteImg image
     */
    public ChangeableSpriteEntity(Coordinate crd, Image spriteImg) {
        super(crd, spriteImg);

        // Default value
        this.counter = new Counter();
        this.setAlive(true);
        this.setTimeToExplode(RENDER_TIME);
    }

    /**
     * Update showing sprite on dead or on running
     */
    @Override
    public void update() {
        this.counter.update();
        if (this.isAlive()) {
            updateSpriteOnRunning();
        } else {
            updateOnExploded();
        }
    }

    /**
     * If kill() is triggered, this will be removed ease by showing dead effect.
     */
    @Override
    public void kill() {
        this.setAlive(false);
    }

    /**
     * When time up, the entity will be marked as removed,
     * otherwise, showing dead effect
     */
    protected void updateOnExploded() {
        if (this.getFrame() > this.getTimeToExplode()) {
            if (this instanceof Bomber) {
                if (GameValue.getHeart() > 0) {
                    this.setXAsPixel(32);
                    this.setYAsPixel(32);
                    this.setAlive(true);
                    ((Bomber) this).context.setOffsetX(0);
                    ((Bomber) this).context.setOffsetY(0);
                } else {
                    this.setRemoved(true);
                    ((Bomber) this).context.setEndGameScreen();
                }
            } else {
                this.setRemoved(true);
            }
        } else {
            updateSpriteOnDead();
        }
    }

    /**
     * Select sprite for displaying on dead state
     */
    protected abstract void updateSpriteOnDead();

    /**
     * Select sprite for displaying on running state
     */
    protected abstract void updateSpriteOnRunning();
}