package uet.oop.bomberman.keyboard;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;
import java.util.*;

public class Keyboard implements Code {
    private final Map<KeyCode, Boolean> keyFlags = new HashMap<>();
    public boolean up, down, left, right, space;

    public Keyboard(Scene scene) {
        // Initialize value
        for (KeyCode key : CODE_LIST) {
            keyFlags.put(key, false);
        }

        // if key pressed, the state corresponding to it will flag
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyFlags.put(event.getCode(), true);
            }
        });

        // otherwise, when key release, the state will be cleared
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyFlags.put(event.getCode(), false);
            }
        });
    }

    public void update() {
        this.up = keyFlags.get(KeyCode.UP) || keyFlags.get(KeyCode.W);
        this.down = keyFlags.get(KeyCode.DOWN) || keyFlags.get(KeyCode.S);
        this.left = keyFlags.get(KeyCode.LEFT) || keyFlags.get(KeyCode.A);
        this.right = keyFlags.get(KeyCode.RIGHT) || keyFlags.get(KeyCode.D);
        this.space = keyFlags.get(KeyCode.SPACE) || keyFlags.get(KeyCode.X);
    }

}
