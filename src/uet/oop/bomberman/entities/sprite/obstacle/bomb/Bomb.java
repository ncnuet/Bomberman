package uet.oop.bomberman.entities.sprite.obstacle.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.untility.Point;
import uet.oop.bomberman.graphics.Sprite;

public final class Bomb extends Entity {
    /**
     * Sprite image.
     */
    private static final Image bomb_img = Sprite.bomb.getFxImage();
    private static final Image bomb_img_1 = Sprite.bomb_1.getFxImage();
    private static final Image bomb_img_2 = Sprite.bomb_2.getFxImage();

    private final Playground playground;

    public Bomb(int x, int y, Playground playground) {
        super(x, y, bomb_img_2);
        this.playground = playground;
        this.setAlive(false);
        this.setTimeToExplode(120);
    }

    /**
     * Explode
     */
    @Override
    public void explode() {
        if (this.getFrameCount().getFrame() > this.getTimeToExplode() || this.isInvisible()) {
            this.setInvisible(true);
            Point coordinate = this.getCoordinate();
            this.playground.addFlame(new Flame(coordinate.x, coordinate.y, this.playground));
            BombermanGame.addCurrentCapacity();
            Sound.bom_explode.start();
        } else {
            selectSpriteOnDead();
        }
    }

    @Override
    protected void selectSpriteOnDead() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 60,
                bomb_img_2, bomb_img_1, bomb_img));
    }
}
