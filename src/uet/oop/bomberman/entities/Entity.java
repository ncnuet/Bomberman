package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {

    private int x;
    private int y;
    private Image spriteImg;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
     * @param xUnit     position in predefined
     * @param yUnit     position in predefined
     * @param spriteImg image
     */
    public Entity(int xUnit, int yUnit, Image spriteImg) {
        this.setX(xUnit * Sprite.SCALED_SIZE);
        this.setY(yUnit * Sprite.SCALED_SIZE);

        this.setSpriteImg(spriteImg);
    }

    /**
     * Draw into graphic context.
     *
     * @param gc graphic context.
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(spriteImg, x, y);
    }

    /**
     * Update.
     */
    public abstract void update();

    protected abstract boolean collide(Entity entity);
}
