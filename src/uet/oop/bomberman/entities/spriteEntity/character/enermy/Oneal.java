package uet.oop.bomberman.entities.spriteEntity.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Direction;
import uet.oop.bomberman.util.Distance;

public final class Oneal extends Enemy {
    private static final Image oneal_left1 = Sprite.oneal_left1.getFxImage();
    private static final Image oneal_left2 = Sprite.oneal_left2.getFxImage();
    private static final Image oneal_left3 = Sprite.oneal_left3.getFxImage();

    private static final Image oneal_right1 = Sprite.oneal_right1.getFxImage();
    private static final Image oneal_right2 = Sprite.oneal_right2.getFxImage();
    private static final Image oneal_right3 = Sprite.oneal_right3.getFxImage();

    private static final Image oneal_dead = Sprite.oneal_dead.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public Oneal(int crdX, int crdY, Playground playground) {
        super(crdX, crdY, oneal_left1, oneal_dead, playground);
    }

    @Override
    protected void selectSprite() {
        if (this.getDirection() == Direction.RIGHT || this.getDirection() == Direction.DOWN) {
            this.setSpriteImg(Sprite.selectSprite(this.getFrameCount().getFrame(), RENDER_TIME,
                    oneal_left1, oneal_left2, oneal_left3));
        } else if (this.getDirection() == Direction.LEFT || this.getDirection() == Direction.UP) {
            this.setSpriteImg(Sprite.selectSprite(this.getFrameCount().getFrame(), RENDER_TIME,
                    oneal_right1, oneal_right2, oneal_right3));
        }
    }

    @Override
    protected void moveSprite(Distance distance) {

    }
}
