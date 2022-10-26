package uet.oop.bomberman.entities.spriteEntity.bomb;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameValue;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.enermy.Enemy;
import uet.oop.bomberman.entities.spriteEntity.brick.Brick;
import uet.oop.bomberman.utils.Direction;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Point;

import java.util.ArrayList;
import java.util.List;

public final class Flame extends ExploitableEntity {
    /**
     * Sprite image.
     */
    private static final Image center = Sprite.bomb_exploded.getFxImage();
    private static final Image center_1 = Sprite.bomb_exploded1.getFxImage();
    private static final Image center_2 = Sprite.bomb_exploded2.getFxImage();

    private static final int[] DIR_X = new int[]{0, 1, 0, -1};
    private static final int[] DIR_Y = new int[]{-1, 0, 1, 0};

    private final Context context;
    private final List<FlameSegment> segments;

    /**
     * Constructor.
     *
     * @param crdX    x
     * @param crdY    y
     * @param context context
     */
    public Flame(int crdX, int crdY, Context context) {
        super(new Coordinate(crdX, crdY), center);
        this.context = context;

        this.setAlive(false);
        this.segments = new ArrayList<>();
        this.generateSegment();
    }

    @Override
    protected void updateSpriteOnDead() {
        this.setSprite(Sprite.selectSprite(
                this.getFrame(), RENDER_TIME,
                center, center_1, center_2));
    }

    /**
     * Generate flame segment at 4 sides of this cell
     */
    private void generateSegment() {
        int index = 0;
        Point curPos = this.getCoordinate().getTileCrd();
        int max_length = GameValue.getFlameLength();

        // Loop through 4 sides of current cell
        for (Direction direction : Direction.values()) {
            // Find entity in range of flameSegmentLength
            for (int length = 1; length <= max_length; length++) {
                Coordinate finPos = Coordinate.createCrdByTile(
                        curPos.getX() + DIR_X[index] * length,
                        curPos.getY() + DIR_Y[index] * length
                );

                // if findingPosition is valid
                if (finPos.isInMap(this.context)) {
                    Entity entity = this.context.getEntity(finPos, false);

                    if (entity instanceof Grass) {
                        this.add(finPos, direction, length != max_length);
                    } else if (
                            entity instanceof Brick ||
                                    entity instanceof Bomb ||
                                    entity instanceof Enemy) {
                        entity.kill();
                        break;
                    } else break;
                } else break;
            }
            index++;
        }
    }

    /**
     * Add new FlameSegment
     *
     * @param crd       coordinate
     * @param direction direction
     * @param isBody    is body of segment or head of segment
     */
    public void add(Coordinate crd, Direction direction, boolean isBody) {
        this.segments.add(new FlameSegment(crd, direction, isBody));
    }

    /**
     * Get flame or flameSegment at given position
     *
     * @param crd coordinate
     * @return Flame entity or null
     */
    public Entity get(Coordinate crd) {
        // Match center segment
        if (this.getCoordinate().equals(crd)) return this;

        // Match segments
        for (FlameSegment segment : this.segments) {
            if (segment.getCoordinate().equals(crd)) return segment;
        }

        return null;
    }

    @Override
    public void update() {
        super.update();

        // Clear child if they are marked as removed
        this.segments.forEach(Entity::update);
        this.segments.removeIf(FlameSegment::isRemoved);
    }

    @Override
    public void render(GraphicsContext graphicsContext, Context context) {
        super.render(graphicsContext, context);
        this.segments.forEach(entity -> entity.render(graphicsContext, context));
    }
}
