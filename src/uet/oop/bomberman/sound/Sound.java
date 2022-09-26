package uet.oop.bomberman.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.untility.Convert;

import java.util.Objects;

public class Sound extends Thread {
    public static final String BG_SOUND = "bg_sound";
    public static final String BOM_EXPLODE = "bom_explode";

    public static final Sound bg_sound = new Sound(Sound.BG_SOUND, true);
    public static final Sound bom_explode = new Sound((Sound.BOM_EXPLODE));

    private Clip clip;
    private AudioInputStream audioInputStream;
    private boolean isContinuous;
    private int volume = 100;

    public boolean isContinuous() {
        return isContinuous;
    }

    public void setContinuous(boolean continuous) {
        isContinuous = continuous;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) throws Exception {
        if (volume >= 0 && volume <= 100) {
            try {
                FloatControl control =
                        (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
                control.setValue(Convert.LinearToDecibel(volume));
            } catch (Exception e) {
                throw new Exception("Unable to set volume now");
            }
        } else {
            throw new IllegalArgumentException("Volume not valid: " + volume);
        }
    }

    /**
     * Constructor.
     *
     * @param sound_name   name of sound
     * @param isContinuous is loop
     */
    public Sound(String sound_name, boolean isContinuous) {
        this.setContinuous(isContinuous);
        this.initial(sound_name);
    }

    /**
     * Constructor.
     *
     * @param sound_name name of sound
     */
    public Sound(String sound_name) {
        this.setContinuous(false);
        this.initial(sound_name);
    }

    /**
     * Initial.
     *
     * @param sound_name name of sound
     */
    private void initial(String sound_name) {
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
            if (this.isContinuous()) {
                this.clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            // Set volume
            FloatControl control =
                    (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.volume = Convert.DecibelToLinear(control.getValue());

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
