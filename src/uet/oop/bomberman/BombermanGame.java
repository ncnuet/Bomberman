package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.Keyboard.Keyboard;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.untility.PathFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {
    private static final String Title = "Bomberman Game made by group 22";
    private static final String IconPath = "/icons/icon.png";

    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    public static final int SCREEN_WIDTH = 31;
    public static final int SCREEN_HEIGHT = 21;

    public static int BomberSpeed = 1;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public void t() {

    }

    Keyboard keyboard;

    /**
     * Entry point.
     *
     * @param args input arguments
     */
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    /**
     * Start render.
     *
     * @param stage stage
     */
    @Override
    public void start(Stage stage) {
        //Start background sound
        Sound.bg_sound.start();

        // Create Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Create root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Create scene
        Scene scene = new Scene(root);

        //Create keyboard
        this.keyboard = new Keyboard(scene);

        // Add scene into stage
        stage.setScene(scene);
        stage.show();

        // Set stage
        stage.setTitle(BombermanGame.Title);
        stage.setResizable(false);
        stage.setMaxWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH);
        stage.setMaxHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT);

        InputStream stream = PathFile.getPath(BombermanGame.IconPath);
        if (stream != null) {
            stage.getIcons().add(new Image(stream));
        }

        // Set timer action
        AnimationTimer timer = new AnimationTimer() {
            private static long lastTime = System.nanoTime();
            private static long updateTimes = 0;

            @Override
            public void handle(long now) {
                if (now - lastTime > 500000000) { // Calc fps after half of second. 500,000,000 ns
                    stage.setTitle(BombermanGame.Title + " | " + updateTimes * 2 + " fps");
                    lastTime = System.nanoTime();
                    updateTimes = 0;
                }

                render();
                update();
                updateTimes++;
            }
        };
        timer.start();

        // Load map
        createMap();

        Entity bomberman = new Bomber(1, 1, keyboard);
        entities.add(bomberman);
    }

    /**
     * Create game's frame.
     */
    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j);
                } else {
                    object = new Grass(i, j);
                }
                stillObjects.add(object);
            }
        }
    }

    /**
     * Update all entities.
     */
    public void update() {
        entities.forEach(Entity::update);
        keyboard.update();
    }

    /**
     * Render.
     */
    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
