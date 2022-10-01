package uet.oop.bomberman.sound;

import uet.oop.bomberman.untility.Convert;
import uet.oop.bomberman.untility.PathFile;

import javax.sound.sampled.*;
import java.util.Objects;

public class PlaySound extends Thread {
    private Clip clip;
    private String path;
    private boolean isContinuous;
    private float volume;

    public boolean isContinuous() {
        return isContinuous;
    }

    public void setContinuous(boolean continuous) {
        isContinuous = continuous;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) throws Exception {
        try {
            FloatControl control =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        } catch (Exception e) {
            throw new Exception("Unable to set volume now");
        }
    }

    public PlaySound(String path) {
        this.path = path;
        try {
            this.clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(PathFile.getStream(this.path));
            this.clip.open(audioInputStream);
            this.clip.start();
            if (this.isContinuous()) clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Set volume
//                    FloatControl control =
//                            (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
//                    this.volume = Convert.DecibelToLinear(control.getValue());

            clip.addLineListener((LineEvent event) -> {
                if (event.getType().toString().equals("Stop")) {
                    this.interrupt();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
