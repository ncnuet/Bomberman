package uet.oop.bomberman.entities.spriteEntity.obstacle.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.spriteEntity.obstacle.StaticSprite;

public abstract class BombSprite extends StaticSprite {
    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public BombSprite(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }

    @Override
    public void kill() {
        this.setInvisible(true);
    }
}
