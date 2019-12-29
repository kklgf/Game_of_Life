package core.maps;

import core.utils.Vector2d;

import java.util.ArrayList;
import java.util.Set;

public class LimitlessMap extends AbstractMap{

    public LimitlessMap(ArrayList<Integer> toStayAlive, ArrayList<Integer> toBecomeAlive,
                        boolean lifeKnowingNeighbourhood) {
        super(toStayAlive, toBecomeAlive, lifeKnowingNeighbourhood);
    }

    @Override
    public String toString(){
        this.actualizeView();
        return super.toString();
    }

    protected void actualizeView () {
        ArrayList<Vector2d> aliveCells = new ArrayList<Vector2d>(alive.keySet());
        Integer minX = aliveCells.get(0).x;
        Integer maxX = aliveCells.get(0).x;
        Integer minY = aliveCells.get(0).y;
        Integer maxY = aliveCells.get(0).y;
        for (Vector2d cell : aliveCells){
            if (minX > cell.x)
                minX = cell.x;
            else if (maxX < cell.x)
                maxX = cell.x;
            if (minY > cell.y)
                minY = cell.y;
            else if (maxY < cell.y)
                maxY = cell.y;
        }
        this.lowerLeft = new Vector2d(minX, minY);
        this.upperRight = new Vector2d(maxX, maxY);
    }

}
