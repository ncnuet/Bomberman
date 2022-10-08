package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class DetonatorItem extends Item {
    private static final Image detonator_item = Sprite.powerup_detonator.getFxImage();

    public DetonatorItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, detonator_item);
    }
}
