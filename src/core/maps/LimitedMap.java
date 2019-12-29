package core.maps;

import core.elements.ILife;
import core.utils.Vector2d;

import java.util.ArrayList;

public class LimitedMap extends AbstractMap{

    public LimitedMap(ArrayList<Integer> toStayAlive, ArrayList<Integer> toBecomeAlive,
                      boolean lifeKnowingNeighbourhood,
                      Integer width, Integer height){
        super(toStayAlive, toBecomeAlive, lifeKnowingNeighbourhood);
        this.upperRight = new Vector2d(width, height);
    }

    @Override
    protected boolean shouldBeAlive(Vector2d cell, Integer numberOfNeighbours){
        if (cell.precedes(upperRight) && cell.follows(lowerLeft)){
            if (this.isAlive(cell)){
                return this.toStayAlive.contains(numberOfNeighbours);
            }
            else {
                return this.toBecomeAlive.contains(numberOfNeighbours);
            }
        }
        return false;
    }
}
