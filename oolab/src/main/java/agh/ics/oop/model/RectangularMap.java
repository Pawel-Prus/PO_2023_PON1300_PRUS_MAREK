package agh.ics.oop.model;

import java.util.UUID;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int width, int height, UUID id) {
        super(width, height, id);
   }

    @Override
    public boolean canMoveTo(Vector2d position){
        return super.canMoveTo(position) && (lowerLeftLimit.precedes(position) && upperRightLimit.follows(position));
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(getLowerLeftLimit(), getUpperRightLimit());
    }
}
