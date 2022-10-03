package uet.oop.bomberman.entities.tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    private static final Image speed_item = Sprite.powerup_speed.getFxImage();

    public SpeedItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, speed_item);
    }
}
