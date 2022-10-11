package uet.oop.bomberman.keyboard;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Code {
    List<KeyCode> CODE_LIST = new ArrayList<>(
            Arrays.asList(
                    KeyCode.A, KeyCode.S, KeyCode.W, KeyCode.D,
                    KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN,
                    KeyCode.X, KeyCode.SPACE));
}
