package agh.ics.oop;

import agh.ics.oop.model.*;


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
    }

}