package uet.oop.bomberman.entities.tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

public class WallpassItem extends Item {
    private static final Image wall_pass_item = Sprite.powerup_wallpass.getFxImage();

    public WallpassItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, wall_pass_item);
    }
}