package uet.oop.bomberman.entities.spriteEntity.enermy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AI;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.MovableEntity;
import uet.oop.bomberman.entities.spriteEntity.bomb.Flame;
import uet.oop.bomberman.entities.spriteEntity.bomb.FlameSegment;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.utils.Direction;
import uet.oop.bomberman.utils.Distance;

public abstract class Enemy extends MovableEntity {
    private static final Image mob_dead1 = Sprite.mob_dead1.getFxImage();
    private static final Image mob_dead2 = Sprite.mob_dead2.getFxImage();
    private static final Image mob_dead3 = Sprite.mob_dead3.getFxImage();
    private Image img_default;
    private Image[] left_sprite;
    private Image[] right_sprite;

    private int speed = 2;
    private AI ai;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAI(AI ai) {
        this.ai = ai;
    }

    protected void setLeftSprites(Image[] images) {
        this.left_sprite = images;
    }

    protected void setRightSprites(Image[] images) {
        this.right_sprite = images;
    }

    protected void setDeadSprite(Image image) {
        this.img_default = image;
    }

    /**
     * Constructor.
     * Create new Entity with position and it's image
     *
     * @param crd       coordinate
     * @param spriteImg image
     */
    public Enemy(Coordinate crd, Image spriteImg, Context context) {
        super(crd, spriteImg, context);

        this.img_default = spriteImg;
        this.left_sprite = null;
        this.right_sprite = null;

        this.setTimeToExplode(RENDER_TIME);
        this.setDirection(Direction.LEFT);
    }

    /**
     * Move with instruction from AI
     *
     * @param direction direction
     */
    private void moveByDirection(Direction direction) {
        switch (direction) {
            case UP -> this.setYAsPixel(this.getYAsPixel() - this.speed);
            case DOWN -> this.setYAsPixel(this.getYAsPixel() + this.speed);
            case LEFT -> this.setXAsPixel(this.getXAsPixel() - this.speed);
            case RIGHT -> this.setXAsPixel(this.getXAsPixel() + this.speed);
            default -> this.setXAsPixel(this.getXAsPixel());
        }
    }

    @Override
    public void kill() {
        Sound.dead.start();
        this.setAlive(false);
    }

    @Override
    protected void updateSpriteOnDead() {
        this.setSprite(Sprite.selectSprite(
                this.getFrameCounter().getFrame(), RENDER_TIME,
                img_default, mob_dead1, mob_dead2, mob_dead3
        ));
    }

    @Override
    public void update() {
        super.update();
        if (this.isAlive()){
            moveEntity(new Distance(0, 0));
            collide(new Distance(0,0));
        }
    }

    @Override
    protected void moveEntity(Distance distance) {
        this.moveByDirection(this.ai.getDirection());
    }

    @Override
    protected void collide(Distance distance) {
        Entity entity = this.context.getEntity(this.getCoordinate(), true);

        if (entity instanceof FlameSegment){
            this.kill();
        }
    }

    @Override
    protected void updateSpriteOnRunning() {
        switch (this.getDirection()) {
            case RIGHT, DOWN -> this.setSprite(
                    Sprite.selectSprite(
                            this.getFrame(), RENDER_TIME, left_sprite));
            case LEFT, UP -> this.setSprite(
                    Sprite.selectSprite(
                            this.getFrame(), RENDER_TIME, right_sprite));
        }
    }
}
