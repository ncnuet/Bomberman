package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.untility.PathFile;

import java.io.InputStream;

public class BombermanGame extends Application {
    private static final String TITLE = "Bomberman Game made by group 22";
    private static final String ICON_PATH = "/icons/icon.png";
    public static final int APP_TILE_WIDTH = 15;
    public static final int APP_TILE_HEIGHT = 15;
    public static final int BORDER_WINDOW_X = 15;
    public static final int BORDER_WINDOW_Y = 38;
    public static final int SCENE_WIDTH = APP_TILE_WIDTH * Sprite.SCALED_SIZE;
    public static final int SCENE_HEIGHT = APP_TILE_HEIGHT * Sprite.SCALED_SIZE;
    public static final int WINDOW_WIDTH = SCENE_WIDTH + BORDER_WINDOW_X;
    public static final int WINDOW_HEIGHT = SCENE_HEIGHT + BORDER_WINDOW_Y;

    /**
     * Game configuration value
     */
    private static int conf_BomberSpeed = 2;
    private static int conf_flameSegmentLength = 1;
    private static boolean conf_canDetonate = false;
    private static boolean conf_canByBomb = false;
    private static boolean conf_canByFlame = false;
    private static boolean conf_canByWall = false;
    private static boolean conf_mystery = false;
    private static int conf_bombCapacity = 1;
    private static int currentCapacity = 1;

    public static void addCurrentCapacity() {
        if (currentCapacity + 1 <= conf_bombCapacity) {
            currentCapacity++;
        }
    }

    public static void removeCurrentCapacity() {
        if (currentCapacity - 1 >= 0) {
            currentCapacity--;
        }
    }

    public static int getCurrentCapacity() {
        return currentCapacity;
    }

    public static void updateBomberSpeed() {
        final int max = 5;
        if (conf_BomberSpeed < max) conf_BomberSpeed++;
    }

    public static int getBomberSpeed() {
        return conf_BomberSpeed;
    }

    public static void updateFlameLength() {
        final int max = 10;
        if (conf_flameSegmentLength < max) conf_flameSegmentLength++;
    }

    public static void updateBombCapacity() {
        final int max = 5;
        if (conf_bombCapacity < max) conf_bombCapacity++;
    }

    public static int getConf_bombCapacity() {
        return conf_bombCapacity;
    }

    public static void updateCanDetonate() {
        BombermanGame.conf_canDetonate = true;
    }

    public static boolean isConf_canDetonate() {
        return conf_canDetonate;
    }

    public static int getFlameLength() {
        return conf_flameSegmentLength;
    }

    public static void updateCanByWall() {
        BombermanGame.conf_canByWall = true;
    }

    public static boolean isConf_canByWall() {
        return conf_canByWall;
    }

    public static void updateCanByBomb() {
        BombermanGame.conf_canByBomb = true;
    }

    public static boolean isConf_canByBomb() {
        return conf_canByBomb;
    }

    public static void updateCanByFlame() {
        BombermanGame.conf_canByFlame = true;
    }

    public static boolean isConf_canByFlame() {
        return conf_canByFlame;
    }

    public static boolean isConf_mystery() {
        return conf_mystery;
    }

    public static void updateMystery() {
        BombermanGame.conf_mystery = true;
    }

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
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        // Add scene into stage
        stage.setScene(playground.getScene());
        stage.show();

        // Setup stage
        stage.setTitle(BombermanGame.TITLE);
        stage.setResizable(false);
        stage.setMaxWidth(WINDOW_WIDTH);
        stage.setMaxHeight(WINDOW_HEIGHT);
        stage.setX((screenBounds.getWidth() - WINDOW_WIDTH) / 2);
        stage.setY((screenBounds.getHeight() - WINDOW_HEIGHT) / 2);

        // Add app icon
        InputStream stream = PathFile.getStream(BombermanGame.ICON_PATH);
        if (stream != null) {
            stage.getIcons().add(new Image(stream));
        }

        // Set timer action
        AnimationTimer timer = new AnimationTimer() {
            private static long lastTime = System.nanoTime();
            private static long frameCount = 0;
            private static final int refreshRate = 2;
            private static final long refreshTime = 1000000000 / refreshRate;

            @Override
            public void handle(long now) {
                if (now - lastTime > refreshTime) { // Calc fps after half of second. 500,000,000 ns
                    stage.setTitle(
                            BombermanGame.TITLE + " | " + frameCount * refreshRate + " " + "fps");
                    lastTime = System.nanoTime();
                    frameCount = 0;
                }

                playground.render();
                playground.update();
                frameCount++;
            }
        };
        timer.start();
    }
}
