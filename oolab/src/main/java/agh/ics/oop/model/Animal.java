package agh.ics.oop.model;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.max;


public class Animal implements WorldElement {
    private MapDirection currentOrientation;
    private Vector2d currentPosition;
    public final double energyToCopulation = 0.3;
    private int animalEnergy;
    private  int numberOfGenes;
    private final Genotype genotype;
    private int age;
    public static final Vector2d UPPER_RIGHT_LIMIT = new Vector2d(4, 4);
    public static final Vector2d LOWER_LEFT_LIMIT = new Vector2d(0, 0);
    public Animal(){
        this.currentOrientation = MapDirection.NORTH;
        this.currentPosition = new Vector2d(2, 2);
        this.animalEnergy = 100;
        this.genotype = new Genotype(7,numberOfGenes=10);
        this.age = 0;

    }

    public Animal(Vector2d newPosition,MapDirection orientation ,int energy,int numberOfGenes){
        this.numberOfGenes = numberOfGenes;
        this.currentPosition = newPosition;
        this.currentOrientation = orientation;
        this.animalEnergy = energy;
        this.genotype = new Genotype(8, numberOfGenes);
        this.age = 0;
    }

    // Using during copulation
    public Animal(Vector2d newPosition ,MapDirection currentOrientation,int energy, Genotype genotype){
        this.currentPosition = newPosition;
        this.currentOrientation = currentOrientation;
        this.animalEnergy = energy;
        this.genotype = genotype;
        this.age = 0;
    }

    public String orientationToString(){
        return switch (this.currentOrientation){
            case NORTH -> "↑"; // Unicode for '↑'
            case SOUTH -> "\u2193"; // Unicode for '↓'
            case WEST -> "\u2190"; // Unicode for '←'
            case EAST -> "\u2192"; // Unicode for '→'
            case NORTHWEST -> "\u2196"; // Unicode for '↖'
            case SOUTHWEST -> "\u2199"; // Unicode for '↙'
            case NORTHEAST -> "\u2197"; // Unicode for '↗'
            case SOUTHEAST -> "\u2198"; // Unicode for '↘'

        };

    }
    // The method will be used in class GlobalMap to add energy to animalEnergy when it ate the grass
    public void changeEnergy(int value){
        this.animalEnergy = max(animalEnergy + value, 0);
    }

    public boolean isDead(){
        return this.animalEnergy == 0;
    }

    public Animal copulate(Animal mother){
        System.out.println(numberOfGenes);
        int childEnergy = (int)((energyToCopulation)*(mother.animalEnergy + this.animalEnergy));
        ArrayList<Integer> childGenes;
        if(this.animalEnergy > mother.animalEnergy){
             childGenes = divideGenotype(this, mother);

        }else {
            childGenes =  divideGenotype(mother, this);
        }
        this.changeEnergy(-(int)((energyToCopulation)*this.animalEnergy));
        mother.changeEnergy(-(int)((energyToCopulation)*mother.animalEnergy));

        Genotype childGenotype = new Genotype(8, numberOfGenes, childGenes);
        childGenotype.randomMutations();
        return new Animal(mother.currentPosition, mother.currentOrientation, childEnergy, childGenotype);

    }

    private ArrayList<Integer> divideGenotype(Animal animal1, Animal animal2) {
        int total_energy = animal1.animalEnergy + animal2.animalEnergy;
        Random random = new Random();
        boolean isRight = 1 == random.nextInt(2);
        System.out.println(isRight);
        ArrayList<Integer> genes = new ArrayList<>();
        int divideIdx = (animal1.animalEnergy*numberOfGenes / total_energy);
        System.out.println(divideIdx);
        if (isRight){
            for(int i =0; i < numberOfGenes;i++){
                if(i <= divideIdx) {
                    genes.add(animal1.genotype.getGenes().get(i));
                }else genes.add(animal2.genotype.getGenes().get(i));
            }
        }else{
            for(int i =0; i < numberOfGenes;i++){
                if(i >= numberOfGenes-divideIdx) {
                    genes.add(animal1.genotype.getGenes().get(i));
                }else genes.add(animal2.genotype.getGenes().get(i));
            }
        }
        return genes;
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

    public void  incrementAge() {
        this.age ++;
    }
    public int getAnimalEnergy() {
        return animalEnergy;
    }

    public Genotype getGenotype() {
        return genotype;
    }

    public int getAge() {
        return age;
    }
}
