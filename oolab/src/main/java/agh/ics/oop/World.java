package agh.ics.oop;

import agh.ics.oop.model.*;
import agh.ics.oop.model.extra.RandomPositionGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class World {

    private static int randint(int start, int end) {return (int)((Math.random()*(end + 1 - start)) + start);}
    public static void main(String[] args) {
        System.out.println("System wystartował\n");
        //lab7
        System.out.println("======>lab7");

        List<MoveDirection> directions = OptionsParser.parse(args);
//        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));



        ConsoleMapDisplay observer = new ConsoleMapDisplay();

        List<Simulation> simulations = new ArrayList<>();
        AbstractWorldMap currentMap;
        for (int i = 0; i < 999999; i++) {

            int width = randint(1,5);
            int height = randint(1,5);
            int number = randint(1,Math.min(width*height,5));
            List<Vector2d> positions = (new RandomPositionGenerator(width,height,number).positions);

            int index = randint(0,1);
            if ((index % 2) == 0) {currentMap = new RectangularMap(randint(width,15),randint(height,15));}
            else {currentMap = new GrassField(randint(1,20));}
            currentMap.registerObserver(observer);
            simulations.add(new Simulation(directions, positions, (WorldMap<Animal, Vector2d>) currentMap,false));
        }
        SimulationEngine SE = new SimulationEngine(simulations);
        SE.runAsyncInThreadPool();


        System.out.println("\nSystem zakończył działanie");

    }


}
