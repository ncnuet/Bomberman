package uet.oop.bomberman.util;

import uet.oop.bomberman.BombermanGame;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class PathFile {
    public static InputStream getStream(String path) {
        return Objects.requireNonNull(BombermanGame.class.getResourceAsStream(path));
    }

    public static String getPath(String path) {
        try {
            return Objects.requireNonNull(BombermanGame.class.getResource(path)).getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "res" + path;
    }

    public static URL getURL(String path) throws MalformedURLException {
        try {
            return Objects.requireNonNull(BombermanGame.class.getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new URL("file","", PathFile.getPath(path));
    }
}
