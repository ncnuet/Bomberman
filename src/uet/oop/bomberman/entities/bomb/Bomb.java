package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Keyboard.Keyboard;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Distance;

public class Bomb extends Character {
    private static final Image bomb_img = Sprite.bomb.getFxImage();
    private static final Image bomb_img_1 = Sprite.bomb_1.getFxImage();
    private static final Image bomb_img_2 = Sprite.bomb_2.getFxImage();

    private final Playground playground;

    @Override
    public void update() {
        selectSprite();
        this.getFrameCount().update();
    }

    @Override
    protected void move(Distance distance) {

    }

    @Override
    protected Distance listenMoving() {
        return null;
    }

    @Override
    protected void selectSprite() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 60,
                bomb_img, bomb_img_1, bomb_img_2));
    }

    @Override
    protected boolean collide(Entity entity) {
        return false;
    }

    public Bomb(int x, int y, Playground playground) {
        super(x, y, bomb_img);
        this.playground = playground;
    }
}
