package uet.oop.bomberman.sound;

import uet.oop.bomberman.untility.Convert;

public class Sound {
    /**
     * Define audio file name.
     */
    private static final String BG_SOUND = "bg_sound";
    private static final String BOM_EXPLODE = "bom_explode";
    private static final String BOM_SET = "bom_set";

    /**
     * Define Audio object which ready to play.
     */
    public static final Sound bg_sound = new Sound(Sound.BG_SOUND, true);
    public static final Sound bom_explode = new Sound((Sound.BOM_EXPLODE));
    public static final Sound bom_set = new Sound(Sound.BOM_SET);

    /**
     * Internal property.
     */
    private final String relativePath;
    private final boolean continuous;
    private int volume = 100;
    private PlaySound playSound;

    public int getVolume() {
        return this.volume;
    }

    /**
     * Convert volume from linear scale to logarithmic scale.
     * Pass it as parameter.
     *
     * @param volume value in linear scale (0-100)
     * @throws Exception exception.
     */
    public void setVolume(int volume) throws Exception {
        if (volume >= 0 && volume <= 100) {
            this.playSound.setVolume(Convert.LinearToDecibel(volume));
        } else {
            throw new IllegalArgumentException("Volume not valid: " + volume);
        }
    }

    /**
     * Constructor.
     *
     * @param sound_name name of sound
     * @param continuous is loop
     */
    public Sound(String sound_name, boolean continuous) {
        this.relativePath = "/sound/" + sound_name + ".wav";
        this.continuous = continuous;
    }

    /**
     * Constructor.
     * Default: Not loop
     *
     * @param sound_name name of sound
     */
    public Sound(String sound_name) {
        this.relativePath = "/sound/" + sound_name + ".wav";
        this.continuous = false;
    }

    /**
     * Start to play audio in a new Thread.
     * Auto close after finishing and terminate Thread.
     */
    public void start() {
        try {
            this.playSound = new PlaySound(relativePath, this.continuous);
            this.setVolume(50);
            playSound.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
