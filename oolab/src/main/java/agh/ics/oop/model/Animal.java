package agh.ics.oop.model;

public class Animal implements WorldElement {
    private MapDirection currentOrientation;
    private Vector2d currentPosition;
    public static final Vector2d UPPER_RIGHT_LIMIT = new Vector2d(4, 4);
    public static final Vector2d LOWER_LEFT_LIMIT = new Vector2d(0, 0);
    public Animal(){
        this.currentOrientation = MapDirection.NORTH;
        this.currentPosition = new Vector2d(2, 2);


    }
    public Animal(Vector2d newPosition){
        this.currentOrientation = MapDirection.NORTH;
        this.currentPosition = newPosition;
    }

    public String orientationToString(){
        return switch (this.currentOrientation){
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };

    }
    @Override
    public String toString(){
        return orientationToString();
    }

    @Override
    public boolean isAt(Vector2d position){
        return currentPosition.equals(position);
    }

    public boolean canMoveTo(Vector2d position){
        return LOWER_LEFT_LIMIT.precedes(position) && UPPER_RIGHT_LIMIT.follows(position);
    }

    public Vector2d calculateNextPosition(MoveDirection direction){
        return switch (direction){
            case FORWARD-> currentPosition.add(currentOrientation.toUnitVector());
            case BACKWARD -> currentPosition.subtract(currentOrientation.toUnitVector());
            default -> currentPosition;

        };
    }
    public void move(MoveDirection direction, MoveValidator validator){
        Vector2d newPosition = calculateNextPosition(direction);
        if(validator.canMoveTo(newPosition)){
            switch (direction){
                case RIGHT -> currentOrientation = currentOrientation.next();
                case LEFT -> currentOrientation = currentOrientation.previous();
                case FORWARD, BACKWARD -> currentPosition = newPosition;
            }
        }


    }
    @Override
    public MapDirection getCurrentOrientation() {
        return currentOrientation;
    }
    @Override
    public Vector2d getCurrentPosition() {
        return currentPosition;
    }


}
