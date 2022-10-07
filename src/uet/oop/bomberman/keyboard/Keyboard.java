package uet.oop.bomberman.keyboard;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.untility.Direction;
import uet.oop.bomberman.untility.Distance;

import java.util.*;

public class Keyboard implements Code {
    protected final Map<KeyCode, Boolean> keyFlags = new HashMap<>();

    public Distance distance;
    public boolean moving;
    public Direction direction;

    public Keyboard(Scene scene) {
        // Initialize value
        this.distance = new Distance(0, 0);
        this.moving = false;
        this.direction = Direction.RIGHT;

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
}
