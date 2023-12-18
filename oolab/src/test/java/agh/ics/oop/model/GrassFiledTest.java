package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GrassFiledTest {

    @Test
    public void isPositionOccupiedByAnimal() throws PositionAlreadyOccupiedException {
        GrassField grassMap = new GrassField(10, UUID.fromString("1"));
        List<Animal> animals = List.of(
                new Animal(),
                new Animal(new Vector2d(9, 4),MapDirection.NORTH, 0,0),
                new Animal(new Vector2d(7, 4),MapDirection.NORTH,0,0),
                new Animal(new Vector2d(70, -4),MapDirection.NORTH, 0,0),
                new Animal(new Vector2d(-7, 4),MapDirection.NORTH, 0,0),
                new Animal(new Vector2d(-7, -4),MapDirection.NORTH, 0,0)

        );
        List<Vector2d> freePositions = List.of(new Vector2d(0,0), new Vector2d(1,1), new Vector2d(-8,4));
        for (Animal a : animals) {
            grassMap.place(a);

        }
        for(Animal a: animals){
        assertTrue(grassMap.isOccupied(a.getCurrentPosition()));
        }
        for(Vector2d fp : freePositions){
            assertFalse(grassMap.isOccupied(fp));
        }

    }
    @Test
    public void isObjectAtPosition() throws PositionAlreadyOccupiedException {
        GrassField grassMap = new GrassField(100,UUID.fromString("1"));
        List<Vector2d> positions = List.of(
                new Vector2d(2,2),
                new Vector2d(0, 0),
                new Vector2d(7, 0),
                new Vector2d(76, -5),
                new Vector2d(2, 4),
                new Vector2d(-10, -4)
        );
        List<Animal> animals = List.of(
                new Animal(),
                new Animal(new Vector2d(0, 0),MapDirection.NORTH,0,0),
                new Animal(new Vector2d(7, 0), MapDirection.NORTH,0,0),
                new Animal(new Vector2d(76, -5), MapDirection.NORTH,0,0),
                new Animal(new Vector2d(2, 4), MapDirection.NORTH,0,0),
                new Animal(new Vector2d(-10, -4), MapDirection.NORTH,0,0)

        );
        List<Vector2d> freePositions = List.of(
                new Vector2d(3,28),
                new Vector2d(-1,-4),
                new Vector2d(-8,30),
                new Vector2d(60,23),
                new Vector2d(34,10),
                new Vector2d(-1,40)
        );
        for (Animal a : animals) {
                grassMap.place(a);

        }
        System.out.println(grassMap);
        for (int idx = 0 ;idx<positions.size();idx++) {
            assertEquals(animals.get(idx), grassMap.objectAt(positions.get(idx)));
            assertNull(grassMap.objectAt(freePositions.get(idx)));
        }


    }
    @Test
    public void areAnimalsPlacedCorrectly() throws PositionAlreadyOccupiedException {
        GrassField grassMap = new GrassField(100, UUID.fromString("1"));
        List<Vector2d> positions = List.of(
                new Vector2d(1, 9),
                new Vector2d(2, 2),
                new Vector2d(0,5),
                new Vector2d(0,0),
                new Vector2d(9,9)
        );

        List<Animal> animals =  List.of(
                new Animal(new Vector2d(1, 9),MapDirection.NORTH, 0,0),
                new Animal(),
                new Animal(new Vector2d(0,5), MapDirection.NORTH,0,0),
                new Animal(new Vector2d(0,0),MapDirection.NORTH, 0,0),
                new Animal(new Vector2d(9,9),MapDirection.NORTH, 0,0)
        );
        List<Animal> animalsWhitOnlyOneOtherPosition =  List.of(
                new Animal(new Vector2d(1, 9),MapDirection.NORTH, 0,0),
                new Animal(),
                new Animal(new Vector2d(0,5),MapDirection.NORTH, 0,0),
                new Animal(new Vector2d(0,0),MapDirection.NORTH, 0,0),
                new Animal(new Vector2d(9,9), MapDirection.NORTH,0,0),
                new Animal(new Vector2d(99,-9),MapDirection.NORTH, 0,0)
        );


        for(Animal a: animals) {
            grassMap.place(a);

        }

        for(int idx = 0;idx<positions.size();idx++){
            assertEquals(animals.get(idx), grassMap.getAnimals().get(positions.get(idx)));

        }
        for (Animal animal : animalsWhitOnlyOneOtherPosition) {
            try {
                grassMap.place(animal);

            } catch (PositionAlreadyOccupiedException e) {
                assertEquals("Position " + animal.getCurrentPosition() + " is already occupied.", e.getMessage());
            }
        }
        assertEquals(animals.size()+1, grassMap.getAnimals().size());

        for(int idx = 0;idx<positions.size();idx++){
            assertEquals(animals.get(idx), grassMap.getAnimals().get(positions.get(idx)));

        }
        assertEquals(animalsWhitOnlyOneOtherPosition.get(5), grassMap.getAnimals().get(new Vector2d(99,-9)));


    }

    @Test
    public void shouldAnimalsMoveCorrectly() throws PositionAlreadyOccupiedException {

        GrassField map2 = new GrassField(0, UUID.fromString("2"));
        List<Animal> animals1 =  List.of(new Animal(new Vector2d(0, 0),MapDirection.NORTH, 0,0),new Animal(), new Animal(new Vector2d(1,1),MapDirection.NORTH, 0,0));
        for (Animal a : animals1) {
            map2.place(a);

        }
        System.out.println(map2.getAnimals());
        List<Vector2d> finalPositions = List.of(new Vector2d(-4,0), new Vector2d(2, 7), new Vector2d(1, -2));
        List<Vector2d> mapLimits= List.of(new Vector2d(-4, -2), new Vector2d(2,7));
        List<MoveDirection>  moves= List.of(
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD
        );
        for(int i = 0; i < moves.size();i++){
            map2.move(animals1.get(i%3) , moves.get(i));
            System.out.println(map2);

        }
        assertEquals(mapLimits.get(0), map2.getLowerLeftLimit());
        assertEquals(mapLimits.get(1), map2.getUpperRightLimit());

        for(Vector2d fp : finalPositions){
            assertEquals(fp, map2.getAnimals().get(finalPositions.get(finalPositions.indexOf(fp))).getCurrentPosition());
        }

        GrassField grassMap = new GrassField(24,UUID.fromString("2"));
        List<Vector2d> positions = List.of(
                new Vector2d(4, 4),
                new Vector2d(5, 5),
                new Vector2d(5, 4),
                new Vector2d(4, 5)
        );
        List<Vector2d> expectedPositions = List.of(
                new Vector2d(4, 8),
                new Vector2d(5, 1),
                new Vector2d(10, 4),
                new Vector2d(1,5)
        );
        List<MapDirection> expectedOrientations = List.of(
                MapDirection.NORTH,
                MapDirection.SOUTH,
                MapDirection.EAST,
                MapDirection.WEST
        );

        List<MoveDirection> Moves = List.of(
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD
        );
        List<Animal>  animals =  new ArrayList<>();
        for(Vector2d pos: positions) {
            animals.add(new Animal(pos, MapDirection.NORTH,0,0));

        }
        for(Animal a : animals) {
            grassMap.place(a);

        }
        System.out.println(grassMap);
        for(int i=0; i < Moves.size();i++){
            grassMap.move(animals.get(i%4), Moves.get(i));
            System.out.println(grassMap);
        }

        for (int idx =0 ;idx < expectedOrientations.size() ;idx++){
            assertEquals(expectedPositions.get(idx), grassMap.getAnimals().get(expectedPositions.get(idx)).getCurrentPosition());
        }

    }
}
