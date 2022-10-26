package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.enermy.Balloon;
import uet.oop.bomberman.entities.spriteEntity.enermy.Enemy;
import uet.oop.bomberman.entities.spriteEntity.enermy.Oneal;
import uet.oop.bomberman.entities.spriteEntity.bomb.Flame;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.entities.spriteEntity.CharacterType;
import uet.oop.bomberman.entities.spriteEntity.MovableEntity;
import uet.oop.bomberman.keyboard.KeyControl;
import uet.oop.bomberman.entities.spriteEntity.bomber.Bomber;
import uet.oop.bomberman.entities.StackEntity;
import uet.oop.bomberman.entities.spriteEntity.bomb.Bomb;
import uet.oop.bomberman.map.FileMapLoader;
import uet.oop.bomberman.map.MapLoader;
import uet.oop.bomberman.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private int MIN_OFFSET_X;
    private int MIN_OFFSET_Y;
    private List<Entity> entities;
    private List<MovableEntity> movableEntities;
    private List<Bomb> bombs;
    private List<Flame> flames;

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

    public int getWidth() {
        return this.map.getWidth();
    }

    public int getHeight() {
        return this.map.getHeight();
    }

    public Scene getScene() {
        return this.scene;
    }

    public Context() {
        try {
            // Initial list
            this.entities = new ArrayList<>();
            this.movableEntities = new ArrayList<>();
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
            this.MIN_OFFSET_X = BombermanGame.SCENE_WIDTH - this.map.getWidthByPixel();
            this.MIN_OFFSET_Y = BombermanGame.SCENE_WIDTH - this.map.getHeightByPixel();

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

    public void addFlame(Flame flame) {
        this.flames.add(flame);
    }

    public void addCharacter(int x, int y, CharacterType characterType) {
        switch (characterType) {
            case BOMBER -> this.movableEntities.add(new Bomber(x, y, keyboard, this));
            case BALLOON -> this.movableEntities.add(new Balloon(x, y, this));
            case ONEAL -> this.movableEntities.add(new Oneal(x, y, this));
        }
    }

    private Entity getTile(Coordinate crd) {
        int width = this.map.getWidth();
        final int index = crd.getTileCrd().getY() * width + crd.getTileCrd().getX();

        Entity entity = this.entities.get(index);
        if (entity instanceof StackEntity) return ((StackEntity) entity).peek();
        return entity;
    }

    private Entity getBomb(Coordinate crd) {
        for (Bomb bomb : this.bombs) {
            if (bomb.getCoordinate().equals(crd)) {
                return bomb;
            }
        }
        return null;
    }

    /**
     * Get Flame at given position
     *
     * @param crd coordinate
     * @return Flame entity if exist
     */
    private Entity getFlame(Coordinate crd) {
        for (Flame flame : this.flames) {
            Entity e = flame.get(crd);
            if (e != null) return e;
        }
        return null;
    }

    private Entity getEnemy(Coordinate crd) {
        for (MovableEntity movableEntity : this.movableEntities) {
            if (movableEntity instanceof Enemy) {
                if (movableEntity.getCoordinate().equals(crd)) {
                    return movableEntity;
                }
            }
        }
        return null;
    }

    public Entity getEntity(Coordinate crd, boolean excludeEnemy) {
        Entity tile = getTile(crd);
        Entity bomb = getBomb(crd);
        Entity flame = getFlame(crd);

        if (!excludeEnemy) {
            Entity enemy = getEnemy(crd);
            if (enemy != null) return enemy;
        }

        if (flame != null) return flame;
        if (bomb != null) return bomb;
        return tile;
    }

    /**
     * Update all entities.
     */
    public void update() {
        // Update entities
        this.entities.forEach(Entity::update);
        this.movableEntities.forEach(Entity::update);
        this.bombs.forEach(Entity::update);
        this.flames.forEach(Entity::update);

        // Remove out date entities
        this.bombs.removeIf(Bomb::isRemoved);
        this.flames.removeIf(Flame::isRemoved);
        this.movableEntities.removeIf(MovableEntity::isRemoved);

        keyboard.update();
    }

    public void render() {
        graphicsContext.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        entities.forEach(entity -> entity.render(graphicsContext, this));
        movableEntities.forEach(entity -> entity.render(graphicsContext, this));
        bombs.forEach(entity -> entity.render(graphicsContext, this));
        flames.forEach(entity -> entity.render(graphicsContext, this));
    }

}
