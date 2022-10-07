package uet.oop.bomberman.entities.character.unmoving.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.untility.Point;
import uet.oop.bomberman.entities.character.unmoving.Explosion;
import uet.oop.bomberman.graphics.Sprite;

public final class Bomb extends Explosion {
    /**
     * Sprite image.
     */
    private static final Image bomb_img = Sprite.bomb.getFxImage();
    private static final Image bomb_img_1 = Sprite.bomb_1.getFxImage();
    private static final Image bomb_img_2 = Sprite.bomb_2.getFxImage();

    public static final int TIME_TO_EXPLODE = 120; // by frame unit

    private final Playground playground;

    public Bomb(int x, int y, Playground playground) {
        super(x, y, bomb_img_2);
        this.playground = playground;
    }

    @Override
    public void update() {
        selectSprite();
        super.update();
    }

    @Override
    protected void selectSprite() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 60,
                bomb_img_2, bomb_img_1, bomb_img));
    }

    /**
     * Explode
     */
    @Override
    public void explode() {
        if (this.getFrameCount().getFrame() > TIME_TO_EXPLODE) {
            this.setExploded(true);
            Point coordinate = this.getCoordinate();
            this.playground.addFlame(new Flame(coordinate.x, coordinate.y, this.playground));
            Sound.bom_explode.start();
        }
    }
}
