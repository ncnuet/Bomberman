package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Playground;
import uet.oop.bomberman.entities.spriteEntity.obstacle.brick.Brick;
import uet.oop.bomberman.entities.tile.Tile;

import java.util.Arrays;
import java.util.Stack;

public final class StackEntity extends Entity {
    private final Stack<Entity> entities = new Stack<>();

    public Entity getTopEntity() {
        return this.entities.peek();
    }

    public StackEntity(int x, int y, Entity... entities) {
        super(x, y, entities[entities.length - 1].getSpriteImg());
        this.entities.addAll(Arrays.stream(entities).toList());
    }

    @Override
    public void update() {
        this.entities.forEach(Entity::update);

        // Remove
        this.entities.removeIf((Entity entity) -> {
            if (entity instanceof Brick) {
                return (((Brick) entity).isInvisible());
            }
            if (entity instanceof Tile) {
                return ((Tile) entity).isInvisible();
            }
            return false;
        });
    }

    @Override
    public void render(GraphicsContext graphicsContext, Playground playground) {
        this.entities.forEach(entity -> entity.render(graphicsContext, playground));
    }

    @Override
    public void kill() {

    }
}
