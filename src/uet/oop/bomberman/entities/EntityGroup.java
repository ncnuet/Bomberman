package uet.oop.bomberman.entities;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class EntityGroup extends Entity{
    private Stack<Entity> entities = new Stack<>();

    public EntityGroup(int x, int y, Entity ... entities){
        super(x,y,entities[entities.length-1].getSpriteImg());
        this.entities.addAll(Arrays.stream(entities).toList());
    }

    @Override
    public void update() {

    }

    @Override
    protected boolean collide(Entity entity) {
        return false;
    }
}
