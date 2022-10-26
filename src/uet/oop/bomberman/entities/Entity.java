package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.spriteEntity.bomber.Bomber;
import uet.oop.bomberman.entities.tile.item.*;
import uet.oop.bomberman.utils.Coordinate;

/*
 The class manages the display and the position of an entity.
 This is the top base class.
 */
public abstract class Entity {

    private static final int CENTER_POINT_X = BombermanGame.SCENE_WIDTH / 2;
    private static final int CENTER_POINT_Y = BombermanGame.SCENE_HEIGHT / 2;

    private final Coordinate coordinate; // the coordinate of entity by tile
    private Image spriteImg; // the image of entity
    private boolean isRemoved; // default = false. If true, entity will be cleared

    /**
     * Get and set value as pixel coordinate
     */

    public int getXAsPixel() {
        return this.coordinate.getPixelCrd().getX();
    }

    protected void setXAsPixel(int x) {
        this.coordinate.setXAsPixel(x);
    }

    protected void setXAsPixel(int x, int offsetX, int offsetY) {
        this.coordinate.setXAsPixel(x, offsetX, offsetY);
    }

    public int getYAsPixel() {
        return this.coordinate.getPixelCrd().getY();
    }

    protected void setYAsPixel(int y) {
        this.coordinate.setYAsPixel(y);
    }

    protected void setYAsPixel(int y, int offsetX, int offsetY) {
        this.coordinate.setYAsPixel(y, offsetX, offsetY);
    }

    public int getX() {
        return this.coordinate.getX();
    }

    public int getY() {
        return this.coordinate.getY();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Image getSprite() {
        return spriteImg;
    }

    protected void setSprite(Image spriteImg) {
        this.spriteImg = spriteImg;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    protected void setRemoved(boolean removed) {
        this.isRemoved = removed;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crd    coordinate
     * @param sprite image
     */
    public Entity(Coordinate crd, Image sprite) {
        this.coordinate = crd;
        this.setSprite(sprite);
        this.setRemoved(false);
    }

    /**
     * Draw into graphic context.
     *
     * @param gc graphic context.
     */
    public void render(GraphicsContext gc, Context context) {
        int x = this.getXAsPixel();
        int y = this.getYAsPixel();
        int mapWidth = context.getWidthByPixel();
        int mapHeight = context.getHeightByPixel();

        if (this instanceof Bomber) {
            if (this.getXAsPixel() >= CENTER_POINT_X && this.getXAsPixel() + CENTER_POINT_X <= mapWidth)
                x = CENTER_POINT_X;
            if (this.getYAsPixel() >= CENTER_POINT_Y && this.getYAsPixel() + CENTER_POINT_Y <= mapHeight)
                y = CENTER_POINT_Y;
            if (this.getXAsPixel() + CENTER_POINT_X > mapWidth)
                x = BombermanGame.SCENE_WIDTH - (mapWidth - this.getXAsPixel());
            if (this.getYAsPixel() + CENTER_POINT_Y > mapHeight)
                y = BombermanGame.SCENE_HEIGHT - (mapHeight - this.getYAsPixel());
        } else {
            int offsetX = context.getOffsetX();
            int offsetY = context.getOffsetY();
            x += offsetX;
            y += offsetY;
        }

        gc.drawImage(spriteImg, x, y);
    }

    /**
     * Classify the Item type
     *
     * @param entity Item entity
     * @return type of item or tile
     */
    public static EntityType classify(Entity entity) {
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

    /**
     * Update entity state.
     */
    public abstract void update();

    /**
     * Update killable state
     */
    public abstract void kill();
}
