package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Tile {
    private static final Image speed_item = Sprite.powerup_speed.getFxImage();

    public SpeedItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, speed_item);
    }
}
