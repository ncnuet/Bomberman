package uet.oop.bomberman.entities.sprite.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Direction;
import uet.oop.bomberman.untility.Distance;

public final class Balloon extends Enermy {
    private static final Image balloom_left1 = Sprite.balloom_left1.getFxImage();
    private static final Image balloom_left2 = Sprite.balloom_left2.getFxImage();
    private static final Image balloom_left3 = Sprite.balloom_left3.getFxImage();

    private static final Image balloom_right1 = Sprite.balloom_right1.getFxImage();
    private static final Image balloom_right2 = Sprite.balloom_right2.getFxImage();
    private static final Image balloom_right3 = Sprite.balloom_right3.getFxImage();

    private static final Image balloon_dead = Sprite.balloom_dead.getFxImage();

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public Balloon(int crdX, int crdY) {
        super(crdX, crdY, balloom_left1, balloon_dead);
    }

    @Override
    protected void selectSprite() {
        if (this.getDirection() == Direction.RIGHT || this.getDirection() == Direction.DOWN) {
            this.setSpriteImg(Sprite.selectSprite(this.getFrameCount().getFrame(), 30,
                    balloom_left1, balloom_left2, balloom_left3));
        } else if (this.getDirection() == Direction.LEFT || this.getDirection() == Direction.UP) {
            this.setSpriteImg(Sprite.selectSprite(this.getFrameCount().getFrame(), 30,
                    balloom_right1, balloom_right2, balloom_right3));
        }
    }

    @Override
    protected void moveSprite(Distance distance) {

    }
}
