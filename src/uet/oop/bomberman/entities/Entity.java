package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.spriteEntity.character.Bomber;
import uet.oop.bomberman.entities.tile.item.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Convert;
import uet.oop.bomberman.util.Point;

public abstract class Entity {

    private static final int CENTER_POINT_X = BombermanGame.SCENE_WIDTH / 2;
    private static final int CENTER_POINT_Y = BombermanGame.SCENE_HEIGHT / 2;

    private int x, y; // in pixel
    private Point coordinate; // in tile
    private Image spriteImg;
    private boolean invisible;

    public int getX() {
        return x;
    }

    protected void setX(int x) {
        this.x = x;
        this.setCoordinate(Convert.pixelToTile(new Point(this.x, this.y)));
    }

    public int getY() {
        return y;
    }

    protected void setY(int y) {
        this.y = y;
        this.setCoordinate(Convert.pixelToTile(new Point(this.x, this.y)));
    }

    public Point getCoordinate() {
        return coordinate;
    }

    protected void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public Image getSpriteImg() {
        return spriteImg;
    }

    protected void setSpriteImg(Image spriteImg) {
        this.spriteImg = spriteImg;
    }

    public boolean isInvisible() {
        return invisible;
    }

    protected void setInvisible(boolean invisible) {
        this.invisible = invisible;
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
        this.setX(crdX * Sprite.SCALED_SIZE); // convert from tile crd to pixel crd
        this.setY(crdY * Sprite.SCALED_SIZE); // convert from tile crd to pixel crd
        this.setCoordinate(new Point(crdX, crdY));
        this.setSpriteImg(spriteImg);
        this.setInvisible(false);
        this.setInvisible(false);
    }

    /**
     * Draw into graphic context.
     *
     * @param gc graphic context.
     */
    public void render(GraphicsContext gc, Playground playground) {
        int posX = this.getX();
        int posY = this.getY();
        int mapWidth = playground.getWidthByPixel();
        int mapHeight = playground.getHeightByPixel();

        if (this instanceof Bomber) {
            if (this.getX() >= CENTER_POINT_X && this.getX() + CENTER_POINT_X <= mapWidth)
                posX = CENTER_POINT_X;
            if (this.getY() >= CENTER_POINT_Y && this.getY() + CENTER_POINT_Y <= mapHeight)
                posY = CENTER_POINT_Y;
            if (this.getX() + CENTER_POINT_X > mapWidth)
                posX = BombermanGame.SCENE_WIDTH - (mapWidth - this.getX());
            if (this.getY() + CENTER_POINT_Y > mapHeight)
                posY = BombermanGame.SCENE_HEIGHT - (mapHeight - this.getY());
        } else {
            int offsetX = playground.getOffsetX();
            int offsetY = playground.getOffsetY();
            posX += offsetX;
            posY += offsetY;
        }

        gc.drawImage(spriteImg, posX, posY);
    }

    public static EntityType detectItem(Entity entity) {
        if (entity instanceof Portal) return EntityType.PORTAL;
        if (entity instanceof BombItem) return EntityType.ITEM_BOMB;
        if (entity instanceof BombpassItem) return EntityType.ITEM_BOMB_BYPASS;
        if (entity instanceof FlameItem) return EntityType.ITEM_FLAME;
        if (entity instanceof FlamepassItem) return EntityType.ITEM_FLAME_BYPASS;
        if (entity instanceof DetonatorItem) return EntityType.ITEM_DETONATOR;
        if (entity instanceof SpeedItem) return EntityType.ITEM_SPEED;
        if (entity instanceof WallpassItem) return EntityType.ITEM_WALL_BYPASS;

        return EntityType.TILE;
    }

    public abstract void update();

    public abstract void kill();
}
