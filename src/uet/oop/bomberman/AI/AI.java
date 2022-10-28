package uet.oop.bomberman.AI;

import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.bomb.FlameSegment;
import uet.oop.bomberman.entities.spriteEntity.bomber.Bomber;
import uet.oop.bomberman.entities.spriteEntity.enermy.Enemy;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.utils.Direction;
import uet.oop.bomberman.utils.Distance;

import java.util.Random;

public abstract class AI {


    /**
     * Constructor.
     */
    public AI(Enemy enemy, Context context) {
        this.enemy = enemy;
        this.context = context;
        this.speed = enemy.getSpeed();
    }

    public abstract Direction getDirection();

    protected void resetStepAvailable(int length) {
        this.stepAvailable = Sprite.SCALED_SIZE + random.nextInt(length) * Sprite.SCALED_SIZE;
    }

    private boolean isCanPass(Entity entity) {
        return entity == enemy
                || entity instanceof FlameSegment
                || entity instanceof Grass
                || entity instanceof Bomber;
    }

    protected boolean isCanMoveWithDirection(Direction direction) {
        final int size = Sprite.SCALED_SIZE - 1;
        if (direction == null) return false;
        Distance distance = switch (direction) {
            case UP -> new Distance(0, -speed);
            case RIGHT -> new Distance(speed, 0);
            case DOWN -> new Distance(0, speed);
            case LEFT -> new Distance(-speed, 0);
        };

        for (int i = 0; i < 4; i++) {
            int x = this.enemy.getXAsPixel() + distance.getX() + (i % 2) * size;
            int y = this.enemy.getYAsPixel() + distance.getY() + (i / 2) * size;

            Entity entity = this.context.getEntity(Coordinate.createCrdByPixel(x,y), false);
            if (!isCanPass(entity)) return false;
        }

        return true;
    }
}

