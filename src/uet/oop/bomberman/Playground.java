package uet.oop.bomberman;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Keyboard.Keyboard;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.map.FileMapLoader;
import uet.oop.bomberman.map.MapLoader;

import java.util.ArrayList;
import java.util.List;

public class Playground {
    private MapLoader map;
    private Keyboard keyboard;

    private List<Entity> entities;
    private List<Character> characters;

    private GraphicsContext graphicsContext;
    private Canvas canvas;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public Playground() {
        try {
            this.entities = new ArrayList<>();
            this.characters = new ArrayList<>();

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
            this.characters.add(new Bomber(1, 1, keyboard));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }

    /**
     * Update all entities.
     */
    public void update() {
        this.entities.forEach(Entity::update);
        this.characters.forEach(Entity::update);

        keyboard.update();
    }

    /**
     * Render.
     */
    public void render() {
        graphicsContext.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        entities.forEach(entity -> entity.render(graphicsContext));
        characters.forEach(entity -> entity.render(graphicsContext));
    }

}
