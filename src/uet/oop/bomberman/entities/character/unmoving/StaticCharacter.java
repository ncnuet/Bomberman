package uet.oop.bomberman.entities.character.unmoving;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.untility.Distance;

public abstract class StaticCharacter extends Character {
    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public StaticCharacter(int crdX, int crdY, Image spriteImg) {
        super(crdX, crdY, spriteImg);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    protected void selectSprite() {
    }
}
