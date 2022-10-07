package uet.oop.bomberman.entities.character.moving;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.unmoving.Explosion;
import uet.oop.bomberman.entities.character.unmoving.bomb.Bomb;
import uet.oop.bomberman.entities.character.unmoving.brick.Brick;
import uet.oop.bomberman.keyboard.KeyControl;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.entities.tile.Item.*;
import uet.oop.bomberman.untility.EntityType;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Item.Portal;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Convert;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.untility.Point;

public final class Bomber extends MovingCharacter {

    /**
     * Sprite image.
     */
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
    private static final Image player_dead = Sprite.player_dead1.getFxImage();
    private static final Image player_dead_1 = Sprite.player_dead2.getFxImage();
    private static final Image player_dead_2 = Sprite.player_dead3.getFxImage();

    /**
     * Main Class
     */
    private static final int MIN_TIME_BETWEEN_PUT_BOMB = 20;
    private final KeyControl keyboard;
    private final Playground playground;
    private int timeBetweenPutBomb; // by frame unit
    private Bomb myLatestBomb; // last set bomb

    public Bomber(int x, int y, KeyControl keyboard, Playground playground) {
        super(x, y, player_img_right);
        this.keyboard = keyboard;
        this.playground = playground;
    }

    @Override
    public void update() {
        // Determine entity and move
        if (this.isAlive()) {
            Distance distance = keyboard.getDistance();
            setMoving(!distance.isZero());
            setDirection(keyboard.getDirection());
            collide(distance);
        } else {
            explode();
        }

        // Listen press space key to set bomb
        listenSetBomb();
        this.timeBetweenPutBomb++;

        // Display
        selectSprite();
        super.update();
    }

    @Override
    protected void kill() {
        this.setAlive(false);
    }


    //-----------------
    // Bombing handler
    // ----------------
    private void listenSetBomb() {
        if (this.keyboard.isSpacePressed()) {
            setBomb();
        }
    }

    private void setBomb() {
        Point position = Convert.pixelToTile(new Point(
                this.getX() + Sprite.SCALED_SIZE / 2,
                this.getY() + Sprite.SCALED_SIZE / 2
        ));
        Entity entity = this.playground.getEntity(position);

        if (entity instanceof Grass && this.timeBetweenPutBomb > MIN_TIME_BETWEEN_PUT_BOMB) {
            placeBomb(position);
            this.timeBetweenPutBomb = 0;
        }
    }

    private void placeBomb(Point position) {
        Bomb bomb = new Bomb(position.x, position.y, this.playground);
        this.playground.addBomb(bomb);
        this.myLatestBomb = bomb;
        Sound.bom_set.start();
    }

    //-----------------
    // Moving handler
    // ----------------

    private void collideItem(Entity entity) {
        ((Item) entity).setInvisible(true);
        Sound.cryst_up.start();
    }

    private EntityType detectItem(Entity entity) {
        collideItem(entity);

        if (entity instanceof Portal) return EntityType.PORTAL;
        if (entity instanceof BombItem) return EntityType.ITEM_BOMB;
        if (entity instanceof BombpassItem) return EntityType.ITEM_BOMB_BYPASS;
        if (entity instanceof FlameItem) return EntityType.ITEM_FLAME;
        if (entity instanceof FlamepassItem) return EntityType.ITEM_FLAME_BYPASS;
        if (entity instanceof DetonatorItem) return EntityType.ITEM_DETONATOR;
        if (entity instanceof SpeedItem) return EntityType.ITEM_SPEED;
        if (entity instanceof WallpassItem) return EntityType.ITEM_WALL_BYPASS;

        return EntityType.TILE;
    }

    private EntityType detectEntity(Distance distance) {
        final int size = Sprite.SCALED_SIZE - 1;
        boolean bombFlag = false;
        Entity entity = null;

        // Xét tọa độ mới từ 4 đỉnh
        for (int i = 0; i < 4; i++) {
            int x = this.getX() + distance.getX() + (i % 2) * size + (i % 2 == 0 ? 4 : -10);
            int y = this.getY() + distance.getY() + (i / 2) * size + (i / 2 == 0 ? 5 : -5);

            entity = this.playground.getEntity(
                    Convert.pixelToTile(new Point(x, y))
            );

            if (entity instanceof Bomb) {
                bombFlag = true;
                if (entity == myLatestBomb) continue;
                return EntityType.BOMB;
            }

            if (entity instanceof Explosion && !(entity instanceof Brick)) return EntityType.BOMB;
            if (entity instanceof Item) return detectItem(entity);


            if (!(entity instanceof Grass)) return EntityType.TILE;
        }

        // entity in this line = Grass | Bomb already set
        if (entity instanceof Grass && !bombFlag) this.myLatestBomb = null;
        return EntityType.GRASS;
    }

    private void collide(Distance distance) {
        EntityType entityType = detectEntity(distance);

        switch (entityType) {
            case TILE:
                break;
            case ENERMY:
                // TODO: kill
                break;
            case BOMB:
                kill();
                break;
            case PORTAL:
                this.setAlive(false);
                break;

            case ITEM_BOMB_BYPASS:
                BombermanGame.updateCanByBomb();
                break;
            case ITEM_WALL_BYPASS:
                BombermanGame.updateCanByWall();
                break;
            case ITEM_FLAME_BYPASS:
                BombermanGame.updateCanByFlame();
                break;
            case ITEM_BOMB:
                BombermanGame.updateBombCapacity();
                break;
            case ITEM_FLAME:
                BombermanGame.updateFlameLength();
                break;
            case ITEM_SPEED:
                BombermanGame.updateBomberSpeed();
                break;
            case ITEM_DETONATOR:
                BombermanGame.updateCanDetonate();
                break;
            // TODO: Mystery item.

            default: // Grass can move through
                moveGraphic(distance);
                moveSprite(distance);
                break;
        }
    }

    private void moveGraphic(Distance distance) {
        int centerDistanceX = BombermanGame.SCENE_WIDTH / 2;
        int centerDistanceY = BombermanGame.SCENE_HEIGHT / 2;
        int mapWidth = playground.getWidthByPixel();
        int mapHeight = playground.getHeightByPixel();

        if (this.getX() >= centerDistanceX && this.getX() + centerDistanceX <= mapWidth) {
            this.playground.setOffsetX(this.playground.getOffsetX() - distance.getX());
        }
        if (this.getY() >= centerDistanceY && this.getY() + centerDistanceY <= mapHeight) {
            this.playground.setOffsetY(this.playground.getOffsetY() - distance.getY());
        }
    }

    /**
     * Move sprite.
     *
     * @param distance predicated distance
     */
    @Override
    protected void moveSprite(Distance distance) {
        this.setX(this.getX() + distance.getX());
        this.setY(this.getY() + distance.getY());
    }

    /**
     * Select which sprite will display.
     */
    @Override
    protected void selectSprite() {
        if (this.isAlive()) {
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
        } else {
            this.setSpriteImg(Sprite.selectSprite(
                    this.getFrameCount().getFrame(),
                    60,
                    player_dead, player_dead_1, player_dead_2
            ));
        }

    }
}
