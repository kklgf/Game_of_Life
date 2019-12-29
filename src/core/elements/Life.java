package core.elements;

import core.utils.Vector2d;

public class Life implements ILife{
    private Vector2d position;

    public Life(Vector2d position){
        this.position = position;
    }

    @Override
    public String toString(){
        return "*";
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }
}
