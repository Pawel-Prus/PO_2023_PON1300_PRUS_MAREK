package agh.ics.oop;
import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import static agh.ics.oop.OptionsParser.parseToEnum;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.UUID;

public class SimulationIntegrationTest {
    @Test
    public void shouldSimulationWorkCorrect(){
    List<Vector2d> positions = List.of(
            new Vector2d(4, 4),
            new Vector2d(5, 5),
            new Vector2d(5, 4),
            new Vector2d(4, 5)

    );
    String [] moves = {"f", "r", "r","l","f","r","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","b"};
    List<MoveDirection> expectedMoves = List.of(
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
    List<MoveDirection> parsedMoves = parseToEnum(moves);
    assertIterableEquals(expectedMoves, parsedMoves);

    RectangularMap map = new RectangularMap(12, 12, UUID.fromString("1"));
    Simulation simulation = new Simulation(parsedMoves, positions, map);
    System.out.println(simulation.getAnimals());
    simulation.run();
    List<MapDirection> expectedOrientations = List.of(MapDirection.NORTH,MapDirection.SOUTH,MapDirection.EAST, MapDirection.WEST);
    List<Vector2d> expectedPositions = List.of(new Vector2d(4, 8),new Vector2d(5, 1),new Vector2d(10, 4), new Vector2d(1,5));
    assertEquals(12,map.getHeight());
    assertEquals(12,map.getWidth());
    assertEquals(new Vector2d(0,0), map.getLowerLeftLimit());
        assertEquals(new Vector2d(11,11), map.getUpperRightLimit());
    for (int idx =0 ;idx < expectedOrientations.size() ;idx++){
        assertEquals(expectedOrientations.get(idx),map.objectAt(expectedPositions.get(idx)).getCurrentOrientation());
        assertTrue(map.isOccupied(expectedPositions.get(idx)));
    }




    }

}
