package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public final class Grass extends Tile {
    private static final Image grass = Sprite.grass.getFxImage();

    public Grass(int x, int y) {
        super(x, y, grass);
    }
}
