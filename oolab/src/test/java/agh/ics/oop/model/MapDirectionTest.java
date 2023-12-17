package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {

    @Test
    public void isNextDirectionCorrect(){
        MapDirection direction = MapDirection.NORTH.next();
        assertEquals(MapDirection.EAST, direction);
        direction = direction.next();
        assertEquals(MapDirection.SOUTH, direction);
        direction = direction.next();
        assertEquals(MapDirection.WEST, direction);
        direction = direction.next();
        assertEquals(MapDirection.NORTH, direction);
    }

    @Test
    public void isPreviousDirectionCorrect(){
        MapDirection direction = MapDirection.NORTH.previous();
        assertEquals(MapDirection.WEST, direction);
        direction = direction.previous();
        assertEquals(MapDirection.SOUTH, direction);
        direction = direction.previous();
        assertEquals(MapDirection.EAST, direction);
        direction = direction.previous();
        assertEquals(MapDirection.NORTH, direction);
    }

}
