package uet.oop.bomberman.entities.tile.item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.tile.Tile;

public abstract class Item extends Tile {
    public Item(int xUnit, int yUnit, Image spriteImg) {
        super(xUnit, yUnit, spriteImg);
    }
}
