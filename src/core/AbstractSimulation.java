package core;

import core.maps.AbstractMap;

public abstract class AbstractSimulation implements ISimulation{

    protected AbstractMap map;
    protected Integer actualTurn;
    protected Integer numberOfTurns;

    @Override
    public void start(boolean show) throws Exception {
        for(int i = 0; i < numberOfTurns; i++){
            this.nextTurn(show);
            if (i % 100 == 0){
                System.out.print(i);
            }
        }
    }

    @Override
    public void nextTurn(boolean show) {
        map.destroyAndCreateLife();
        this.numberOfTurns ++;
        if (show){
            System.out.println(this);
        }
    }
}
