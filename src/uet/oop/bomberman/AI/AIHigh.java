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

