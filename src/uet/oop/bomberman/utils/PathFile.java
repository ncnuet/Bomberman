package uet.oop.bomberman.utils;

import uet.oop.bomberman.BombermanGame;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class PathFile {
    public static InputStream getStream(String path) {
        return BombermanGame.class.getResourceAsStream(path);
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
        return new URL("file","", PathFile.getPath(path));
    }
}
