package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Convert;
import uet.oop.bomberman.untility.Distance;
import uet.oop.bomberman.untility.Point;

public class Flame extends Character {
    private static final Image center = Sprite.bomb_exploded.getFxImage();
    private static final Image center_1 = Sprite.bomb_exploded1.getFxImage();
    private static final Image center_2 = Sprite.bomb_exploded2.getFxImage();

    private static final Image ver_first = Sprite.explosion_vertical_top_last.getFxImage();
    private static final Image ver_first_1 = Sprite.explosion_vertical_top_last1.getFxImage();
    private static final Image ver_first_2 = Sprite.explosion_vertical_top_last2.getFxImage();

    private static final Image ver_last = Sprite.explosion_vertical_down_last.getFxImage();
    private static final Image ver_last_1 = Sprite.explosion_vertical_down_last1.getFxImage();
    private static final Image ver_last_2 = Sprite.explosion_vertical_down_last2.getFxImage();

    private static final Image hor_left = Sprite.explosion_horizontal_left_last.getFxImage();
    private static final Image hor_left_1 = Sprite.explosion_horizontal_left_last1.getFxImage();
    private static final Image hor_left_2 = Sprite.explosion_horizontal_left_last2.getFxImage();

    private static final Image hor_right = Sprite.explosion_horizontal_right_last.getFxImage();
    private static final Image hor_right_1 = Sprite.explosion_horizontal_right_last1.getFxImage();
    private static final Image hor_right_2 = Sprite.explosion_horizontal_right_last2.getFxImage();

    private boolean exploded;
    private final Point coordinate;
    private Playground playground;
    FramSegment[] side;

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public Flame(int x, int y, Playground playground) {
        super(x, y, center);
        this.setExploded(false);
        this.coordinate = Convert.pixelToTile(new Point(this.getX(), this.getY()));
        side = new FramSegment[4];
        this.playground = playground;
    }

    @Override
    public void update() {
        selectSprite();
        explode();
        this.getFrameCount().update();
    }

    @Override
    protected void move(Distance distance) {
    }

    @Override
    protected void selectSprite() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 30,
                center, center_1, center_2));
    }

    private void explode() {
        if (this.getFrameCount().getFrame() > 30) {
            this.setExploded(true);
        }
    }

    @Override
    protected boolean collide(Entity entity) {
        return false;
    }
}
