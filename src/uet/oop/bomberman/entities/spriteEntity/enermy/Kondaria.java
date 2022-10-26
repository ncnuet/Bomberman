package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;

public final class Kondaria extends Enemy {
    private static final Image[] left_sprites = new Image[]{
            Sprite.kondoria_left1.getFxImage(),
            Sprite.kondoria_left2.getFxImage(),
            Sprite.kondoria_left3.getFxImage()};
    private static final Image[] right_sprites = new Image[]{
            Sprite.kondoria_right1.getFxImage(),
            Sprite.kondoria_right2.getFxImage(),
            Sprite.kondoria_right3.getFxImage()};

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX position in predefined
     * @param crdY position in predefined
     */
    public Kondaria(int crdX, int crdY, Context context) {
        super(new Coordinate(crdX, crdY),
                left_sprites[0], context);

        this.setDeadSprite(left_sprites[0]);
        this.setLeftSprites(left_sprites);
        this.setRightSprites(right_sprites);
    }
}
