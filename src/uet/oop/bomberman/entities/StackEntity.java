package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.Context;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.utils.Coordinate;

import java.util.Arrays;
import java.util.Stack;

public final class StackEntity extends Entity {
    private final Stack<Entity> entities = new Stack<>();

    public Entity peek() {
        return this.entities.peek();
    }

    /**
     * Constructor.
     *
     * @param crd      coordinate
     * @param entities list of entities
     */
    public StackEntity(Coordinate crd, Entity... entities) {
        super(crd, entities[entities.length - 1].getSprite());
        this.entities.addAll(Arrays.stream(entities).toList());
    }

    @Override
    public void render(GraphicsContext graphicsContext, Context context) {
        this.entities.forEach(entity -> entity.render(graphicsContext, context));
    }

    @Override
    public void update() {
        this.entities.forEach(Entity::update);

        // Remove if entity marked as removed
        this.entities.removeIf((Entity entity) -> {
            // Never remove Grass layer
            if (!(entity instanceof Grass)) {
                return entity.isRemoved();
            }
            return false;
        });
    }

    // Stack never be killed because of having Grass layer at bottom stack
    @Override
    public void kill() {
    }
}
