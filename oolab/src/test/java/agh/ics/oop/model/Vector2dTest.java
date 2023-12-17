package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class Vector2dTest {

    @Test
    public void isXGetterCorrect(){
        Vector2d v = new Vector2d(9, 10);
        assertEquals(9, v.getX());
        assertNotEquals(10, v.getX());
    }

    @Test
    public void isYGetterCorrect(){
        Vector2d v = new Vector2d(-98, 123);
        assertEquals(123, v.getY());
        assertNotEquals(0, v.getY());
    }
    @Test
    public void convertVectorToString(){
        Vector2d v1 = new Vector2d(1, 4);
        String s1 = "(1,4)";
        Vector2d v2 = new Vector2d(0, 0);
        assertEquals(s1, v1.toString());
        // Sprawdzam czy jakieś dodatkowe spacje nie zostaną zaakceptowane
        assertNotEquals("(0, 0)" , v2.toString());

    }

    @Test
    public void shouldV1ProceedsOther(){
        Vector2d v1 = new Vector2d(1, 3);
        Vector2d other = new Vector2d(2, 6);
        Vector2d other1 = new Vector2d(0, 2);
        Vector2d other2 = new Vector2d(1, 3);
        assertTrue(v1.precedes(other));
        assertFalse(v1.precedes(other1));
        assertTrue(v1.precedes(other2));

    }

    @Test
    public void shouldV1FollowsOther(){
        Vector2d v1 = new Vector2d(10, 5);
        Vector2d other = new Vector2d(9,5 );
        Vector2d other1 = new Vector2d(11, 2);
        Vector2d other2 = new Vector2d(10, 5);
        assertTrue(v1.follows(other));
        assertFalse(v1.follows(other1));
        assertTrue(v1.follows(other2));

    }

    @Test
    public void shouldSumV1AndV2EqualsV3(){
        Vector2d v1 = new Vector2d(10, 23);
        Vector2d v2 = new Vector2d(15, 7);
        Vector2d v3 = new Vector2d(25, 30);
        Vector2d v4 = new Vector2d(-29, -18);
        Vector2d v5 = new Vector2d(-9, -17);
        Vector2d v6 = new Vector2d(-38, -35);
        assertEquals(v3, v1.add(v2));
        assertNotEquals(v1, v2.add(v3));
        assertEquals(v6, v4.add(v5));


    }

    @Test
    public void shouldDiffV1AndV2EqualsV3(){
        Vector2d v1 = new Vector2d(20, 100);
        Vector2d v2 = new Vector2d(-9, 50);
        Vector2d v3 = new Vector2d(29, 50);
        Vector2d v4 = new Vector2d(-19, -20);
        Vector2d v5 = new Vector2d(-23, -9);
        Vector2d v6 = new Vector2d(4, -11);
        assertEquals(v3, v1.subtract(v2));
        assertNotEquals(v1, v3.subtract(v2));
        assertEquals(v6, v4.subtract(v5));


    }
    @Test
    public void isUpperRightOfTwoVectors(){
        Vector2d v1 = new Vector2d(5, 30);
        Vector2d v2 = new Vector2d(10, 25);
        Vector2d UpperRight =  new Vector2d(10, 30);
        Vector2d v3 = new Vector2d(10, 50);
        Vector2d v4 = new Vector2d(-10, 679);
        Vector2d UpperRight2 = new Vector2d(100, 1000);
        assertEquals(UpperRight, v1.upperRight(v2));
        assertNotEquals(UpperRight2, v4.upperRight(v3));

    }

    @Test
    public void isLowerLeftOfTwoVectors(){
        Vector2d v1 = new Vector2d(20, 50);
        Vector2d v2 = new Vector2d(100, -89);
        Vector2d LowerLeft =  new Vector2d(20, -89);
        Vector2d v3 = new Vector2d(11, 50);
        Vector2d v4 = new Vector2d(99, 90);
        Vector2d LowerLeft2 = new Vector2d(0, 1);
        assertEquals(LowerLeft, v1.lowerLeft(v2));
        assertNotEquals(LowerLeft2, v3.lowerLeft(v4));

    }

    @Test
    public void isOppositeOfV(){
        Vector2d v = new Vector2d(10,99);
        Vector2d vOpposite = new Vector2d(-10, -99);
        Vector2d vNotOpposite = new Vector2d(10, -99);
        Vector2d v1 = new Vector2d(0,-1);
        Vector2d v1Opposite = new Vector2d(0,1);
        Vector2d v1NotOpposite = new Vector2d(1,0 );
        assertEquals(vOpposite, v.opposite());
        assertNotEquals(vNotOpposite, v.opposite());
        assertEquals(v1Opposite, v1.opposite());
        assertNotEquals(v1NotOpposite, v1.opposite());

    }

    @Test
    public void ShouldV1EqualV2(){
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        Vector2d v3  = new Vector2d(2, 1);
        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));

    }
}
