package uet.oop.bomberman.AI;

import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.spriteEntity.enermy.Enemy;
import uet.oop.bomberman.utils.*;

import java.util.ArrayList;
import java.util.List;

public class AILow extends AI {
    private Direction preAnsDirection;

    /**
     * Constructor.
     *
     * @param enemy   enemy that need manage
     * @param context context
     */
    public AILow(Enemy enemy, Context context) {
        super(enemy, context);
        this.resetStepAvailable(6);
    }

    public void reset(){
        this.preAnsDirection = null;
    }

    private List<Direction> getAllDirectionsCanMove() {
        List<Direction> directionsCanMove = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            if (isCanMoveWithDirection(direction)) directionsCanMove.add(direction);
        }

        return directionsCanMove;
    }

    @Override
    public Direction getDirection() {
        if (this.stepAvailable - speed < 0
                || preAnsDirection == null
                || !isCanMoveWithDirection(preAnsDirection)) {

            List<Direction> directionsCanMove = this.getAllDirectionsCanMove();

            if (!directionsCanMove.isEmpty()) {
                int size = directionsCanMove.size();
                int rand = AI.random.nextInt(size);
                preAnsDirection = directionsCanMove.get(rand);
                this.resetStepAvailable(6);
            } else {
                preAnsDirection = null;
            }
        }
        this.stepAvailable -= speed;
        return preAnsDirection;
    }
}
