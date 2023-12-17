package agh.ics.oop;
import static agh.ics.oop.OptionsParser.parseToEnum;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionsParserTest {
//    @Test
//    public void hasOnlyExpectedMoves(){
//        String [] moves1 = {"f", "l", "r", "g", "t", "f", " i"};
//        String [] expectedMoves1 = {"f", "l", "r", "f"};
//        String [] testMoves1 = getExpectedMoves(moves1);
//        String [] moves2 = {"R", "F", "0", "ola", "kr", "r ", " f "};
//        String [] expectedMoves2 = {};
//        String [] testMoves2 = getExpectedMoves(moves2);
//        assertEquals(expectedMoves1.length, testMoves1.length);
//        // Przypadek gdy żaden ruch nie jest poprawny
//        assertEquals(expectedMoves2.length, testMoves2.length);
//        for(int idx = 0; idx < expectedMoves1.length; idx++) {
//            assertEquals(expectedMoves1[idx], testMoves1[idx]);
//        }
//    }

    @Test
    public void areMovesCorrectlyParsedToEnum(){
        String [] moves1 = {"f", "l", "r", "f", "r"};
        String [] moves2 = {"f", "l", "r", "g", "t", "f", " i" , "L", "G", "r", "ala", "123"};
        List<MoveDirection> parsedMoves1 = Arrays.asList(
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT

        );
        List<MoveDirection> testMoves1 = parseToEnum(moves1);
        try{

            assertThrows(IllegalArgumentException.class, () -> parseToEnum(moves2));
        } catch (IllegalArgumentException e) {
            fail(e + " is not legal move specification");
        }
        String [] moves3 = {"F ", "lr" , "a;a" , "123455" , "f ", " r"};

        try{

            assertThrows(IllegalArgumentException.class, () -> parseToEnum(moves3));
        } catch (IllegalArgumentException e) {
            fail(e + " is not legal move specification");
        }
        assertEquals(parsedMoves1.size(), testMoves1.size());
        assertIterableEquals(parsedMoves1, testMoves1);

    }
}
