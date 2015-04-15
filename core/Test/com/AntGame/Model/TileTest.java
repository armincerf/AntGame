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

public class TileTest {
    GameController gc;
    AntController ac;
    MapController mc;
    Ant a;
    Position p;
    Tile t;

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
        t = new Tile(TileType.Clear, new Position(3, 3));
    }

    @Test
    public void testPutFood() throws Exception {
        t.putFood(3);
        assertEquals(3, t.getFood());
    }

    @Test
    public void testIsAntHill() throws Exception {
        assertFalse(t.isAntHill());
        t.set_tileType(TileType.antHill);
        assertTrue(t.isAntHill());
    }

    @Test
    public void testHasAliveAnt() throws Exception {
        assertFalse(t.hasAliveAnt());
        a.set_antPosition(new Position(3, 3));
        assertTrue(t.hasAliveAnt());
    }

    @Test
    public void testClearAnt() throws Exception {
        a.set_antPosition(new Position(3, 3));
        t.clearAnt();
        assertFalse(t.hasAliveAnt());
    }

    @Test
    public void testPutAntOnTile() throws Exception {
        Ant b = new Ant(new Position(5, 6), Colour.Black, Direction.DownLeft);
        t = new Tile(TileType.Clear, new Position(5, 6));

        assertFalse(t.hasAliveAnt());
    }

    @Test
    public void testPutMarkerOnTile() throws Exception {

    }

    @Test
    public void testRemoveMarker() throws Exception {

    }
}