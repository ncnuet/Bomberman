package uet.oop.bomberman.entities.tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

public class BombpassItem extends Item {
    private static final Image bom_pass_item = Sprite.powerup_bombpass.getFxImage();

    public BombpassItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, bom_pass_item);
    }
}
