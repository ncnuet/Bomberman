package uet.oop.bomberman.sound;

import uet.oop.bomberman.exceptions.AudioNotReadyException;
import uet.oop.bomberman.untility.PathFile;

import javax.sound.sampled.*;

/**
 * PlaySound class play a sound read from stream.
 */
public class PlaySound extends Thread implements AudioControl<Float> {
    private Clip clip;
    private final boolean continuous;
    private float volume;

    /**
     * Get current logarithm scale value.
     *
     * @return logarithm scale value (dB)
     */
    @Override
    public Float getVolume() {
        return volume;
    }

    /**
     * Set current volume value.
     * If clip being started, apply this change. (Thread State = RUNNABLE)
     *
     * @param volume volume by decibel
     */
    @Override
    public void setVolume(Float volume) {
        this.volume = volume;
        try {
            if (this.getState().equals(State.RUNNABLE)) this.applyVolume();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * Apply volume to current audio clip.
     *
     * @throws AudioNotReadyException exception.
     */
    private void applyVolume() throws AudioNotReadyException {
        try {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        } catch (Exception e) {
            throw new AudioNotReadyException();
        }
    }

    /**
     * Constructor.
     *
     * @param path       audio relative file path.
     * @param continuous is continuous.
     */
    public PlaySound(String path, boolean continuous) {
        this.continuous = continuous;

        try {
            this.clip = AudioSystem.getClip();
            AudioInputStream stream = AudioSystem.getAudioInputStream(
                    PathFile.getStream(path));

            this.clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Open file as stream and play it in a Thread.
     * Auto close and terminate Thread if play completely.
     */
    @Override
    public void run() {
        try {
            // Config setting before playing
            if (this.continuous) clip.loop(Clip.LOOP_CONTINUOUSLY);
            this.applyVolume();
            this.clip.start();

            // If finish playing audio, kill this Thread
            clip.addLineListener((LineEvent event) -> {
                if (event.getType().toString().equals("Stop")) {
                    this.interrupt();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
