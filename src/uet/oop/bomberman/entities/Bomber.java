package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Keyboard;
import uet.oop.bomberman.graphics.Sprite;

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


    public Bomber(int x, int y, Keyboard keyboard) {
        super(x, y, Sprite.player_right.getFxImage());
        this.keyboard = keyboard;
    }

    @Override
    public void update() {
        Distance distance = listenMoving();
        move(distance);
        selectSprite();
        this.getFrameCount().update();
    }

    protected Distance listenMoving() {
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

    /**
     * Check if character can move with set direction.
     *
     * @return boolean
     */
    @Override
    protected boolean canMove(Distance distance) {
        return true;
    }

    /**
     * Move sprite.
     *
     * @param distance predicated distance
     */
    @Override
    protected void move(Distance distance) {
        if (canMove(distance)) {
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
                    : Sprite.selectSprite(player_img_up_1, player_img_up_2,
                    this.getFrameCount().getFrame(), 20));
            case DOWN -> this.setSpriteImg(!this.isMoving()
                    ? player_img_down
                    : Sprite.selectSprite(player_img_down_1, player_img_down_2,
                    this.getFrameCount().getFrame(), 20));
            case LEFT -> this.setSpriteImg(!this.isMoving()
                    ? player_img_left
                    : Sprite.selectSprite(player_img_left_1, player_img_left_2,
                    this.getFrameCount().getFrame(), 20));
            case RIGHT -> this.setSpriteImg(!this.isMoving()
                    ? player_img_right
                    : Sprite.selectSprite(player_img_right_1, player_img_right_2,
                    this.getFrameCount().getFrame(), 20));
        }
    }
}
