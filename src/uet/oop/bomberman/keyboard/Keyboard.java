package uet.oop.bomberman.keyboard;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Keyboard {
    private static final byte MAX = 120; // max storage in order to keep state
    private final boolean[] keyFlags = new boolean[MAX];
    public boolean up, down, left, right, space;

    public Keyboard(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyFlags[event.getCode().getCode()] = true;
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyFlags[event.getCode().getCode()] = false;
            }
        });
    }

    public void update() {
        this.up = keyFlags[KeyCode.UP.getCode()] || keyFlags[KeyCode.W.getCode()];
        this.down = keyFlags[KeyCode.DOWN.getCode()] || keyFlags[KeyCode.S.getCode()];
        this.left = keyFlags[KeyCode.LEFT.getCode()] || keyFlags[KeyCode.A.getCode()];
        this.right = keyFlags[KeyCode.RIGHT.getCode()] || keyFlags[KeyCode.D.getCode()];
        this.space = keyFlags[KeyCode.SPACE.getCode()] || keyFlags[KeyCode.X.getCode()];
    }

}
