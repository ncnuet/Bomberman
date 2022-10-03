package uet.oop.bomberman.sound;

/**
 * Define Audio object which ready to play.
 */
public interface AudioPack {

    Sound bg_sound = new Sound(AudioFileName.BG_SOUND, true);
    Sound bom_explode = new Sound((AudioFileName.BOM_EXPLODE));
    Sound bom_set = new Sound(AudioFileName.BOM_SET);
    Sound end_game = new Sound(AudioFileName.END_GAME);
    Sound cryst_up = new Sound(AudioFileName.CRYST_UP);

}
