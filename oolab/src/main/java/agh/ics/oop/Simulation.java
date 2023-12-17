package agh.ics.oop;
import java.util.*;

import agh.ics.oop.model.*;


public class Simulation implements Runnable{

    private final List<MoveDirection> directions;
    private  final WorldMap map;
    private final List<Vector2d> positions;
    private final List<Animal> animals;


    // W implementacji listy animals wykorzystuję ArrayList, gdyż na tej liście częśćiej będą wykonywane operaje
    // odczytu i niż dodawania badź usuwania elementów, zatem wykorzystanie ArrayList będzię bardziej optymalne
    public Simulation(List<MoveDirection> directions, List<Vector2d> positions, WorldMap map){
        this.directions = directions;
        this.positions = positions;
        List<Animal> animals = new ArrayList<>();
        for (Vector2d move : positions) {
            Animal animal = new Animal(move);
            try {
                map.place(animal);
                animals.add(animal);
            } catch (PositionAlreadyOccupiedException e) {
                System.err.println(e.getMessage());
            }

        }
        this.animals = animals;
        this.map = map;


    }
    @Override
    public void run() {
        int animalNumber = animals.size();
        for (int idx = 0; idx < directions.size();idx++){
            Animal animal = animals.get(idx % animalNumber);
            map.move(animal, directions.get(idx));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
    public List<MoveDirection> getDirections() {
        return directions;
    }
    public List<Vector2d> getPositions() {
        return positions;
    }
    public List<Animal> getAnimals(){
        return Collections.unmodifiableList(animals);
    }

    public WorldMap getMap() {
        return map;
    }
}
