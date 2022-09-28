package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public final class Wall extends Tile {
    private static final Image wall = Sprite.wall.getFxImage();

    public Wall(int x, int y) {
        super(x, y, wall);
    }
}
