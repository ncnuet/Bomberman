package uet.oop.bomberman.entities.tile.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Item.Item;
import uet.oop.bomberman.graphics.Sprite;

public final class Portal extends Item {
    private static final Image portal = Sprite.portal.getFxImage();

    public Portal(int x, int y) {
        super(x, y, portal);
    }
}