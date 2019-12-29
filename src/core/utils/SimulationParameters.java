package core.utils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimulationParameters {
    public final boolean limitedMap;
    public final boolean lifeKnowingNeighbourhood;
    public final int width;
    public final int height;
    public final ArrayList<Integer> toStayAlive;
    public final ArrayList<Integer> toBecomeAlive;
    public final int numberOfTurns;


    private static Gson gson = new Gson();

    public SimulationParameters(boolean limitedMap, Integer width, Integer height, boolean lifeKnowingNeighbourhood,
                                ArrayList<Integer> toStayAlive, ArrayList<Integer> toBecomeAlive, int numberOfTurns) {
        this.limitedMap = limitedMap;
        this.width = width;
        this.height = height;
        this.lifeKnowingNeighbourhood = lifeKnowingNeighbourhood;
        this.toStayAlive = toStayAlive;
        this.toBecomeAlive = toBecomeAlive;
        this.numberOfTurns = numberOfTurns;
    }

    public static SimulationParameters fromFile(File file) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return SimulationParameters.gson.fromJson(bufferedReader, SimulationParameters.class);
        } catch (IOException error) {
            throw error;
        }
    }
}
