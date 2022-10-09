package uet.oop.bomberman.entities.spriteEntity.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Convert;
import uet.oop.bomberman.util.Direction;
import uet.oop.bomberman.util.Distance;
import uet.oop.bomberman.util.Point;

import java.util.ArrayList;
import java.util.List;

public final class Balloon extends Enemy {
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
    public Balloon(int crdX, int crdY, Playground playground) {
        super(crdX, crdY, balloom_left1, balloon_dead, playground);
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

    private List<Direction> detectEntity(Distance distance) {
        List<Direction> directionsCanMove = new ArrayList<>();
        final int size = Sprite.SCALED_SIZE - 1;

        for (int i = 0; i < 4; i++) {
            int x = this.getX() + distance.getX() + (i % 2) * size + (i % 2 == 0 ? 4 : -10);
            int y = this.getY() + distance.getY() + (i / 2) * size + (i / 2 == 0 ? 5 : -5);

            Entity entity = this.playground.getEntity(Convert.pixelToTile(new Point(x, y)));
        }
        return directionsCanMove;
    }

    private void move(Direction direction) {

    }

    @Override
    protected void moveSprite(Distance distance) {

    }
}
