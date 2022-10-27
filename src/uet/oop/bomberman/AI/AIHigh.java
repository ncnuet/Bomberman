package uet.oop.bomberman.AI;

import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.spriteEntity.enermy.Enemy;
import uet.oop.bomberman.utils.Direction;

import java.util.List;

public class AIHigh extends AI {
    private Direction preDirection;
    private final AILow ai_low;

    /**
     * Constructor.
     *
     * @param enemy   enemy that need manage
     * @param context context
     */
    public AIHigh(Enemy enemy, Context context) {
        super(enemy, context);
        this.ai_low = new AILow(enemy, context);

        this.resetStepAvailable(2);
    }

    private List<Direction> getDirectionsToBomber() {
        PathFinder finder = new PathFinder(enemy, context);
        return finder.find();
    }

    @Override
    public Direction getDirection() {
        if (this.stepAvailable - speed < 0
                || preDirection == null
                || !isCanMoveWithDirection(preDirection)) {
            List<Direction> dirsToBomber = this.getDirectionsToBomber();

            System.out.println(dirsToBomber); // Log

            if (!dirsToBomber.isEmpty()) {
                preDirection = dirsToBomber.get(0);
                this.resetStepAvailable(2);
            } else {
                preDirection = ai_low.getDirection();
                ai_low.reset();
                this.resetStepAvailable(2);
//                preDirection = null;
            }
        }
        this.stepAvailable -= speed;
        return preDirection;
    }
}