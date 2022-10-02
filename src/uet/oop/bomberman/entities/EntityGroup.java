package uet.oop.bomberman.entities;

import java.util.Arrays;
import java.util.Stack;

public final class EntityGroup extends Entity {
    private final Stack<Entity> entities = new Stack<>();

    public Entity getTopEntity() {
        return this.entities.peek();
    }

    public EntityGroup(int x, int y, Entity... entities) {
        super(x, y, entities[entities.length - 1].getSpriteImg());
        this.entities.addAll(Arrays.stream(entities).toList());
    }

    @Override
    public void update() {

    }
}
