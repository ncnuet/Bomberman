package uet.oop.bomberman.entities.tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class FlamepassItem extends Item {
    private static final Image flame_pass = Sprite.powerup_flamepass.getFxImage();

    public FlamepassItem(int xUnit, int yUnit) {
        super(xUnit, yUnit, flame_pass);
    }
}
