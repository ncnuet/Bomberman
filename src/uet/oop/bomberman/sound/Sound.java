package uet.oop.bomberman.sound;

import javax.sound.sampled.*;

import uet.oop.bomberman.untility.Convert;
import uet.oop.bomberman.untility.PathFile;

public class Sound {
    public static final String BG_SOUND = "bg_sound";
    public static final String BOM_EXPLODE = "bom_explode";
    public static final String BOM_SET = "bom_set";

    public static final Sound bg_sound = new Sound(Sound.BG_SOUND, true);
    public static final Sound bom_explode = new Sound((Sound.BOM_EXPLODE));
    public static final Sound bom_set = new Sound(Sound.BOM_SET);

    private String relativePath;
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
            float volumeInDb = Convert.LinearToDecibel(volume);
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
            this.relativePath = "/sound/" + sound_name + ".wav";
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    public void start() {
        PlaySound playSound = new PlaySound(relativePath);
        playSound.setContinuous(this.isContinuous());
        playSound.start();
    }

}
