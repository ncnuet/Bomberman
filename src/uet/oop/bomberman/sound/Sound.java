package uet.oop.bomberman.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import uet.oop.bomberman.BombermanGame;

import java.util.Objects;

public class Sound extends Thread {
    public static final String BG_SOUND = "bg_sound";
    public static final String BOM_EXPLODE = "bom_explode";

    private Clip clip;
    private AudioInputStream audioInputStream;

    /**
     * Constructor.
     *
     * @param sound_name name of sound
     * @throws Exception e
     */
    public Sound(String sound_name) throws Exception {
        try {
            String relativePath = "/sound/" + sound_name + ".wav";
            this.clip = AudioSystem.getClip();
            this.audioInputStream = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(BombermanGame.class.getResourceAsStream(relativePath)));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }


    @Override
    public void run() {
        try {
            this.clip.open(this.audioInputStream);
            this.clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void interrupt() {
        try {
            this.clip.stop();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
