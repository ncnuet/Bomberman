package uet.oop.bomberman.entities.spriteEntity.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameValue;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Point;

public final class Bomb extends ExploitableEntity {
    /**
     * Sprite image.
     */
    private static final Image bomb_img = Sprite.bomb.getFxImage();
    private static final Image bomb_img_1 = Sprite.bomb_1.getFxImage();
    private static final Image bomb_img_2 = Sprite.bomb_2.getFxImage();

    private static final int EXPLODE_TIME = 120;

    private final Context context;

    public Bomb(int x, int y, Context context) {
        super(new Coordinate(x, y), bomb_img_2);
        this.context = context;

        this.setAlive(false); // Active dead effect
        this.setTimeToExplode(EXPLODE_TIME);
    }

    @Override
    protected void updateSpriteOnDead() {
        this.setSprite(Sprite.selectSprite(
                this.getFrame(), EXPLODE_TIME / 4,
                bomb_img_2, bomb_img_1, bomb_img));
    }

    /**
     * Explode
     */
    @Override
    public void updateOnExploded() {
        if (this.getFrame() > this.getTimeToExplode() || this.isRemoved()) {
            this.setRemoved(true);
            this.context.addFlame(new Flame(
                    this.getCoordinate().getX(),
                    this.getCoordinate().getY(),
                    this.context));
            GameValue.addCurrentCapacity();
            Sound.bom_explode.start();
        } else {
            updateSpriteOnDead();
        }
    }
}
