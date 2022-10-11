package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public final class FlameItem extends Item {
    private static final Image flame_item = Sprite.powerup_flames.getFxImage();

    public FlameItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, flame_item);
    }
}