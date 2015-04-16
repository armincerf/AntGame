package com.AntGame.Model.Helper;

import com.AntGame.Model.Tile;
import com.AntGame.Model.TileType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapRowTest {

    MapRow mr;

    @Before
    public void setUp() throws Exception
    {
        mr = new MapRow(0, 10); // ROW 0 WIDTH 10
        mr.setTile(0, new Tile(TileType.Rocky, Position.set(0,0)));
    }


    @Test
    public void testGetRowNum() throws Exception {
        assertEquals(0, mr.getRowNum());
    }

    @Test
    public void testGetRow() throws Exception {
        assertNotEquals(mr.GetRow(), null);
    }

    @Test
    public void testGetTile() throws Exception {
        assertNotEquals(mr.getTile(0), null);

    }

    @Test
    public void testSetTile() throws Exception {
        mr.setTile(1, new Tile(TileType.Rocky, Position.set(1,0)));

        assertNotEquals(mr.getTile(1), null);

    }
}