package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.untility.Point;
import uet.oop.bomberman.entities.changeable.character.CharacterType;
import uet.oop.bomberman.entities.changeable.character.MovingChangeableObject;
import uet.oop.bomberman.entities.changeable.unmovable.Explosion;
import uet.oop.bomberman.keyboard.KeyControl;
import uet.oop.bomberman.entities.changeable.character.Bomber;
import uet.oop.bomberman.entities.EntityGroup;
import uet.oop.bomberman.entities.changeable.unmovable.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.FileMapLoader;
import uet.oop.bomberman.map.MapLoader;

import java.util.ArrayList;
import java.util.List;

public class Playground {
    private int MIN_OFFSET_X;
    private int MIN_OFFSET_Y;
    private List<Entity> entities;
    private List<MovingChangeableObject> characters;
    private List<Bomb> bombs;
    private List<Explosion> flames;

    private MapLoader map;
    private KeyControl keyboard;
    private GraphicsContext graphicsContext;
    private Canvas canvas;
    private Scene scene;
    private int offsetX = 0;
    private int offsetY = 0;

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        if (offsetX <= 0 && offsetX >= MIN_OFFSET_X) {
            this.offsetX = offsetX;
        }
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        if (offsetY <= 0 && offsetY >= MIN_OFFSET_Y) {
            this.offsetY = offsetY;
        }
    }

    public int getWidthByPixel() {
        return this.map.getWidthByPixel();
    }

    public int getHeightByPixel() {
        return this.map.getHeightByPixel();
    }

    public Scene getScene() {
        return this.scene;
    }

    public Playground() {
        try {
            // Initial list
            this.entities = new ArrayList<>();
            this.characters = new ArrayList<>();
            this.bombs = new ArrayList<>();
            this.flames = new ArrayList<>();

            // Load map
            this.map = new FileMapLoader("Level1");
            this.canvas = new Canvas(this.map.getHeightByPixel(), this.map.getWidthByPixel());
            this.graphicsContext = canvas.getGraphicsContext2D();

            // Create root container wrap canvas
            Group root = new Group();
            root.getChildren().add(canvas);

            // Start draw
            this.scene = new Scene(root);
            this.keyboard = new KeyControl(scene);
            this.map.generateMap(this);

            // Define boundary
            int app_real_width = BombermanGame.APP_TILE_WIDTH * Sprite.SCALED_SIZE;
            int app_real_height = BombermanGame.APP_TILE_HEIGHT * Sprite.SCALED_SIZE;
            this.MIN_OFFSET_X = app_real_width - this.map.getWidthByPixel();
            this.MIN_OFFSET_Y = app_real_height - this.map.getHeightByPixel();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    public void addBomb(Bomb bomb) {
        this.bombs.add(bomb);
    }

    public void addFlame(Explosion explosion) {
        this.flames.add(explosion);
    }

    public void addCharacter(int x, int y, CharacterType characterType) {
        switch (characterType) {
            case BOMBER -> this.characters.add(new Bomber(x, y, keyboard, this));
        }
    }

    public Entity getTile(int index) {
        Entity entity = this.entities.get(index);
        if (entity instanceof EntityGroup) return ((EntityGroup) entity).getTopEntity();
        return entity;
    }

    public Entity getBomb(int x, int y) {
        for (Bomb bomb : this.bombs) {
            Point coordinate = bomb.getCoordinate();
            if (coordinate.x == x && coordinate.y == y) {
                return bomb;
            }
        }
        return null;
    }

    public Entity getFlame(int x, int y) {
        for (Explosion flame : this.flames) {
            Point coordinate = flame.getCoordinate();
            if (coordinate.x == x && coordinate.y == y) {
                return flame;
            }
        }
        return null;
    }

    public Entity getEntity(Point point) {
        int width = this.map.getWidth();

        Entity tile = getTile(point.y * width + point.x);
        Entity bomb = getBomb(point.x, point.y);
        Entity flame = getFlame(point.x, point.y);

        if (flame == null) {
            return (bomb == null) ? tile : bomb;
        } else return flame;
    }

    public boolean isOutOfBound(Point coordinate) {
        return (coordinate.x >= this.map.getWidth() - 1 || coordinate.x <= 0 ||
                coordinate.y >= this.map.getHeight() - 1 || coordinate.y <= 0);
    }

    /**
     * Update all entities.
     */
    public void update() {
        // Update entities
        this.entities.forEach(Entity::update);
        this.characters.forEach(Entity::update);
        this.bombs.forEach(Entity::update);
        this.flames.forEach(Entity::update);

        // Remove out date entities
        this.bombs.removeIf(Bomb::isInvisible);
        this.flames.removeIf(Explosion::isExploded);
        this.characters.removeIf(MovingChangeableObject::isInvisible);

        keyboard.update();
    }

    public void render() {
        graphicsContext.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        entities.forEach(entity -> entity.render(graphicsContext, this));
        characters.forEach(entity -> entity.render(graphicsContext, this));
        bombs.forEach(entity -> entity.render(graphicsContext, this));
        flames.forEach(entity -> entity.render(graphicsContext, this));
    }

}
