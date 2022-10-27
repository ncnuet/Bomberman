import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;

public class Statusbar {
    private Canvas canvas;
    private GraphicsContext gc;

    public Canvas getCanvas() {
        return canvas;
    }

    public Statusbar(){
        this.canvas = new Canvas(BombermanGame.SCENE_WIDTH,50);
        this.gc = canvas.getGraphicsContext2D();

        this.gc.setFill(Color.DARKSEAGREEN);
    }

}
