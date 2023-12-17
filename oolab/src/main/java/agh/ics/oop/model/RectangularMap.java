package agh.ics.oop.model;

import java.util.Map;

public class RectangularMap extends AbstractWorldMap implements WorldMap<Animal, Vector2d> {


    public RectangularMap(int width, int height) {
        super.height = height;
        super.width = width;
    }


    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map.
     * @return True if the animal wa+s placed. The animal cannot be placed if the move is not valid.O*/
    @Override
    public boolean place(Animal animal, Vector2d position, boolean inform) throws PositionAlreadyOccupiedException {
        if (canMoveTo(position)) {
            this.animals.put(position,animal);
            animal.setPosition(position);
            animalCount++;
            return true;
        }
        throw new PositionAlreadyOccupiedException(position);
    }


    /**
     * Moves an animal (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    public void move(Animal animal, MoveDirection direction) {
        Vector2d startPosition = animal.getPosition();
        if (!objectAt(startPosition).equals(animal)) return;
        animals.remove(startPosition);
        animal.move(this,direction);
        animals.put(animal.getPosition(),animal);
        mapChanged("Animal " + animal + " moved from " + startPosition + " to " + animal.getPosition());
    }


    /**
     * Return an animal at a given position.
     *
     * @param position The position of the animal.
     * @return animal or null if the position is not occupied.
     */
    @Override
    public Animal objectAt(Vector2d position) {
        if (inMap(position) && isOccupied(position)) {
            return (Animal) animals.get(position);
        }
        return null;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {return inMap(position) && !isOccupied(position);}

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0,0),new Vector2d(this.getWidth() - 1,this.getHeight() - 1));
    }

}
