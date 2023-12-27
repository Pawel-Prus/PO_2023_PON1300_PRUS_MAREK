package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AnimalTest {
    @Test
    public void isConvertingToStringCorrect(){
        Vector2d v1 = new Vector2d(1,3);
        Animal animal = new Animal(v1,MapDirection.NORTH, 0,0);
        String expectedOutput  = "^";
        String invalidOutput  = ">";
        assertEquals(expectedOutput, animal.toString());
        assertNotEquals(invalidOutput, animal.toString());

    }
    @Test
    public void isAnimalAtPosition(){
        Animal animal = new Animal();
        Vector2d pos = new Vector2d(2, 4);
        Animal animal1 = new Animal(pos,MapDirection.NORTH, 0,0);
        assertFalse(animal.isAt(pos));
        assertTrue(animal1.isAt(pos));

    }

    @Test
    public void CanAnimalMoveToPosition(){
        Animal animal = new Animal();
        Vector2d pos = new Vector2d(4, 4);
        Vector2d pos1 = new Vector2d(5, 4);
        Vector2d pos2 = new Vector2d(-1, 5);
        Vector2d pos3 = new Vector2d(0, 0);
        assertTrue(animal.canMoveTo(pos));
        assertTrue(animal.canMoveTo(pos3));
        assertFalse(animal.canMoveTo(pos1));
        assertFalse(animal.canMoveTo(pos2));
    }

    @Test
    public void isNextPositionCalculateCorrectly(){
        Animal animal = new Animal();
        Animal animal1 = new Animal(new Vector2d(3, 4),MapDirection.NORTH, 0,0);
        Animal animal2 = new Animal(new Vector2d(0, 0), MapDirection.NORTH,0,0);
        MoveDirection direction1 = MoveDirection.FORWARD;
        MoveDirection direction2 = MoveDirection.BACKWARD;
        MoveDirection direction3 = MoveDirection.RIGHT;
        MoveDirection direction4 = MoveDirection.LEFT;
        Vector2d expectedPos1 = new Vector2d(2, 3);
        Vector2d expectedPos2 = new Vector2d(3, 3);
        Vector2d expectedPos3 = new Vector2d(0, 0);
        Vector2d expectedPos4 = new Vector2d(0, 1);
        assertEquals(expectedPos1, animal.calculateNextPosition(0));
        assertEquals(expectedPos2, animal1.calculateNextPosition(4));
        assertEquals(expectedPos3, animal2.calculateNextPosition(2));
        assertEquals(expectedPos4, animal2.calculateNextPosition(0));
        assertEquals(expectedPos3, animal2.calculateNextPosition(6));
        assertEquals(expectedPos4, animal2.calculateNextPosition(0));

    }
    @Test
    public void isPositionGetterWorksCorrect(){
        Animal animal = new Animal();
        Animal animal1 = new Animal(new Vector2d(3, 2),MapDirection.NORTH, 0,0);
        Vector2d expectedPosition1 = new Vector2d(2, 2);
        Vector2d expectedPosition2 = new Vector2d(3, 2);
        assertEquals(expectedPosition1, animal.getCurrentPosition());
        assertEquals(expectedPosition2, animal1.getCurrentPosition());
    }

    @Test
    public void isOrientationGetterWorksCorrect(){
        Animal animal = new Animal();
        Animal animal1 = new Animal(new Vector2d(3, 2),MapDirection.NORTH, 0,0);
        MapDirection direction1 = MapDirection.NORTH;
        MapDirection direction2 = MapDirection.EAST;
        assertEquals(direction1, animal.getCurrentOrientation());
        assertEquals(direction1, animal1.getCurrentOrientation());
        assertNotEquals(direction2, animal1.getCurrentOrientation());
    }
}
