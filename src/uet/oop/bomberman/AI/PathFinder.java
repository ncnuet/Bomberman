package uet.oop.bomberman.AI;

import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.spriteEntity.bomber.Bomber;
import uet.oop.bomberman.entities.spriteEntity.enermy.Enemy;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.utils.Coordinate;
import uet.oop.bomberman.utils.Direction;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathFinder {
    private static final int[] DIR_X = new int[]{0, 1, 0, -1};
    private static final int[] DIR_Y = new int[]{-1, 0, 1, 0};
    private static final Direction[] DIR = new Direction[]{
            Direction.UP, Direction.RIGHT, Direction.DOWN, Direction.LEFT};
    private Context context;
    private final boolean wallPass;
    private boolean[][] visited;
    private int height, width;
    private Queue<Item> queue;

    /**
     * Constructor.
     *
     * @param context context
     * @param enemy   enemy
     */
    public PathFinder(Enemy enemy, Context context) {
        this.init(enemy, context);
        this.wallPass = false;
    }

    /**
     * Constructor.
     *
     * @param context context
     * @param enemy   enemy
     */
    public PathFinder(Enemy enemy, Context context, boolean wallPass) {
        this.init(enemy, context);
        this.wallPass = wallPass;
    }

    private void init(Enemy enemy, Context context) {
        this.context = context;
        Item source = new Item(enemy.getX(), enemy.getY(), 0, new LinkedList<>());
        int width = this.context.getWidth();
        int height = this.context.getHeight();
        this.visited = new boolean[height][width];

        // Mark all as not visited except source
        this.visited[source.crdY][source.crdX] = true;

        this.queue = new LinkedList<>();
        this.queue.add(source);
    }

    // implement bfs algorithm
    // if distance > 25 => no path
    public List<Direction> find() {
        List<Direction> path = new LinkedList<>();

        while (!queue.isEmpty()) {
            Item item = queue.remove();

            // Destination found
            if (isBomber(item)) {
                path = item.direction;
                break;
            }

            if (item.distance > 25) {
                break;
            }

            for (int index = 0; index < 4; index++) {
                int crdX = item.crdX + DIR_X[index];
                int crdY = item.crdY + DIR_Y[index];

                if (isCanPass(crdX, crdY)) {
                    List<Direction> dirList = new LinkedList<>(item.direction);
                    dirList.add(DIR[index]);
                    this.queue.add(new Item(crdX, crdY, item.distance + 1, dirList));
                    this.visited[item.crdY][item.crdX] = true;
                }
            }
        }
        return path;
    }

    public boolean isCanPass(int crdX, int crdY) {
        if (new Coordinate(crdX, crdY).isInMap(context)) {
            if (!visited[crdY][crdX]) {
                Entity entity = this.context.getEntity(
                        Coordinate.createCrdByTile(crdX, crdY), false);
                return (entity instanceof Grass || (wallPass && entity instanceof Wall));
            }
        }

        return false;
    }

    public boolean isBomber(Item item) {
        if (new Coordinate(item.crdX, item.crdY).isInMap(context)) {
            if (!visited[item.crdY][item.crdX]) {
                Entity entity = this.context.getBomber(
                        Coordinate.createCrdByTile(item.crdX, item.crdY));
                return entity instanceof Bomber;
            }
        }
        return false;
    }
}

class Item {
    public int crdX, crdY;
    public int distance;
    public List<Direction> direction;

    public Item(int crdX, int crdY, int distance, List<Direction> direction) {
        this.crdX = crdX;
        this.crdY = crdY;
        this.distance = distance;
        this.direction = direction;
    }
}