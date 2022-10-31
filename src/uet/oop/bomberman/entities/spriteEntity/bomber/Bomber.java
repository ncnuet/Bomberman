package uet.oop.bomberman.entities.spriteEntity.bomber;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.GameValue;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.MovableEntity;
import uet.oop.bomberman.entities.spriteEntity.enermy.Enemy;
import uet.oop.bomberman.entities.spriteEntity.bomb.Bomb;
import uet.oop.bomberman.entities.spriteEntity.bomb.FlameSegment;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.keyboard.KeyControl;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.entities.tile.item.*;
import uet.oop.bomberman.entities.EntityType;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Direction;
import uet.oop.bomberman.utils.Distance;
import uet.oop.bomberman.utils.Coordinate;

public final class Bomber extends MovableEntity {

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
    private static final int MIN_TIME_BETWEEN_PUT_BOMB = RENDER_TIME * 2 / 3;
    private final KeyControl keyboard;
    private int timeBetweenPutBomb; // by frame unit
    private Bomb myLatestBomb; // last set bomb

    /**
     * Constructor.
     *
     * @param crdX     x
     * @param crdY     y
     * @param keyboard keyboard
     * @param context  context
     */
    public Bomber(int crdX, int crdY, KeyControl keyboard, Context context) {
        super(new Coordinate(crdX, crdY), player_img_right, context);
        this.keyboard = keyboard;
    }

    private void listenSetBomb() {
        if (this.keyboard.isSpacePressed()) {
            setBomb();
        }
    }

    private void listenPauseGame() {
        if (this.keyboard.isEscapePressed()) {
            this.context.showMenu();

        }
    }

    private void setBomb() {
        int offsetX = Sprite.SCALED_SIZE / 2;
        int offsetY = Sprite.SCALED_SIZE / 2;

        Coordinate position = Coordinate.createCrdByPixel(
                this.getXAsPixel() + offsetX,
                this.getYAsPixel() + offsetY
        );

        Entity entity = this.context.getEntity(position, false);

        if (entity instanceof Grass // If place is Grass
                && this.timeBetweenPutBomb > MIN_TIME_BETWEEN_PUT_BOMB  // and time between is enough
                && GameValue.getCurrentCapacity() > 0) { // and bomb capacity greater than 0

            placeBomb(position); // Place bomb
            // Subtract current bomb capacity. Redo when bomb exploded completely
            GameValue.removeCurrentCapacity();
            this.timeBetweenPutBomb = 0;
        }
    }

    private void placeBomb(Coordinate position) {
        Bomb bomb = new Bomb(position.getX(), position.getY(), this.context);
        this.context.addBomb(bomb);
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
            int x = this.getXAsPixel() + distance.getX() + (i % 2) * size + (i % 2 == 0 ? 4 : -10);
            int y = this.getYAsPixel() + distance.getY() + (i / 2) * size + (i / 2 == 0 ? 3 : -3);

            entity = this.context.getEntity(Coordinate.createCrdByPixel(x, y), false);

            if (entity instanceof Bomb) {
                bombFlag = true;
                if (entity == myLatestBomb) continue;
                return entity;
            }

            if (!(entity instanceof Grass)) return entity;
        }

        // entity in this line = Grass | Bomb already set
        if (entity instanceof Grass && !bombFlag) this.myLatestBomb = null;
        return null;
    }

    @Override
    protected void collide(Distance distance) {
        Entity entity = detectEntity(distance);

        // Case can move
        if (entity == null || entity instanceof Grass
                || (GameValue.isCanByBomb() && entity instanceof Bomb)
                || (GameValue.isCanByFlame() && entity instanceof FlameSegment)
                || (GameValue.isCanByWall() && entity instanceof Wall &&
                entity.getCoordinate().isInMap(context))) {
            moveGraphic(distance);
            moveEntity(distance);
        }

        // Case be killed
        else if (entity instanceof Enemy || entity instanceof FlameSegment) {
            kill();
        }

        // Case get power-up
        else if (entity instanceof Item) {
            EntityType entityType = Entity.classify(entity);
            entity.kill();
            Sound.cryst_up.start();
            switch (entityType) {
                case ITEM_BOMB_BYPASS -> GameValue.updateCanByBomb(); // done
                case ITEM_WALL_BYPASS -> GameValue.updateCanByWall(); // done
                case ITEM_FLAME_BYPASS -> GameValue.updateCanByFlame(); // done
                case ITEM_BOMB -> { // done
                    GameValue.updateBombCapacity();
                    GameValue.addCurrentCapacity();
                }
                case ITEM_FLAME -> GameValue.updateFlameLength(); // done
                case ITEM_SPEED -> GameValue.updateBomberSpeed(); // done
                case ITEM_DETONATOR -> GameValue.updateCanDetonate(); // TODO:
                case ITEM_MYSTERY -> GameValue.updateMystery(); // TODO:
                case PORTAL -> {
                    this.context.nextLevel();
                }
                // TODO: Mystery item.
            }
        }
    }

    /**
     * Move entity or move screen
     *
     * @param distance distance
     */
    private void moveGraphic(Distance distance) {
        int centerDistanceX = BombermanGame.SCENE_WIDTH / 2;
        int centerDistanceY = BombermanGame.SCENE_HEIGHT / 2;
        int mapWidth = context.getWidthByPixel();
        int mapHeight = context.getHeightByPixel();

        if (this.getXAsPixel() >= centerDistanceX && this.getXAsPixel() + centerDistanceX <= mapWidth) {
            this.context.setOffsetX(this.context.getOffsetX() - distance.getX());
        }
        if (this.getYAsPixel() >= centerDistanceY && this.getYAsPixel() + centerDistanceY <= mapHeight) {
            this.context.setOffsetY(this.context.getOffsetY() - distance.getY());
        }
    }

    @Override
    public void update() {
        // Determine entity and move
        if (this.isAlive()) {
            Distance distance = keyboard.getDistance();
            setMoving(keyboard.isMoving());
            if (this.isMoving()) setDirection(keyboard.getDirection());
            collide(distance);

            updateSpriteOnRunning();
            if (GameValue.getTime() - 1 < 0) this.kill();
        } else {
            this.updateOnExploded();
        }

        // Listen press space key to set bomb
        listenSetBomb();
        listenPauseGame();
        this.timeBetweenPutBomb++;
        super.update();
    }

    @Override
    public void kill() {
        Sound.end_game.start();
        if (GameValue.getHeart() - 1 >= 0) {
            GameValue.setHeart(GameValue.getHeart() - 1);
        }
        this.setAlive(false);
    }

    @Override
    protected void moveEntity(Distance distance) {
        int offsetX = 0;
        int offsetY = 0;

        if (this.getXAsPixel() % Sprite.SCALED_SIZE >= Sprite.SCALED_SIZE - 4) offsetX = 4;
        else if (this.getXAsPixel() % Sprite.SCALED_SIZE <= 10) offsetX = -10;
        if (this.getYAsPixel() % Sprite.SCALED_SIZE >= Sprite.SCALED_SIZE - 3) offsetY = 3;
        else if (this.getYAsPixel() % Sprite.SCALED_SIZE <= 3) offsetY = -3;

        if (distance.getDirection() != null) {
            this.setXAsPixel(this.getXAsPixel() + distance.getX(), offsetX, offsetY);
            this.setYAsPixel(this.getYAsPixel() + distance.getY(), offsetX, offsetY);
        }
    }

    @Override
    protected void updateSpriteOnDead() {
        this.setSprite(Sprite.selectSprite(
                this.getFrameCounter().getFrame(), RENDER_TIME,
                player_dead, player_dead_1, player_dead_2
        ));
    }

    @Override
    protected void updateSpriteOnRunning() {
        switch (this.getDirection()) {
            case UP -> this.setSprite(!this.isMoving()
                    ? player_img_up
                    : Sprite.selectSprite(this.getFrameCounter().getFrame(), RENDER_TIME,
                    player_img_up_1, player_img_up_2
            ));
            case DOWN -> this.setSprite(!this.isMoving()
                    ? player_img_down
                    : Sprite.selectSprite(this.getFrameCounter().getFrame(), RENDER_TIME,
                    player_img_down_1, player_img_down_2
            ));
            case LEFT -> this.setSprite(!this.isMoving()
                    ? player_img_left
                    : Sprite.selectSprite(this.getFrameCounter().getFrame(), RENDER_TIME,
                    player_img_left_1, player_img_left_2
            ));
            case RIGHT -> this.setSprite(!this.isMoving()
                    ? player_img_right
                    : Sprite.selectSprite(this.getFrameCounter().getFrame(), RENDER_TIME,
                    player_img_right_1, player_img_right_2
            ));
        }
    }
}
