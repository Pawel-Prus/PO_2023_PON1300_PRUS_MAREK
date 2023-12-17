package agh.ics.oop.model;

public class Grass implements WorldElement {
    private final Vector2d position;
    private final int grassEnergy;
    public Grass(Vector2d position, int energy){
        this.position = position;

        this.grassEnergy = energy;
    }
    @Override
    public String toString(){
        return "*";
    }
    @Override
    public Vector2d getCurrentPosition() {
        return position;
    }

    @Override
    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    @Override
    public MapDirection getCurrentOrientation(){
        return null;
    }


    public int getGrassEnergy() {
        return grassEnergy;
    }
}
