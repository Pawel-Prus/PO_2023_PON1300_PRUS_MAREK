package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.UUID;


public class World {

    public static void main(String[] args) {
        Animal animal1 = new Animal();
        System.out.println(animal1);
        Animal animal2 = new Animal(new Vector2d(3, 4), MapDirection.SOUTHWEST, 200, 10);
        Animal child  = animal1.copulate(animal2);
        System.out.println(animal1.getGenotype());
        System.out.println(animal2.getGenotype());
        System.out.println(child.getGenotype());
        System.out.println(animal2);
        RectangularMap map = new RectangularMap(10, 10, UUID.randomUUID());
        try {
            map.place(animal1);
        } catch (PositionAlreadyOccupiedException e) {
            throw new RuntimeException(e);
        }

        map.move(animal1, 0);
        System.out.println(map);
        map.move(animal1, 1);
        System.out.println(map);
        map.move(animal1, 1);
        System.out.println(map);
        map.move(animal1, 1);
        System.out.println(map);
        map.move(animal1, 1);
        System.out.println(map);
        map.move(animal1, 1);
        System.out.println(map);
        map.move(animal1, 1);
        System.out.println(map);
        System.out.println("---------------------------");

        Animal animal3 =  new Animal();
        animal3.changePosition(new Vector2d(3, 3));
        animal3.incrementNumberOfChildren();
        animal3.incrementAge();
        animal3.incrementAge();
        Animal animal4 =  new Animal();
        animal4.incrementNumberOfChildren();
        animal4.incrementNumberOfChildren();
        animal4.incrementAge();
        Animal animal5 =  new Animal();
        animal5.changePosition(new Vector2d(0,2));
        animal5.incrementNumberOfChildren();
        animal5.changeEnergy(30);
        ArrayList<Animal> animals = new ArrayList<>();

        animals.add(animal3);
        animals.add(animal4);
        animals.add(animal5);
        Competition competition = new Competition(animals);
        System.out.println( competition.getTheStrongestCouple());
        System.out.println("---------------------------");
        ArrayList<Vector2d> pos  = new ArrayList<>();
        StandardMap map1 = new StandardMap(5, 5, 10, pos, UUID.randomUUID());
        for(Animal a: animals){

            try {
                map1.place(a);
            } catch (PositionAlreadyOccupiedException e) {
                throw new RuntimeException(e);
            }

        }
        System.out.println(map1);
        map1.move(animal3, 0);
        System.out.println(map1);
        System.out.println(animal3.getCurrentPosition());
        map1.move(animal3, 0);
        System.out.println(map1);
        System.out.println(animal3.getCurrentPosition());
        map1.move(animal3, 0);
        System.out.println(map1);
        System.out.println(animal3.getCurrentPosition());
        map1.move(animal3, 0);
        System.out.println(map1);
        System.out.println(animal3.getCurrentPosition());
        map1.move(animal3, 0);
        System.out.println(map1);
        System.out.println(animal3.getCurrentPosition());
        map1.move(animal3, 0);
        System.out.println(map1);
        System.out.println(animal3.getCurrentPosition());


    }

}