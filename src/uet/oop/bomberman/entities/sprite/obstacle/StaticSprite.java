package uet.oop.bomberman.entities.sprite.obstacle;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.sprite.Sprite;

public abstract class StaticSprite extends Sprite {
    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public StaticSprite(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }

    @Override
    public void update() {
        super.update();
    }
}
