package uet.oop.bomberman.entities.tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    private static final Image bomb_item = Sprite.powerup_bombs.getFxImage();

    public BombItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, bomb_item);
    }
}