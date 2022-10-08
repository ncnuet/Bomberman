package uet.oop.bomberman.entities.changeable.unmovable;

import javafx.scene.image.Image;

public abstract class Explosion extends StaticChangeableObject implements Exploitable {
    private static final int TimeToExplode = 30; // in frame time;
    private boolean exploded;

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Explosion(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
        this.setInvisible(false);
        this.setExploded(false);
    }

    @Override
    public void explode() {
        if (this.getFrameCount().getFrame() > TimeToExplode) {
            this.setInvisible(true);
            this.setExploded(true);
        }
    }

    @Override
    public void update() {
        explode();
        super.update();
    }
}
