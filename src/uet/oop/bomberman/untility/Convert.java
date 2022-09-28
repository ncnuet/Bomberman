package uet.oop.bomberman.untility;

public class Convert {
    public static int DecibelToLinear(float db) {
        final float threshold = 20f;
        return (int) (Math.pow(10, db / threshold) * 100);
    }

    public static float LinearToDecibel(int linear) {
        final float threshold = 20f;
        return threshold * (float) Math.log10(linear / 100.0);
    }
}
