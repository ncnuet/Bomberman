package uet.oop.bomberman.entities.spriteEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.spriteEntity.ChangeableSpriteEntity;
import uet.oop.bomberman.utils.Coordinate;

public abstract class UnmovableEntity extends ChangeableSpriteEntity {

    public UnmovableEntity(Coordinate crd, Image sprite) {
        super(crd, sprite);
    }

    @Override
    protected void updateSpriteOnRunning() {

    }
}
