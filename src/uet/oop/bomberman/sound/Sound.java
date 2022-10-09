package uet.oop.bomberman.sound;

import uet.oop.bomberman.util.Convert;

/**
 * Sound class control a special sound.
 */
public class Sound implements AudioControl<Integer>, AudioPack {
    private final String relativePath;
    private final boolean continuous;
    private PlaySound playSound;

    /**
     * Return volume value after converted.
     *
     * @return linear volume value.
     */
    public Integer getVolume() {
        return Convert.DecibelToLinear(this.playSound.getVolume());
    }

    /**
     * Convert volume from linear scale to logarithmic scale.
     * Pass it as parameter.
     *
     * @param volume value in linear scale (0-100)
     * @throws Exception exception.
     */
    @Override
    public void setVolume(Integer volume) throws Exception {
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
