package uet.oop.bomberman.untility;

import uet.oop.bomberman.BombermanGame;

import java.io.InputStream;
import java.util.Objects;

public class PathFile {
    public static InputStream getStream(String path) {
        try {
            return Objects.requireNonNull(BombermanGame.class.getResourceAsStream(path));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static String getPath(String path) {
        return "res\\" + path;
    }
}
