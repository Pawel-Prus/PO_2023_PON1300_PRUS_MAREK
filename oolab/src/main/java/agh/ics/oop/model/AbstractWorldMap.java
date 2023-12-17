package agh.ics.oop.model;

import agh.ics.oop.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap {
    public int height;
    public int width;

    public int animalCount = 0;
    public Map<Vector2d, WorldElement> animals = new HashMap<>();

    Map<Vector2d,WorldElement> grasses = new HashMap<>();

    public void setAnimals(Map<Vector2d, WorldElement> animals) {this.animals = animals;}
    public void setGrasses(Map<Vector2d, WorldElement> grasses) {this.grasses = grasses;}

    public Map<Vector2d,WorldElement> getGrasses() {return this.grasses;}


    public Map<Vector2d,WorldElement> getAnimals() {return this.animals;}

    List<MapChangeListener> observers = new ArrayList<>();

    private final UUID id = UUID.randomUUID();

    public UUID getId() {
        return this.id;
    }

    public void registerObserver(MapChangeListener observer) {
        observers.add(observer);
    }

    public void unregisterObserver(MapChangeListener observer) {
        observers.remove(observer);
    }

    public int getWidth() {return this.width;}
    public int getHeight() {return this.height;}

    public boolean isGrass(WorldElement element) {
        return (Objects.equals(element.toString(), "*"));
    }
    public boolean isAnimal(WorldElement element) {
        return ((!isGrass(element)) && (!element.equals(null)));
    }

    public void mapChanged(String event) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged((WorldMap) this,event);
        }
    }

    public boolean place(WorldElement object, Vector2d position, boolean inform) throws PositionAlreadyOccupiedException {
        if ((isAnimal(object)) && (canMoveTo(position))) {
            animals.put(position, object);
            if (inform) {mapChanged("Animal " + object + " placed at " + position);}
            return true;
        } else if ((isGrass(object)) && (grasses.get(position) == null) && (animals.get(position) == null)) {
            grasses.put(position, object);
            if (inform) {mapChanged("Grass " + object + " placed at " + position);}
            return true;
        } else {throw new PositionAlreadyOccupiedException(position);}
    }


    public void move(WorldElement object, MoveDirection direction) {
        if (!isAnimal(object)) {return;}
        Vector2d startPosition = (Vector2d) object.getPosition();
        if (!objectAt(startPosition).equals(object)) {return;}
        animals.remove(startPosition);
        object.move((MoveValidator) this,direction);
        animals.put((Vector2d) object.getPosition(),object);
        mapChanged("Animal " + object + " moved from " + startPosition + " to " + object.getPosition());
    }


    boolean inMap(Vector2d position) {
        int x = position.getX();
        int y = position.getY();
        return (x < this.getWidth()) && (0 <= x) && (y < this.getHeight()) && (0 <= y);
    }

    public boolean isOccupied(Vector2d position) {
        return ((animals.get(position) != null) || (grasses.get(position) != null));
    }

    public WorldElement objectAt(Vector2d position) {
        if (animals.get(position) != null) {return animals.get(position);}
        else if (grasses.get(position) != null) {return grasses.get(position);}
        else {return null;}

    }

    public boolean canMoveTo(Vector2d position) {
        return (!isOccupied(position) || isGrass(objectAt(position)));
    }


    public Map<Vector2d, WorldElement> getElement() {
        Map<Vector2d, WorldElement> output = new HashMap<>();
        for (Map.Entry<Vector2d, WorldElement> element : grasses.entrySet()) {output.put(element.getKey(), element.getValue());}
        for (Map.Entry<Vector2d, WorldElement> element : animals.entrySet()) {output.put(element.getKey(), element.getValue());}
        return output;
    }

    public Vector2d[] findMax(Map<Vector2d, WorldElement> map,Vector2d lL, Vector2d uR) {
        Vector2d lowerLeft = lL;
        Vector2d upperRight = uR;
        for (Map.Entry<Vector2d, WorldElement> entry : map.entrySet()) {

            Vector2d vector = entry.getKey();
            if (lowerLeft == null) {lowerLeft = vector;}
            if (upperRight == null) {upperRight = vector;}
            upperRight = upperRight.upperRight(vector);
            lowerLeft = lowerLeft.lowerLeft(vector);
        }

        return new Vector2d[]{lowerLeft, upperRight};

    }

    public abstract Boundary getCurrentBounds();

    public String toString() {
        Boundary bounds = getCurrentBounds();
        Vector2d lowerleft = bounds.lowerleft();
        Vector2d upperright = bounds.upperright();
        return (new MapVisualizer((WorldMap) this)).draw(lowerleft, upperright);
    }


}
