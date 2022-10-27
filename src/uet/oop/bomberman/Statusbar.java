package uet.oop.bomberman;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.graphics.Sprite;

public class Statusbar {
    private Canvas canvas;
    private GraphicsContext gc;

    private static final Image heart = Sprite.heart.getFxImage();
    private static final Image clock = Sprite.clock.getFxImage();
    private static final Image by_wall = Sprite.powerup_wallpass.getFxImage();
    private static final Image by_flame = Sprite.powerup_flamepass.getFxImage();
    private static final Image by_bomb = Sprite.powerup_bombpass.getFxImage();

    private int width;
    private int height;

    public Canvas getCanvas() {
        return canvas;
    }

    public Statusbar() {
        this.width = BombermanGame.SCENE_WIDTH;
        this.height = Sprite.SCALED_SIZE * 2 + 10;
        this.canvas = new Canvas(width, height);
        this.gc = canvas.getGraphicsContext2D();

        this.render();
    }

    public void render() {
        this.gc.clearRect(0, 0, width, height);
        this.gc.setFill(Color.CADETBLUE);
        this.gc.fillRect(0, 0, width, height);
        this.showHeart();
        this.showTime();
        this.showSpeed();
        this.showBomb();
        this.showScore();
        this.showPowerUp();
    }

    private void showHeart() {
        setText("Heart: ", 5, 5);
        for (int i = 0; i < GameValue.getHeart(); i++) {
            this.gc.drawImage(heart, 80 + i * Sprite.SCALED_SIZE, 0);
        }
    }

    private void showTime() {
        this.gc.drawImage(clock, 200, 0);
        setText(Integer.toString(GameValue.getTime()), 240, 5);
    }

    private void showBomb() {
        setText("Bomb: ", 320, 5);
        setText(Integer.toString(GameValue.getCurrentCapacity()), 400, 5);
    }

    private void showSpeed() {
        setText("Speed: ", 460, 5);
        setText(Integer.toString(GameValue.getBomberSpeed()), 540, 5);
    }

    private void showScore() {
        setText("Score: ", 600, 5);
        setText(Integer.toString(GameValue.getScore()), 680, 5);
    }

    private void showPowerUp() {
        setText("PowerUp ", 5, 42);
        if (GameValue.isCanByFlame()) {
            this.gc.drawImage(by_flame, 96, 32);
        }
        if (GameValue.isCanByBomb()) {
            this.gc.drawImage(by_bomb, 128, 32);
        }
        if (GameValue.isCanByWall()) {
            this.gc.drawImage(by_wall, 160, 32);
        }
    }

    private void setText(String text, int crdX, int crdY) {
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.TOP);
        gc.setFont(new Font("Minecraft", 20));
        this.gc.fillText(text, crdX, crdY);
    }
}
