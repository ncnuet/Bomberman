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

public class Welcome {
    private Canvas canvas;
    private GraphicsContext gc;
    private Image image;
    private final MenuItem[] menu;

    private Context context;

    public Canvas getCanvas() {
        return canvas;
    }

    public Welcome(Context context) throws FileNotFoundException {
        this.context = context;
        this.canvas = new Canvas(800, 600);
        InputStream stream = new FileInputStream(PathFile.getPath("/images/background.jpg"));
        this.image = new Image(stream);
        this.gc = this.canvas.getGraphicsContext2D();

        this.menu = new MenuItem[]{
                new MenuItem("Play", new Rectangle(40, 295, 200, 40)),
                new MenuItem("Play on Lan", new Rectangle(40, 335, 200, 40)),
                new MenuItem("Sound", new Rectangle(40, 375, 200, 40)),
                new MenuItem("Exit", new Rectangle(40, 415, 200, 40))
        };

        drawMenu(0, 0);

        this.canvas.setOnMouseMoved(e -> {
            this.drawMenu(e.getX(), e.getY());
        });

        this.canvas.setOnMouseClicked(e -> {
            if (menu[0].rect.contains(e.getX(), e.getY())) {
                this.context.setGamePlay();
            }
            if (menu[2].rect.contains(e.getX(), e.getY())) {
                this.context.showVolumeControl();
            }
            if (menu[3].rect.contains(e.getX(), e.getY())) {
                System.exit(0);
            }
        });
    }

    private void drawMenu(double crdX, double crdY) {
        this.gc.clearRect(0, 0, 800, 600);
        this.gc.drawImage(image, 0, 0);

        for (MenuItem menuItem : menu) {
            if (menuItem.rect.contains(crdX, crdY)) {
                this.drawTextWithBackground(menuItem.text, menuItem.rect.x, menuItem.rect.y);
            } else {
                this.drawText(menuItem.text, menuItem.rect.x, menuItem.rect.y);
            }
        }
    }

    private void drawText(String text, int crdX, int crdY) {
        gc.setFill(Color.rgb(47, 39, 70));
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.TOP);
        gc.setFont(new Font("Minecraft", 24));

        this.gc.fillText(text, crdX, crdY);
    }

    private void drawTextWithBackground(String text, int crdX, int crdY) {
        gc.setFill(Color.rgb(47, 39, 70));
        gc.fillRect(crdX - 10, crdY - 10, 180, 40);

        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setTextBaseline(VPos.TOP);
        gc.setFont(new Font("Minecraft", 24));

        this.gc.fillText(text, crdX, crdY);
    }
}
