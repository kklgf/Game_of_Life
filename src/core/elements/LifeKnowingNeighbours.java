package core.elements;

import core.utils.Vector2d;

public class LifeKnowingNeighbours extends Life {
    public Integer numberOfNeighbours;

    public LifeKnowingNeighbours(Vector2d position, Integer numberOfNeighbours) {
        super(position);
        this.numberOfNeighbours = numberOfNeighbours;
    }

    @Override
    public String toString(){
        return this.numberOfNeighbours.toString();
    }
}
