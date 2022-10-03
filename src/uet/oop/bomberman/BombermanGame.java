package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Screen;
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

    private static int conf_BomberSpeed = 2;
    private static int conf_FlameSegmentLength = 1;
    private static boolean conf_canDetonate = false;
    private static boolean conf_canByBomb = false;
    private static boolean conf_canByFlame = false;
    private static boolean conf_canByWall = false;
    private static int bombCapacity = 1;

    public static void updateBomberSpeed() {
        final int max = 5;
        if (conf_BomberSpeed < max) conf_BomberSpeed++;
    }

    public static int getBomberSpeed() {
        return conf_BomberSpeed;
    }

    public static void updateFlameLength() {
        final int max = 10;
        if (conf_FlameSegmentLength < max) conf_FlameSegmentLength++;
    }

    public static void updateBombCapacity() {
        final int max = 5;
        if (bombCapacity < max) bombCapacity++;
    }

    public static int getBombCapacity() {
        return bombCapacity;
    }

    public static void updateCanDetonate() {
        conf_canDetonate = true;
    }

    public static boolean isConf_canDetonate() {
        return conf_canDetonate;
    }

    public static int getFlameLength() {
        return conf_FlameSegmentLength;
    }

    public static void updateCanByWall() {
        conf_canByWall = true;
    }

    public static boolean isConf_canByWall() {
        return conf_canByWall;
    }

    public static void updateCanByBomb() {
        conf_canByBomb = true;
    }

    public static boolean isConf_canByBomb() {
        return conf_canByBomb;
    }

    public static void updateCanByFlame() {
        conf_canByFlame = true;
    }

    public static boolean isConf_canByFlame() {
        return conf_canByFlame;
    }

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

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        stage.setX(200);
        stage.setY(200);

        System.out.println("Height: " + screenBounds.getHeight() + " Width: " + screenBounds.getWidth());

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
