package uet.oop.bomberman.entities.character.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.StaticCharacter;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.untility.Convert;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.untility.Point;

public final class Bomb extends StaticCharacter {
    /**
     * Sprite image.
     */
    private static final Image bomb_img = Sprite.bomb.getFxImage();
    private static final Image bomb_img_1 = Sprite.bomb_1.getFxImage();
    private static final Image bomb_img_2 = Sprite.bomb_2.getFxImage();

    public static final int TIME_EXPLODE = 120; // by frame unit

    private final Playground playground;
    private boolean exploded;

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public Bomb(int x, int y, Playground playground) {
        super(x, y, bomb_img_2);
        this.setExploded(false);
        this.playground = playground;
    }

    @Override
    public void update() {
        selectSprite();
        explode();
        super.update();
    }

    @Override
    protected void selectSprite() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 60,
                bomb_img_2, bomb_img_1, bomb_img));
    }

    private void explode() {
        if (this.getFrameCount().getFrame() > TIME_EXPLODE) {
            this.setExploded(true);
            Point coordinate = this.getCoordinate();
            this.playground.addFlame(new Flame(coordinate.x, coordinate.y, this.playground));
            Sound.bom_explode.start();
        }
    }
}
