package uet.oop.bomberman;

public class GameValue {
    private static int BOMBER_SPEED = 2;
    private static int FLAME_LENGTH = 1;
    private static boolean CAN_DETONATE = false;
    private static boolean CAN_BY_BOMB = false;
    private static boolean CAN_BY_FLAME = false;
    private static boolean CAN_BY_WALL = false;
    private static boolean CAN_MYSTERY = false;
    private static int BOMB_CAPACITY = 1;
    private static int CURRENT_BOMB_CAPACITY = 1;

    /**
     * Increase the maximum of bomb that can put at time.
     */
    public static void updateBombCapacity() {
        final int max = 5;
        if (BOMB_CAPACITY < max) BOMB_CAPACITY++;
    }

    /**
     * Add current bomb capacity that can put at time.
     */
    public static void addCurrentCapacity() {
        if (CURRENT_BOMB_CAPACITY + 1 <= BOMB_CAPACITY) {
            CURRENT_BOMB_CAPACITY++;
        }
    }

    /**
     * Remove current bomb capacity that can put at time.
     */
    public static void removeCurrentCapacity() {
        if (CURRENT_BOMB_CAPACITY - 1 >= 0) {
            CURRENT_BOMB_CAPACITY--;
        }
    }

    /**
     * Get current bomb capacity that can put at time.
     *
     * @return bomb capacity
     */
    public static int getCurrentCapacity() {
        return CURRENT_BOMB_CAPACITY;
    }

    /**
     * Speed up bomber.
     */
    public static void updateBomberSpeed() {
        final int max = 5;
        if (BOMBER_SPEED < max) BOMBER_SPEED++;
    }

    /**
     * Get bomber's speed.
     *
     * @return bomber's speed
     */
    public static int getBomberSpeed() {
        return BOMBER_SPEED;
    }

    /**
     * Expand the destroyed area.
     */
    public static void updateFlameLength() {
        final int max = 10;
        if (FLAME_LENGTH < max) FLAME_LENGTH++;
    }

    /**
     * Get the length of flame.
     *
     * @return flame's length
     */
    public static int getFlameLength() {
        return FLAME_LENGTH;
    }

    /**
     * Active by wall ability.
     */
    public static void updateCanByWall() {
        CAN_BY_WALL = true;
    }

    /**
     * Determine whether bomber can pass by wall.
     *
     * @return result
     */
    public static boolean isCanByWall() {
        return CAN_BY_WALL;
    }

    /**
     * Active by bomb ability.
     */
    public static void updateCanByBomb() {
        CAN_BY_BOMB = true;
    }

    /**
     * Determine whether bomber can pass by bomb.
     *
     * @return result
     */
    public static boolean isCanByBomb() {
        return CAN_BY_BOMB;
    }

    /**
     * Active by flame ability.
     */
    public static void updateCanByFlame() {
        CAN_BY_FLAME = true;
    }

    /**
     * Determine whether bomber can pass by flame.
     *
     * @return result
     */
    public static boolean isCanByFlame() {
        return CAN_BY_FLAME;
    }

    // ------------- TODO LIST ------------------

    /**
     * TODO:
     */
    public static void updateMystery() {
        CAN_MYSTERY = true;
    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public static boolean isMystery() {
        return CAN_MYSTERY;
    }

    /**
     * TODO:
     */
    public static void updateCanDetonate() {
        CAN_DETONATE = true;

    }

    /**
     * TODO:
     *
     * @return TODO:
     */
    public static boolean isCanDetonate() {
        return CAN_DETONATE;
    }

}
