package agh.ics.oop.model;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface WorldMap extends MoveValidator {

    /**
     * Place a animal on the map.
     *
     * @param animal The animal to place on the map.
     */
     void  place(Animal animal) throws PositionAlreadyOccupiedException;

    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(Animal animal, Integer direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */

    WorldElement objectAt(Vector2d position);
    Map<Vector2d, WorldElement> getElement();

    void addObserver(MapChangeListener observer);
    void removerObserver(MapChangeListener observer);
    void mapChanged(String message);
    void notifyObservers(String message);
    Boundary getCurrentBounds();

    UUID getId();
    //List<Animal> getOrderedAnimals();
}
