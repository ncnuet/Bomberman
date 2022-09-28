package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Entity {
    private static final Image wall = Sprite.wall.getFxImage();

    public Wall(int x, int y) {
        super(x, y, wall);
    }

    @Override
    public void update() {

    }
}
