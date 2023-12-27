package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, WorldElement> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>();

    protected final int width;
    protected final int height;
    protected final Vector2d lowerLeftLimit;
    protected final Vector2d upperRightLimit ;
    protected final UUID id;



    protected AbstractWorldMap(int width, int height, UUID id) {
        this.width = width;
        this.height = height;
        this.id = id;
        lowerLeftLimit = new Vector2d(0,0);
        upperRightLimit = new Vector2d(width-1, height-1);
    }

    public void addObserver(MapChangeListener observer){
        observers.add(observer);
    }

    public void removerObserver(MapChangeListener observer){
        observers.remove(observer);
    }

    public synchronized void notifyObservers(String message){
        for(MapChangeListener o : observers){
            o.mapChanged(this, message);
        }
    }

    public synchronized void mapChanged(String message) {
        notifyObservers(message);
    }

    public boolean isOccupied(Vector2d position){
        return animals.containsKey(position);
    }
    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position);
    }
    public void place (Animal animal) throws PositionAlreadyOccupiedException{
        try {
            if (canMoveTo(animal.getCurrentPosition())) {
                animals.put(animal.getCurrentPosition(), animal);
                mapChanged("Animal placed at %s".formatted(animal.getCurrentPosition()));
            } else {
                throw new PositionAlreadyOccupiedException(animal.getCurrentPosition());
            }
        } catch (PositionAlreadyOccupiedException e) {
            System.err.println(e.getMessage());
        }

    }

    public void move(Animal animal, Integer direction){
        Vector2d oldPosition = animal.getCurrentPosition();
        animals.remove(oldPosition);
        animal.move(direction,this);
        animals.put(animal.getCurrentPosition(), animal);
        mapChanged("Animal moved from %s to %s in direction %s".formatted(oldPosition,animal.getCurrentPosition(), animal.orientationToString()));
    }


    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getCurrentBounds().lowerLeftLimit(), getCurrentBounds().upperRightLimit());

    }


    public WorldElement objectAt(Vector2d position){

            return animals.get(position);
    }

    public Map<Vector2d, WorldElement> getElement() {
        return new HashMap<>(animals);
    }

    public abstract Boundary getCurrentBounds();

    @Override
    public UUID getId() {
        return id;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Vector2d getLowerLeftLimit() {
        return lowerLeftLimit;
    }

    public Vector2d getUpperRightLimit() {
        return upperRightLimit;
    }

    public Map<Vector2d, WorldElement> getAnimals(){
        return animals;
    }



}
