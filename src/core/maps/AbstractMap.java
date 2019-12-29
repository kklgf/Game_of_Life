package core.maps;

import core.elements.ILife;
import core.elements.Life;
import core.elements.LifeKnowingNeighbours;
import core.utils.Vector2d;

import java.util.*;

public abstract class AbstractMap implements IMap, IWorldMap{

    public Vector2d lowerLeft = new Vector2d(0,0);
    public Vector2d upperRight = new Vector2d(0,0);
    protected HashMap<Vector2d, ILife> alive;
    protected HashMap<Vector2d, ILife> nextTurnAlive;
    protected MapVisualizer mapVision;
    boolean lifeKnowingNeighbourhood;
    protected ArrayList<Integer> toStayAlive;
    protected ArrayList<Integer> toBecomeAlive;

    public AbstractMap(ArrayList<Integer> toStayAlive, ArrayList<Integer> toBecomeAlive,
                       boolean lifeKnowingNeighbourhood) {
        this.mapVision = new MapVisualizer(this);
        this.alive = new HashMap<Vector2d, ILife>();
        this.lifeKnowingNeighbourhood = lifeKnowingNeighbourhood;
        this.toStayAlive = toStayAlive;
        this.toBecomeAlive = toBecomeAlive;
        this.nextTurnAlive = new HashMap<Vector2d, ILife>();
    }

    @Override
    public void destroyAndCreateLife() {
        this.alive = this.nextTurnAlive;
        this.nextTurnAlive = new HashMap<Vector2d, ILife>();
        for (ILife life : this.alive.values()){
            for (Vector2d cell : this.getSurrounding(life.getPosition())){
                Integer numberOfNeighbours = this.countNeighbours(cell);
                if (shouldBeAlive(cell, numberOfNeighbours)){
                    this.makeAlive(cell, numberOfNeighbours);
                }
            }
        }
    }

    protected boolean shouldBeAlive(Vector2d cell, Integer numberOfNeighbours){
        if (this.isAlive(cell)){
            return this.toStayAlive.contains(numberOfNeighbours);
        }
        else {
            return this.toBecomeAlive.contains(numberOfNeighbours);
        }
    }

    public void makeAlive(Vector2d cell){
        if (this.lifeKnowingNeighbourhood){
            nextTurnAlive.put(cell, new Life(cell));
        }
        else {
            nextTurnAlive.put(cell, new LifeKnowingNeighbours(cell, this.countNeighbours(cell)));
        }
    }

    protected void makeAlive(Vector2d cell, Integer numberOfNeighbours){
        if (this.lifeKnowingNeighbourhood){
            nextTurnAlive.put(cell, new Life(cell));
        }
        else {
            nextTurnAlive.put(cell, new LifeKnowingNeighbours(cell, numberOfNeighbours));
        }
    }

    protected Integer countNeighbours(Vector2d position){
        Integer numberOfNeighbours = 0;
        for (Vector2d cell : this.getSurrounding(position)){
            if (cell.equals(position))
                continue;
            if (this.isAlive(cell))
                numberOfNeighbours++;
        }
        return numberOfNeighbours;
    }

    protected boolean isAlive(Vector2d position){
        return this.isOccupied(position);
    }

    protected Vector2d[] getSurrounding(Vector2d position){
        return new Vector2d[]{position.add(new Vector2d(1, -1)),
                position.add(new Vector2d(1, 0)),
                position.add(new Vector2d(1, 1)),
                position.add(new Vector2d(0, -1)),
                position.add(new Vector2d(0, 0)),
                position.add(new Vector2d(0, 1)),
                position.add(new Vector2d(-1, -1)),
                position.add(new Vector2d(-1, 0)),
                position.add(new Vector2d(-1, 1))};
    }

    @Override
    public String toString(){
        return mapVision.draw(lowerLeft, upperRight);
    }

    @Override
    public Object objectAt(Vector2d position){
        return this.alive.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position){
        return this.objectAt(position) != null;
    }
}
