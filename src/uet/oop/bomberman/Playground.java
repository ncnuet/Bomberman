package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Keyboard.Keyboard;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.FileMapLoader;
import uet.oop.bomberman.map.MapLoader;
import uet.oop.bomberman.untility.Point;

import java.util.ArrayList;
import java.util.List;

public class Playground {
    private MapLoader map;
    private Keyboard keyboard;

    private List<Entity> entities;
    private List<Character> characters;
    private List<Bomb> bombs;

    private GraphicsContext graphicsContext;
    private Canvas canvas;

    private Scene scene;

    public Scene getScene() {
        return this.scene;
    }

    public Playground() {
        try {
            this.entities = new ArrayList<>();
            this.characters = new ArrayList<>();
            this.bombs = new ArrayList<>();

            this.map = new FileMapLoader("Level1", this);
            this.canvas = new Canvas(
                    Sprite.SCALED_SIZE * this.map.getSize().getWidth(),
                    Sprite.SCALED_SIZE * this.map.getSize().getWidth());
            this.graphicsContext = canvas.getGraphicsContext2D();

            // Create root container
            Group root = new Group();
            root.getChildren().add(this.canvas);

            this.scene = new Scene(root);
            this.keyboard = new Keyboard(scene);
            this.characters.add(new Bomber(1, 1, keyboard, this));

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

    public Entity getTile(int index) {
        return this.entities.get(index);
    }

    public Entity getEntity(Point point) {
        int width = this.map.getWidth();
        Entity tileEntity = getTile(point.y * width + point.x);
        return tileEntity;
    }

    /**
     * Update all entities.
     */
    public void update() {
        this.entities.forEach(Entity::update);
        this.characters.forEach(Entity::update);
        this.bombs.forEach(Entity::update);

        keyboard.update();
    }

    public void render() {
        graphicsContext.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        entities.forEach(entity -> entity.render(graphicsContext));
        characters.forEach(entity -> entity.render(graphicsContext));
        bombs.forEach(entity -> entity.render(graphicsContext));
    }

}
