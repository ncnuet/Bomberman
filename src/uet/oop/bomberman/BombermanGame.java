package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.utils.PathFile;
import uet.oop.bomberman.utils.Size;

import java.io.InputStream;

public class BombermanGame extends Application {
    public static final String OS_TYPE = System.getProperty("os.name");
    private static final String TITLE = "Bomberman Game made by group 22";
    private static final String ICON_PATH = "/icons/icon.png";
    private static final Size MAP_VIEW_SIZE = new Size(15, 15);
    public static final int BORDER_WINDOW_X = 15;
    public static final int BORDER_WINDOW_Y = 38;
    public static final int SCENE_WIDTH = MAP_VIEW_SIZE.getWidthAsPixel();
    public static final int SCENE_HEIGHT = MAP_VIEW_SIZE.getHeightAsPixel();
    public static final int WINDOW_WIDTH = SCENE_WIDTH;
    public static final int WINDOW_HEIGHT = SCENE_HEIGHT;

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

        Context context = new Context();

        // Get boundary of screen
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        // Add scene into stage
        stage.setScene(context.getScene());
        stage.show();

        // Setup stage
        stage.setTitle(BombermanGame.TITLE);
        stage.setResizable(false);
//        stage.setFullScreen(true);
//        stage.setMaximized(true);
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
            private static long titleUpdateTimestamp = System.nanoTime();
            private static long updateTimestamp = System.nanoTime();
            private static long frameCount = 0;
            private static final long refreshTitleTime = 1000000000;
            private static final long refreshUpdateTime
                    = (OS_TYPE.equals("Linux"))
                    ? 1000000000 / 100
                    : 0;

            @Override

            public void handle(long now) {
                if (now - updateTimestamp > refreshUpdateTime) {
                    context.render();
                    context.update();

                    updateTimestamp = System.nanoTime();
                    frameCount++;
                }

                if (now - titleUpdateTimestamp > refreshTitleTime) {
                    stage.setTitle(BombermanGame.TITLE + " | " + frameCount + " " + "fps");

                    titleUpdateTimestamp = System.nanoTime();
                    frameCount = 0;
                }
            }
        };

        timer.start();
    }
}
