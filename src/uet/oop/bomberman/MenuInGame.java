package uet.oop.bomberman;

import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuInGame {
    private GraphicsContext gc;
    private Canvas canvas;
    private MenuItem[] menu;
    private int height, width;
    private Context context;

    public MenuInGame(Canvas canvas, Context context) {
        this.context = context;
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.width = BombermanGame.SCENE_WIDTH;
        this.height = BombermanGame.SCENE_HEIGHT;
        this.menu = new MenuItem[]{
                new MenuItem("Continue", new Rectangle(80, 295, 200, 40)),
                new MenuItem("Sound", new Rectangle(80, 355, 200, 40)),
                new MenuItem("Menu", new Rectangle(80, 415, 200, 40))
        };

        this.gc.setFill(Color.rgb(47, 39, 70, 0.8));
        this.gc.fillRect(0, 0, width, height);

        this.canvas.setOnMouseMoved(e -> {
            this.drawMenu(e.getX(), e.getY());
            if (!BombermanGame.IS_PAUSE) {
                this.canvas.setOnMouseMoved(v -> {
                });
            }
        });

        this.canvas.setOnMouseClicked(e -> {
            if (menu[0].rect.contains(e.getX(), e.getY())) {
                BombermanGame.IS_PAUSE = false;
                this.canvas.setOnMouseMoved(v -> {
                });
            }
            if (menu[1].rect.contains(e.getX(), e.getY())) {
                this.context.showVolumeControl();
            }
            if (menu[2].rect.contains(e.getX(), e.getY())) {
                this.context.setGameMenu();
                this.canvas.setOnMouseMoved(v -> {
                });
            }
        });



        drawMenu(0, 0);
    }

    private void drawMenu(double crdX, double crdY) {
//        this.gc.clearRect(0, 0, width, height);

        for (MenuItem menuItem : menu) {
            if (menuItem.rect.contains(crdX, crdY)) {
                this.drawTextWithBackground(menuItem.text, menuItem.rect.x, menuItem.rect.y);
            } else {
                this.drawText(menuItem.text, menuItem.rect.x, menuItem.rect.y);
            }
        }
    }

    private void drawText(String text, int crdX, int crdY) {
        gc.setFill(Color.WHITE);
        gc.fillRect(crdX - 10, crdY - 10, 180, 40);

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
