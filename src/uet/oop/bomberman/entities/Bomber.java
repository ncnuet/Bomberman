package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Keyboard.Keyboard;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.tile.EntityType;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Convert;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.untility.Point;

public final class Bomber extends Character {
    private static final Image player_img_up = Sprite.player_up.getFxImage();
    private static final Image player_img_up_1 = Sprite.player_up_1.getFxImage();
    private static final Image player_img_up_2 = Sprite.player_up_2.getFxImage();
    private static final Image player_img_down = Sprite.player_down.getFxImage();
    private static final Image player_img_down_1 = Sprite.player_down_1.getFxImage();
    private static final Image player_img_down_2 = Sprite.player_down_2.getFxImage();
    private static final Image player_img_left = Sprite.player_left.getFxImage();
    private static final Image player_img_left_1 = Sprite.player_left_1.getFxImage();
    private static final Image player_img_left_2 = Sprite.player_left_2.getFxImage();
    private static final Image player_img_right = Sprite.player_right.getFxImage();
    private static final Image player_img_right_1 = Sprite.player_right_1.getFxImage();
    private static final Image player_img_right_2 = Sprite.player_right_2.getFxImage();

    private final Keyboard keyboard;
    private final Playground playground;


    public Bomber(int x, int y, Keyboard keyboard, Playground playground) {
        super(x, y, player_img_right);
        this.keyboard = keyboard;
        this.playground = playground;
    }

    @Override
    public void update() {
        Distance distance = listenEvent();
        move(distance);
        selectSprite();
        this.getFrameCount().update();
    }

    @Override
    protected boolean collide(Entity entity) {
        return false;
    }

    protected Distance listenEvent() {
        int x = (this.keyboard.left ? -1 : 0) + (this.keyboard.right ? 1 : 0);
        int y = (this.keyboard.up ? -1 : 0) + (this.keyboard.down ? 1 : 0);

        this.setMoving(x != 0 || y != 0);
        if (x < 0) setDirection(Direction.LEFT);
        if (x > 0) setDirection(Direction.RIGHT);
        if (y < 0) setDirection(Direction.UP);
        if (y > 0) setDirection(Direction.DOWN);

        return new Distance(
                x * BombermanGame.BomberSpeed,
                y * BombermanGame.BomberSpeed);
    }

    private EntityType detectEntity(Distance distance) {
        final int size = Sprite.SCALED_SIZE - 1;

        Entity entity = null;

        // Xét tọa độ mới từ 4 đỉnh
        for (int i = 0; i < 4; i++) {
            int x = this.getX() + distance.getX() + (i % 2) * size + (i % 2 == 0 ? 4 : -10);
            int y = this.getY() + distance.getY() + (i / 2) * size + (i / 2 == 0 ? 5 : -5);

            entity = this.playground.getEntity(
                    Convert.pixelToTile(new Point(x, y))
            );

            if (!(entity instanceof Grass)) return EntityType.TILE;
        }
        return EntityType.GRASS;
    }

    /**
     * Move sprite.
     *
     * @param distance predicated distance
     */
    @Override
    protected void move(Distance distance) {
        EntityType entityType = detectEntity(distance);

        switch (entityType) {
            case TILE:
                break;
            case ENERMY:
                // TODO: kill
                break;
            default: // Grass can move through
                this.setX(this.getX() + distance.getX());
                this.setY(this.getY() + distance.getY());

        }
    }

    /**
     * Select which sprite will display.
     */
    @Override
    protected void selectSprite() {
        switch (this.getDirection()) {
            case UP -> this.setSpriteImg(!this.isMoving()
                    ? player_img_up
                    : Sprite.selectSprite(this.getFrameCount().getFrame(), 20,
                    player_img_up_1, player_img_up_2
            ));
            case DOWN -> this.setSpriteImg(!this.isMoving()
                    ? player_img_down
                    : Sprite.selectSprite(this.getFrameCount().getFrame(), 20,
                    player_img_down_1, player_img_down_2
            ));
            case LEFT -> this.setSpriteImg(!this.isMoving()
                    ? player_img_left
                    : Sprite.selectSprite(this.getFrameCount().getFrame(), 20,
                    player_img_left_1, player_img_left_2
            ));
            case RIGHT -> this.setSpriteImg(!this.isMoving()
                    ? player_img_right
                    : Sprite.selectSprite(this.getFrameCount().getFrame(), 20,
                    player_img_right_1, player_img_right_2
            ));
        }
    }
}
