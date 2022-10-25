package uet.oop.bomberman.AI;

import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.character.Bomber;
import uet.oop.bomberman.entities.spriteEntity.character.enermy.Enemy;
import uet.oop.bomberman.entities.spriteEntity.obstacle.bomb.FlameSegment;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.util.Convert;
import uet.oop.bomberman.util.Direction;
import uet.oop.bomberman.util.Distance;
import uet.oop.bomberman.util.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AILow extends AI {
    private int stepAvailable;
    private final Random random;
    private final Enemy enemy;
    private final int speed;
    private final Playground playground;
    private Direction preAnsDirection;

    public AILow(Enemy enemy, Playground playground) {
        this.random = new Random();
        this.enemy = enemy;
        this.speed = enemy.getSpeed();
        this.playground = playground;
        resetStepAvailable();
    }


    private void resetStepAvailable() {
        this.stepAvailable = Sprite.SCALED_SIZE + random.nextInt(6) * Sprite.SCALED_SIZE;
    }

    @Override
    public Direction getDirection() {
        if (this.stepAvailable - speed < 0
                || preAnsDirection == null
                || !isCanMoveWithDirection(preAnsDirection)) {

            List<Direction> directionsCanMove = this.canMove();

            if (!directionsCanMove.isEmpty()) {
                int size = directionsCanMove.size();
                int rand = this.random.nextInt(size);
                preAnsDirection = directionsCanMove.get(rand);
                resetStepAvailable();
            }
        }
        this.stepAvailable -= speed;
        return preAnsDirection;
    }

    private boolean isCanPass(Entity entity) {
        return entity == enemy
                || entity instanceof FlameSegment
                || entity instanceof Grass
                || entity instanceof Bomber;
    }

    private boolean isCanMoveWithDirection(Direction direction) {
        final int size = Sprite.SCALED_SIZE - 1;
        if (direction == null) return false;
        Distance distance = switch (direction) {
            case UP -> new Distance(0, -speed);
            case RIGHT -> new Distance(speed, 0);
            case DOWN -> new Distance(0, speed);
            case LEFT -> new Distance(-speed, 0);
        };

        for (int i = 0; i < 4; i++) {
            int x = this.enemy.getX() + distance.getX() + (i % 2) * size;
            int y = this.enemy.getY() + distance.getY() + (i / 2) * size;
            Point point = Convert.pixelToTile(new Point(x, y));
            Entity entity = this.playground.getEntity(point);
            if (!isCanPass(entity)) return false;
        }

        return true;
    }

    private List<Direction> canMove() {
        List<Direction> directionsCanMove = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            if (isCanMoveWithDirection(direction)) directionsCanMove.add(direction);
        }

        return directionsCanMove;
    }
}
