package uet.oop.bomberman.entities.sprite.obstacle.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.sprite.character.enermy.Enermy;
import uet.oop.bomberman.entities.sprite.obstacle.brick.Brick;
import uet.oop.bomberman.untility.Direction;
import uet.oop.bomberman.untility.Point;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public final class Flame extends Entity {
    /**
     * Sprite image.
     */
    private static final Image center = Sprite.bomb_exploded.getFxImage();
    private static final Image center_1 = Sprite.bomb_exploded1.getFxImage();
    private static final Image center_2 = Sprite.bomb_exploded2.getFxImage();

    private static final int[] directionX = new int[]{0, 1, 0, -1};
    private static final int[] directionY = new int[]{-1, 0, 1, 0};

    private final Playground playground;
    private final List<FlameSegment> segments;

    public Flame(int x, int y, Playground playground) {
        super(x, y, center);
        this.playground = playground;
        this.setAlive(false);
        this.segments = new ArrayList<>();
        this.generateSegment();
        this.setTimeToExplode(30);
    }

    @Override
    protected void selectSpriteOnDead() {
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
                        this.addFlameSegment(
                                findingPosition.x,
                                findingPosition.y,
                                direction,
                                j != BombermanGame.getFlameLength());
                    } else if (entity instanceof Brick) {
                        ((Brick) entity).setAlive(false);
                        break;
                    } else if (entity instanceof Bomb) {
                        ((Bomb) entity).setInvisible(true);
                    } else if (entity instanceof Enermy) {
                        ((Enermy) entity).kill();
                    } else break;
                } else {
                    // if position is invalid, skip to next side
                    break;
                }
            }
            index++;
        }
    }

    public void addFlameSegment(int x, int y, Direction direction, boolean isBody) {
        this.segments.add(new FlameSegment(x, y, direction, isBody));
    }

    public Entity getFlameSegment(int x, int y) {
        Point coordinate = this.getCoordinate();
        if (x == coordinate.x && y == coordinate.y) return this;
        for (FlameSegment segment : this.segments) {
            Point coordinateItem = segment.getCoordinate();
            if (x == coordinateItem.x && y == coordinateItem.y) return segment;
        }
        return null;
    }

    @Override
    public void update() {
        super.update();
        this.segments.forEach(Entity::update);
        this.segments.removeIf(FlameSegment::isInvisible);
    }

    @Override
    public void render(GraphicsContext graphicsContext, Playground playground) {
        super.render(graphicsContext, playground);
        this.segments.forEach(entity -> entity.render(graphicsContext, playground));
    }
}
