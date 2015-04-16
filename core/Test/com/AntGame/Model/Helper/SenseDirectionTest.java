package com.AntGame.Model.Helper;

import com.AntGame.Controller.MapController;
import javafx.geometry.Pos;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SenseDirectionTest {
    MapController mc;
    Position p, b;

    @Before
    public void setUp() throws Exception {
        p = new Position(100, 100);
        mc = new MapController();

    }

    @Test
    public void testAdjacent_cell() throws Exception {
        mc.createRandomMap();

        b = new Position(101, 101);
        assertEquals(SenseDirection.adjacent_cell(p, Direction.DownRight).toString(), b.toString());

        b = new Position(100, 101);
        assertEquals(SenseDirection.adjacent_cell(p, Direction.DownLeft).toString(), b.toString());

        b = new Position(99, 100);
        assertEquals(SenseDirection.adjacent_cell(p, Direction.Left).toString(), b.toString());

        b = new Position(100, 99);
        assertEquals(SenseDirection.adjacent_cell(p, Direction.UpLeft).toString(), b.toString());

        b = new Position(101, 101);
        assertEquals(SenseDirection.adjacent_cell(p, Direction.DownRight).toString(), b.toString());
    }

    @Test
    public void testSensed_cell() throws Exception {
        Position p = Position.set(1, 1);

   		//Check still in the same location
        assertEquals(SenseDirection.sensed_cell(p, Direction.Right, SenseDirection.here).toString(), Position.set(1, 1).toString());
        //Check one cell to the right
        assertEquals(SenseDirection.sensed_cell(p, Direction.Right, SenseDirection.ahead).toString(), Position.set(2, 1).toString());

        assertEquals(SenseDirection.sensed_cell(p, Direction.UpRight, SenseDirection.rightahead).toString(), Position.set(2, 1).toString());

        assertEquals(SenseDirection.sensed_cell(p, Direction.DownRight, SenseDirection.leftahead).toString(), Position.set(2, 1).toString());


    }
}