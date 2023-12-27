package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

public class StandardMap implements WorldMap{
    private final int width;
    private final int height;
    private  final int grassNumber;
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final ArrayList<Grass> grasses;
    private final List<Vector2d> fertilized;
    private int grassEnergy;
    private final List<MapChangeListener> observers = new ArrayList<>();

    private final UUID id;

    private final Vector2d lowerLeftLimit;
    private final Vector2d upperRightLimit ;

    public StandardMap(int width, int height, int grassNumber, List<Vector2d> fertilized, UUID id){
        this.width = width;
        this.height = height;
        this.grassNumber = grassNumber;
        this.fertilized = fertilized;
        this.id =id;
        this.lowerLeftLimit = new Vector2d(0, 0);
        this.upperRightLimit =new Vector2d(width-1, height-1);
        this.grasses = placeGrass();


    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return lowerLeftLimit.precedes(position) && upperRightLimit.follows(position);
    }

    @Override
    public void place(Animal animal) throws PositionAlreadyOccupiedException {
        try{
        if(canMoveTo(animal.getCurrentPosition())){
            animals.add(animal);
        }else {
            throw new PositionAlreadyOccupiedException(animal.getCurrentPosition());
        }
    } catch (PositionAlreadyOccupiedException e) {
        System.err.println(e.getMessage());
    }


    }
    public ArrayList<Grass> placeGrass(){
        RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(width, height, grassNumber, fertilized);
        ArrayList<Grass> grasses1 = new ArrayList<>();
        for(Vector2d grassPosition : randomPositionGenerator) {
            grasses1.add(new Grass(grassPosition, grassEnergy));

        }
        return grasses1;

    }

    @Override
    public void move(Animal animal, Integer direction) {
        Vector2d newPosition = animal.calculateNextPosition(direction);
        MapDirection oldOrientation = animal.getCurrentOrientation();
        if (newPosition.getY() == height | newPosition.getY() < 0){
            System.out.println("YYYYYYYYYYYYYYYY");
            animals.remove(animal);
            animal.changeOrientation(oldOrientation.changeOrientation(4));
            animals.add(animal);
            mapChanged("Animal moved from %s to %s in direction %s".formatted(newPosition,animal.getCurrentPosition(), animal.orientationToString()));

        }
        if(newPosition.getX() == width | newPosition.getX() < 0){
            animals.remove(animal);
            animal.changePosition(new Vector2d(width-newPosition.getX(), newPosition.getY()));
            animals.add(animal);
            mapChanged("Animal moved from %s to %s in direction %s".formatted(newPosition,animal.getCurrentPosition(), animal.orientationToString()));
        }else {
            animals.remove(animal);
            animal.move(direction, this);
            animals.add(animal);
            mapChanged("Animal moved from %s to %s in direction %s".formatted(newPosition, animal.getCurrentPosition(), animal.orientationToString()));
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {

        for(Animal a: animals){
            if(a.getCurrentPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    public boolean isOccupiedByGrass(Vector2d position){
        for(Grass g : grasses){
            if (g.getCurrentPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        if(isOccupied(position)){
            for(Animal a : animals){
                if (a.getCurrentPosition().equals(position)){
                    return a;
                }
            }
        }else if(isOccupiedByGrass(position)){
            for(Grass g : grasses){
                if(g.getCurrentPosition().equals(position)){
                    return g;
                }
            }
        }

        return null;
    }

    @Override
    public HashMap<Vector2d, WorldElement> getElement() {
        HashMap<Vector2d, WorldElement> elements = new HashMap<>();
        for(Animal a : animals){
            elements.put(a.getCurrentPosition(), a);
        }
        for(Grass g : grasses){
            elements.put(g.getCurrentPosition(), g);
        }
        return elements ;
    }
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(getCurrentBounds().lowerLeftLimit(), getCurrentBounds().upperRightLimit());

    }

    @Override
    public void addObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    @Override
    public void removerObserver(MapChangeListener observer) {
        observers.remove(observer);

    }

    @Override
    public void mapChanged(String message) {
        notifyObservers(message);

    }

    @Override
    public void notifyObservers(String message) {
        for(MapChangeListener o : observers){
            o.mapChanged(this, message);
        }

    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(lowerLeftLimit, upperRightLimit);
    }

    @Override
    public UUID getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Vector2d getLowerLeftLimit() {
        return lowerLeftLimit;
    }

    public Vector2d getUpperRightLimit() {
        return upperRightLimit;
    }

    public int getGrassNumber() {
        return grassNumber;
    }
}
