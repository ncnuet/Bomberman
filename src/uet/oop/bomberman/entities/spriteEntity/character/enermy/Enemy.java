package uet.oop.bomberman.entities.spriteEntity.character.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AI;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.spriteEntity.character.Character;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.util.Direction;
import uet.oop.bomberman.util.Distance;

public abstract class Enemy extends Character {
    private static final Image mob_dead1 = Sprite.mob_dead1.getFxImage();
    private static final Image mob_dead2 = Sprite.mob_dead2.getFxImage();
    private static final Image mob_dead3 = Sprite.mob_dead3.getFxImage();

    private final Image img_default;
    private int speed = 2;
    private AI ai;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAi(AI ai) {
        this.ai = ai;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crdX      position in predefined
     * @param crdY      position in predefined
     * @param spriteImg image
     */
    public Enemy(int crdX, int crdY, Image spriteImg, Image dead_img, Playground playground) {
        super(crdX, crdY, spriteImg, playground);
        this.img_default = dead_img;
        this.setTimeToExplode(80);
        this.setDirection(Direction.LEFT);
    }

    @Override
    public void kill() {
        Sound.dead.start();
        this.setAlive(false);
    }

    @Override
    protected void selectSpriteOnDead() {
        this.setSpriteImg(Sprite.selectSprite(
                this.getFrameCount().getFrame(), 80,
                img_default, mob_dead1, mob_dead2, mob_dead3
        ));
    }

    @Override
    public void update() {
        super.update();
        if (this.isAlive()) moveSprite(new Distance(0, 0));
    }

    @Override
    protected void moveSprite(Distance distance) {
        this.move(this.ai.getDirection());
    }

    private void move(Direction direction) {
        switch (direction) {
            case UP -> this.setY(this.getY() - this.speed);
            case DOWN -> this.setY(this.getY() + this.speed);
            case LEFT -> this.setX(this.getX() - this.speed);
            case RIGHT -> this.setX(this.getX() + this.speed);
            default -> this.setX(this.getX());
        }
    }
}
