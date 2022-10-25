package uet.oop.bomberman.entities.spriteEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.util.FrameCount;

public abstract class SpriteEntity extends Entity {
    protected static final int RENDER_TIME = 30;
    private boolean alive;
    private int timeToExplode;
    private final FrameCount frameCount;

    public boolean isAlive() {
        return alive;
    }

    protected void setAlive(boolean alive) {
        this.alive = alive;
        this.frameCount.reset();
    }

    public int getTimeToExplode() {
        return timeToExplode;
    }

    protected void setTimeToExplode(int timeToExplode) {
        this.timeToExplode = timeToExplode;
    }

    public FrameCount getFrameCount() {
        return frameCount;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public SpriteEntity(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
        this.frameCount = new FrameCount();
        this.setAlive(true);
        this.setTimeToExplode(RENDER_TIME);
    }

    /**
     * Update.
     */
    @Override
    public void update() {
        this.frameCount.update();
        if (!this.alive) explode();
        else selectSprite();
    }

    protected void explode() {
        if (this.getFrameCount().getFrame() > this.getTimeToExplode()) {
            this.setInvisible(true);
        } else {
            selectSpriteOnDead();
        }
    }

    @Override
    public void kill() {
        this.setAlive(false);
    }

    protected abstract void selectSpriteOnDead();

    /**
     * Select display sprite
     */
    protected abstract void selectSprite();
}