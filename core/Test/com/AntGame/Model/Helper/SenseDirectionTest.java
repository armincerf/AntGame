package com.AntGame.Model.Helper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SenseDirectionTest {



    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAdjacent_cell() throws Exception {

    }

    @Test
    public void testSensed_cell() throws Exception {
    	Position p = Position.Set(1,1);

   		//Check still in the same location
    	assertEquals(SenseDirection.sensed_cell(p, Direction.Right, SenseDirection.here), Position.Set(1,1));
    	//Check one cell to the right
    	assertEquals(SenseDirection.sensed_cell(p, Direction.Right, SenseDirection.ahead), Position.Set(2,1));
		
		assertEquals(SenseDirection.sensed_cell(p, Direction.UpRight, SenseDirection.rightahead), Position.Set(2,1));

		assertEquals(SenseDirection.sensed_cell(p, Direction.DownRight, SenseDirection.leftahead), Position.Set(2,1));


    }
}