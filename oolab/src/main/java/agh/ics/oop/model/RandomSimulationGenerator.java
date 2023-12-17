package agh.ics.oop.model;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomSimulationGenerator {
    private final int n;
    private final int positionsNumber;
    private final int movesNumber;
    private final int width;
    private final int height;
    private final int grassNumber;
    private final MapChangeListener observer = new ConsoleMapDisplay();
    public RandomSimulationGenerator(int n, int positionsNumber, int movesNumber, int width, int height, int grassNumber){
        this.n = n;
        this.positionsNumber = positionsNumber;
        this.movesNumber = movesNumber;
        this.width = width;
        this.height = height;
        this.grassNumber = grassNumber;

    }

    public List<MoveDirection> getRandomDirections(){
        Random random = new Random();
        List<MoveDirection> directions =  new ArrayList<>();
        for(int i = 0; i < movesNumber ;i ++){
            MoveDirection direction = MoveDirection.values()[random.nextInt(4)];
            directions.add(direction);
        }

     return directions;
    }

    public List<Simulation> getSimulations(){
        List<Simulation> simulations = new ArrayList<>();
        for(int i =0 ;i< n; i++){
            if(i % 2 == 0){ // 0 - RectangularMap , 1 - GrassField
                RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(width, height, positionsNumber);
                List<Vector2d> positions = randomPositionGenerator.getPositions();
                List<MoveDirection> directions = getRandomDirections();
                RectangularMap map = new RectangularMap(width, height, UUID.randomUUID());
                map.addObserver(observer);
                simulations.add(new Simulation(directions, positions, map));

            }else{
                RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(width, height, grassNumber);
                List<Vector2d> positions = randomPositionGenerator.getPositions();
                List<MoveDirection> directions = getRandomDirections();
                GrassField grassMap = new GrassField(grassNumber, UUID.randomUUID());
                grassMap.addObserver(observer);
                simulations.add(new Simulation(directions, positions, grassMap));

            }

        }

        return simulations;


    }


}
