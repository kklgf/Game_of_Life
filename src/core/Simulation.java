package core;

import core.maps.AbstractMap;
import core.maps.LimitedMap;
import core.maps.LimitlessMap;
import core.utils.MyRandom;
import core.utils.SimulationParameters;
import core.utils.Vector2d;

import java.util.ArrayList;

import static java.lang.Math.*;

public class Simulation extends AbstractSimulation {
    protected boolean limitedMap;

    public Simulation(SimulationParameters params) throws Exception {
        this.numberOfTurns = params.numberOfTurns;
        this.actualTurn = 0;
        if (params.limitedMap){
            this.map = new LimitedMap(params.toStayAlive, params.toBecomeAlive, params.lifeKnowingNeighbourhood, params.width, params.height);
        }
        else {
            this.map = new LimitlessMap(params.toStayAlive, params.toBecomeAlive, params.lifeKnowingNeighbourhood);
        }
    }

    public Simulation(boolean limitedMap, Integer width, Integer height, boolean lifeKnowingNeighbourhood,
                      ArrayList<Integer> toStayAlive, ArrayList<Integer> toBecomeAlive,
                      Integer numberOfTurns){
        this.numberOfTurns = numberOfTurns;
        this.actualTurn = 0;
        if (limitedMap){
            this.map = new LimitedMap(toStayAlive, toBecomeAlive, lifeKnowingNeighbourhood, width, height);
        }
        else {
            this.map = new LimitlessMap(toStayAlive, toBecomeAlive, lifeKnowingNeighbourhood);
        }
    }

    @Override
    public String toString(){
        return map.toString();
    }

    public void initializeLifeRandomly(Integer howMany) throws IllegalArgumentException {
        if (limitedMap){
            for(Integer i = 0; i < howMany; i++){
                Vector2d position = MyRandom.getRandomVectorInArea(map.lowerLeft, map.upperRight);
                this.map.makeAlive(position);
            }
        }
        else {
            for(Integer i = 0; i < howMany; i++){
                Vector2d position = MyRandom.getRandomVectorInArea(new Vector2d(0,0), new Vector2d((int) sqrt(howMany), (int) sqrt(howMany)));
                this.map.makeAlive(position);
            }
        }
    }
}
