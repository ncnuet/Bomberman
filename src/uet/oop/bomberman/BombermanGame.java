package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.untility.PathFile;

import java.io.InputStream;


public class BombermanGame extends Application {
    private static final String TITLE = "Bomberman Game made by group 22";
    private static final String ICON_PATH = "/icons/icon.png";
    public static final int SCREEN_WIDTH = 31;
    public static final int SCREEN_HEIGHT = 14;

    public static final int OFFSET = Sprite.SCALED_SIZE / 2;

    public static int BomberSpeed = 2;


    private Canvas canvas;

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

        Playground playground = new Playground();

        // Add scene into stage
        stage.setScene(playground.getScene());
        stage.show();

        // Set stage
        stage.setTitle(BombermanGame.TITLE);
        stage.setResizable(false);
        stage.setMaxWidth(Sprite.SCALED_SIZE * SCREEN_WIDTH + OFFSET);
        stage.setMaxHeight(Sprite.SCALED_SIZE * SCREEN_HEIGHT + OFFSET / 2);

        InputStream stream = PathFile.getStream(BombermanGame.ICON_PATH);
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
                    stage.setTitle(BombermanGame.TITLE + " | " + updateTimes * 2 + " fps");
                    lastTime = System.nanoTime();
                    updateTimes = 0;
                }

                playground.render();
                playground.update();
                updateTimes++;
            }
        };
        timer.start();
    }
}
