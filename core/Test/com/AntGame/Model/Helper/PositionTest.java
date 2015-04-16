package com.AntGame.Model.Helper;

import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    Position p;

    @Before
    public void setUp() throws Exception {
        p = Position.set(0,0);
    }

    @Test
    public void testSetPosition() throws Exception {
        p.setPosition(1,1);

        assertEquals(p.get_x(), 1);
        assertEquals(p.get_y(), 1);

    }

    @Test
    public void testSet() throws Exception {
        Position p2 = Position.set(1,1);

        assertEquals(p2.get_x(), 1);
        assertEquals(p2.get_y(), 1);
    }
}