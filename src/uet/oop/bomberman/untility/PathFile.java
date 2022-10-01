package uet.oop.bomberman.untility;

import uet.oop.bomberman.BombermanGame;

import java.io.InputStream;
import java.util.Objects;

public class PathFile {
    public static InputStream getStream(String path) {
        return Objects.requireNonNull(BombermanGame.class.getResourceAsStream(path));
    }

    public static String getPath(String path) {
        return "res\\" + path;
    }
}
