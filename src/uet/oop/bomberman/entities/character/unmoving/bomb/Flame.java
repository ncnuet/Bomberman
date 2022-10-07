package uet.oop.bomberman.entities.character.unmoving.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.unmoving.brick.Brick;
import uet.oop.bomberman.untility.Direction;
import uet.oop.bomberman.untility.Point;
import uet.oop.bomberman.entities.character.unmoving.Explosion;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;

public final class Flame extends Explosion {
    /**
     * Sprite image.
     */
    private static final Image center = Sprite.bomb_exploded.getFxImage();
    private static final Image center_1 = Sprite.bomb_exploded1.getFxImage();
    private static final Image center_2 = Sprite.bomb_exploded2.getFxImage();

    private static final int[] directionX = new int[]{0, 1, 0, -1};
    private static final int[] directionY = new int[]{-1, 0, 1, 0};

    private final Playground playground;

    public Flame(int x, int y, Playground playground) {
        super(x, y, center);

        this.playground = playground;
        this.generateSegment();
    }

    @Override
    public void update() {
        selectSprite();
        super.update();
    }

    @Override
    protected void selectSprite() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 30,
                center, center_1, center_2));
    }

    private void generateSegment() {
        int index = 0;
        Point currentPosition = this.getCoordinate();

        // Loop through 4 sides
        for (Direction direction : Direction.values()) {
            // find entity in range of flameSegmentLength
            for (int j = 1; j <= BombermanGame.getFlameLength(); j++) {
                Point findingPosition = new Point(
                        currentPosition.x + directionX[index] * j,
                        currentPosition.y + directionY[index] * j);

                // if findingPosition is valid
                if (findingPosition.x >= 0 && findingPosition.y >= 0) {
                    Entity entity = this.playground.getEntity(findingPosition);
                    if (entity instanceof Grass) {
                        this.playground.addFlame(new FrameSegment(
                                findingPosition.x,
                                findingPosition.y,
                                direction,
                                j != BombermanGame.getFlameLength()
                        ));
                    } else if (entity instanceof Brick) {
                        ((Brick) entity).setExploding(true);
                        break;
                    } else break;
                } else {
                    // if position is invalid, skip to next side
                    break;
                }
            }
            index++;
        }
    }
}
