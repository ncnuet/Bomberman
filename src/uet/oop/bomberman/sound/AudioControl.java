package uet.oop.bomberman.sound;

/**
 * Supply method for manage a sound, include set volume,...
 */
public interface AudioControl<N extends Number> {

    void setVolume(N volume) throws Exception;

    N getVolume();
}
