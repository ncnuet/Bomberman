package uet.oop.bomberman.keyboard;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.util.Direction;
import uet.oop.bomberman.util.Distance;

public class KeyControl extends Keyboard {
    private int x, y;

    public KeyControl(Scene scene) {
        super(scene);
    }

    public void update() {
        // Update direction
        boolean up = keyFlags.get(KeyCode.UP) || keyFlags.get(KeyCode.W);
        boolean down = keyFlags.get(KeyCode.DOWN) || keyFlags.get(KeyCode.S);
        boolean left = keyFlags.get(KeyCode.LEFT) || keyFlags.get(KeyCode.A);
        boolean right = keyFlags.get(KeyCode.RIGHT) || keyFlags.get(KeyCode.D);

        this.x = (left ? -1 : 0) + (right ? 1 : 0);
        this.y = (up ? -1 : 0) + (down ? 1 : 0);
    }

    public Direction getDirection() {
        Direction direction = null;

        if (x < 0) direction = Direction.LEFT;
        if (x > 0) direction = Direction.RIGHT;
        if (y < 0) direction = Direction.UP;
        if (y > 0) direction = Direction.DOWN;

        return direction;
    }

    public boolean isMoving() {
        return (x != 0 || y != 0);
    }

    public Distance getDistance() {
//        if (x != 0) offsetX -= x;
//        if (y != 0) offsetY -= y;
        return new Distance(
                x * BombermanGame.getBomberSpeed(),
                y * BombermanGame.getBomberSpeed());
    }

    public boolean isSpacePressed() {
        return keyFlags.get(KeyCode.SPACE) || keyFlags.get(KeyCode.X);
    }
}
