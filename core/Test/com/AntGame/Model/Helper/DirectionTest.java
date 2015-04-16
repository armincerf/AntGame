package com.AntGame.Model.Helper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    Direction d;

    @Before
    public void setUp() throws Exception
    {
        d = Direction.DownLeft;
    }


    @Test
    public void testGetEnumeratedDirection() throws Exception {
        assertEquals(d.getEnumeratedDirection(), 2);
    }

    @Test
    public void testTurn() throws Exception {
        //Turn Left- Should now be facing left
        d.turn(Left_or_Right.left);

        assertEquals(d, Direction.Left);
    }

    @Test
    public void testFromInt() throws Exception {
        assertEquals(Direction.fromInt(0), Direction.Right);
    }
}