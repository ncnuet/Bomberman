package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public final class SpeedItem extends Item {
    private static final Image speed_item = Sprite.powerup_speed.getFxImage();

    public SpeedItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, speed_item);
    }
}
