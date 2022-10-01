package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Convert;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.untility.Point;

public class Bomb extends Character {
    private static final Image bomb_img = Sprite.bomb.getFxImage();
    private static final Image bomb_img_1 = Sprite.bomb_1.getFxImage();
    private static final Image bomb_img_2 = Sprite.bomb_2.getFxImage();

    public static final int TIME_EXPLODE = 120; // by frame unit

    private final Point coordinate;
    private boolean exploded;

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    @Override
    public void update() {
        selectSprite();
        explode();
        this.getFrameCount().update();
    }

    @Override
    protected boolean collide(Entity entity) {
        return false;
    }

    @Override
    protected void move(Distance distance) {
    }

    @Override
    protected void selectSprite() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 60,
                bomb_img_2, bomb_img_1, bomb_img));
    }

    private void explode() {
        if (this.getFrameCount().getFrame() > TIME_EXPLODE) {
            this.setExploded(true);
        }
    }

    public Bomb(int x, int y) {
        super(x, y, bomb_img_2);
        this.coordinate = Convert.pixelToTile(new Point(this.getX(), this.getY()));
        this.setExploded(false);
    }
}
