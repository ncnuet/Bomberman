package uet.oop.bomberman;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import uet.oop.bomberman.utils.PathFile;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class EndGameScreen {
    private Canvas canvas;
    private GraphicsContext gc;
    private Image image;

    private Context context;

    public Canvas getCanvas() {
        return canvas;
    }

    public EndGameScreen(Context context) throws FileNotFoundException {
        this.context = context;
        this.canvas = new Canvas(800, 600);
        InputStream stream = new FileInputStream(PathFile.getPath("/images/background_2.jpg"));
        this.image = new Image(stream);
        this.gc = this.canvas.getGraphicsContext2D();
        this.gc.clearRect(0, 0, 800, 600);
        this.gc.drawImage(image, 0, 0);

        drawText("Your score: ", 230, 200);
        drawText(Integer.toString(GameValue.getScore()),400, 300);

        Rectangle rect = new Rectangle(0,0,800,600);

        this.canvas.setOnMouseClicked(e -> {
            if (rect.contains(e.getX(),e.getY())){
                this.canvas.setOnMouseClicked(v->{});
                this.context.setGameMenu();
            }
        });
    }

    private void drawText(String text, int crdX, int crdY) {
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.TOP);
        gc.setFont(new Font("Minecraft", 64));

        this.gc.fillText(text, crdX, crdY);
    }
}
