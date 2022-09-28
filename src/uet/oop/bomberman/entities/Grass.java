package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {
    private static final Image grass = Sprite.grass.getFxImage();

    public Grass(int x, int y) {
        super(x, y, grass);
    }

    @Override
    public void update() {

    }
}
