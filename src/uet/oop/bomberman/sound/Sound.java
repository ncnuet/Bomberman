package uet.oop.bomberman.sound;

import uet.oop.bomberman.untility.Convert;

public class Sound {
    private static final String BG_SOUND = "bg_sound";
    private static final String BOM_EXPLODE = "bom_explode";
    private static final String BOM_SET = "bom_set";

    public static final Sound bg_sound = new Sound(Sound.BG_SOUND, true);
    public static final Sound bom_explode = new Sound((Sound.BOM_EXPLODE));
    public static final Sound bom_set = new Sound(Sound.BOM_SET);

    private String relativePath;
    private boolean continuous;
    private int volume = 100;

    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
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
     * @param continuous is loop
     */
    public Sound(String sound_name, boolean continuous) {
        this.setContinuous(continuous);
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
