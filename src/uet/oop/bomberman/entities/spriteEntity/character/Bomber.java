package uet.oop.bomberman.entities.spriteEntity.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.character.enermy.Enemy;
import uet.oop.bomberman.entities.spriteEntity.obstacle.bomb.Bomb;
import uet.oop.bomberman.entities.spriteEntity.obstacle.bomb.FlameSegment;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.keyboard.KeyControl;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.entities.tile.item.*;
import uet.oop.bomberman.entities.EntityType;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Convert;
import uet.oop.bomberman.util.Distance;
import uet.oop.bomberman.util.Point;

public final class Bomber extends Character {

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
            setMoving(keyboard.isMoving());
            if (this.isMoving()) setDirection(keyboard.getDirection());
            collide(distance);
            selectSprite();
        } else {
            this.explode();
        }

        // Listen press space key to set bomb
        listenSetBomb();
        this.timeBetweenPutBomb++;
        super.update();
    }

    @Override
    public void kill() {
        Sound.end_game.start();
        this.setAlive(false);
    }

    @Override
    protected void selectSpriteOnDead() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(),
                60,
                player_dead, player_dead_1, player_dead_2
        ));
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
        int offsetX = Sprite.SCALED_SIZE / 2;
        int offsetY = Sprite.SCALED_SIZE / 2;

        Point position = Convert.pixelToTile(new Point(
                this.getX() + offsetX,
                this.getY() + offsetY
        ));

        Entity entity = this.playground.getEntity(position);

        if (entity instanceof Grass &&
                this.timeBetweenPutBomb > MIN_TIME_BETWEEN_PUT_BOMB &&
                BombermanGame.getCurrentCapacity() > 0) {
            placeBomb(position);
            BombermanGame.removeCurrentCapacity();
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

    private Entity detectEntity(Distance distance) {
        final int size = Sprite.SCALED_SIZE - 1;
        boolean bombFlag = false;
        Entity entity = null;

        // Xét tọa độ mới từ 4 đỉnh
        for (int i = 0; i < 4; i++) {
            int x = this.getX() + distance.getX() + (i % 2) * size + (i % 2 == 0 ? 4 : -10);
            int y = this.getY() + distance.getY() + (i / 2) * size + (i / 2 == 0 ? 5 : -5);

            entity = this.playground.getEntity(Convert.pixelToTile(new Point(x, y)));

            if (entity instanceof Bomb) {
                bombFlag = true;
                if (entity == myLatestBomb) continue;
                return entity;
            }
            if (entity instanceof FlameSegment || entity instanceof Enemy) {
                return entity;
            }
            if (!(entity instanceof Grass)) return entity;
        }

        // entity in this line = Grass | Bomb already set
        if (entity instanceof Grass && !bombFlag) this.myLatestBomb = null;
        return null;
    }

    private void collide(Distance distance) {
        Entity entity = detectEntity(distance);
        if (entity == null || entity instanceof Grass ||
                (BombermanGame.isConf_canByBomb() && entity instanceof Bomb) ||
                (BombermanGame.isConf_canByWall() && entity instanceof Wall &&
                        !this.playground.isOutOfBound(entity.getCoordinate()))) {
            moveGraphic(distance);
            moveSprite(distance);
        } else if (entity instanceof Enemy) {
            kill();
        } else if (entity instanceof FlameSegment) {
            if (!BombermanGame.isConf_canByFlame()) {
                kill();
            }
        } else if (entity instanceof Item) {
            EntityType entityType = Entity.detectItem(entity);
            entity.kill();
            Sound.cryst_up.start();
            switch (entityType) {
                case ITEM_BOMB_BYPASS -> BombermanGame.updateCanByBomb(); // v
                case ITEM_WALL_BYPASS -> BombermanGame.updateCanByWall(); // v
                case ITEM_FLAME_BYPASS -> BombermanGame.updateCanByFlame(); // v
                case ITEM_BOMB -> { // v
                    BombermanGame.updateBombCapacity();
                    BombermanGame.addCurrentCapacity();
                }
                case ITEM_FLAME -> BombermanGame.updateFlameLength(); // v
                case ITEM_SPEED -> BombermanGame.updateBomberSpeed(); // v
                case ITEM_DETONATOR -> BombermanGame.updateCanDetonate(); // TODO:
                case ITEM_MYSTERY -> BombermanGame.updateMystery(); // TODO:
                case PORTAL -> this.setAlive(false);
                // TODO: Mystery item.
            }
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
