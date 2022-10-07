package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.character.moving.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.Convert;
import uet.oop.bomberman.untility.Point;

public abstract class Entity {
    private int x; // in pixel
    private int y; // in pixel
    private Point coordinate; // in tile
    private Image spriteImg;

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        if (coordinate.x < 0 || coordinate.y < 0) {
            throw new IllegalArgumentException("Incorrect coordinate");
        }
        this.coordinate = coordinate;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        this.setCoordinate(Convert.pixelToTile(new Point(this.x, this.y)));
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        this.setCoordinate(Convert.pixelToTile(new Point(this.x, this.y)));
    }

    public Image getSpriteImg() {
        return spriteImg;
    }

    public void setSpriteImg(Image spriteImg) {
        this.spriteImg = spriteImg;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      in tile
     * @param crdY      in tile
     * @param spriteImg image
     */
    public Entity(int crdX, int crdY, Image spriteImg) {
        this.setX(crdX * Sprite.SCALED_SIZE);
        this.setY(crdY * Sprite.SCALED_SIZE);

        this.setSpriteImg(spriteImg);
    }

    /**
     * Draw into graphic context.
     *
     * @param gc graphic context.
     */
    public void render(GraphicsContext gc, Playground playground) {
        int posX = this.getX();
        int posY = this.getY();
        int centerDistanceX = BombermanGame.SCENE_WIDTH / 2;
        int centerDistanceY = BombermanGame.SCENE_HEIGHT / 2;
        int mapWidth = playground.getWidthByPixel();
        int mapHeight = playground.getHeightByPixel();

        if (this instanceof Bomber) {
            if (this.getX() >= centerDistanceX && this.getX() + centerDistanceX <= mapWidth)
                posX = centerDistanceX;
            if (this.getY() >= centerDistanceY && this.getY() + centerDistanceY <= mapHeight)
                posY = centerDistanceY;
            if (this.getX() + centerDistanceX > mapWidth)
                posX = BombermanGame.SCENE_WIDTH - (mapWidth - this.getX());
            if (this.getY() + centerDistanceY > mapHeight)
                posY = BombermanGame.SCENE_HEIGHT - (mapHeight - this.getY());
        } else {
            int offsetX = playground.getOffsetX();
            int offsetY = playground.getOffsetY();
            posX += offsetX;
            posY += offsetY;
        }

        gc.drawImage(spriteImg, posX, posY);
    }

    /**
     * Update.
     */
    public abstract void update();
}
