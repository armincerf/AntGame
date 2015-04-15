package com.AntGame.Model;

import com.AntGame.Controller.AntController;
import com.AntGame.Controller.GameController;
import com.AntGame.Controller.MapController;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AntTest {
    GameController gc;
    AntController ac;
    MapController mc;
    Ant a;
    Position p;

    /**
     * Sets up test
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        gc = new GameController();
        gc.Initialize();
        ac = gc.getAntController();
        mc = gc.getMapController();
        p = new Position(100, 100);
        mc.createRandomMap();
        a = new Ant(p, Colour.Black, Direction.DownLeft);
        ac.addAnt(a);
    }

    /**
     * Tests before and after resting
     *
     * @throws Exception
     */
    @Test
    public void testDecrementRest() throws Exception {
        int start = a.getResting();
        a.decrementRest();
        int end = a.getResting();
        assertNotEquals(start, end);
        assertEquals(start - 1, end);
    }

    /**
     * tests if hasfood returns correct value
     *
     * @throws Exception
     */
    @Test
    public void testHasFood() throws Exception {
        assertFalse(a.hasFood());
        a.setHasFood(true);
        assertTrue(a.hasFood());
    }
}