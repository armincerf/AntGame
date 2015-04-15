package com.AntGame.Controller;

import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapControllerTest {

    MapController mc;


    @Before
    public void setUp() throws Exception {
        mc = new MapController();
    }

    @Test
    public void testGetMap() throws Exception {
        mc.createRandomMap();

        assertNotEquals(mc.getMap(), null);
    }

    @Test
    public void testCreateMapFromFile() throws Exception {
        mc.createMapFromFile("C:\\Users\\Bradley\\Documents\\GitHub\\AntGame\\core\\assets\\tiny.world");
    }


    @Test
    public void testCreateRandomMap() throws Exception {
        mc.createRandomMap();

        assertNotEquals(mc.getMap(), null);
    }


    @Test
    public void testCheckHillSpace() throws Exception {
        mc.createRandomMap();

        for(int i = 0; i <= mc.getHeight(); i++){
            for(int j = 0; j < mc.getWidth(); j++){
                if(mc.getMap().getRow(i).getTile(j).isAntHill()){
                    assertEquals(mc.checkHillSpace(j,i), true);
                }
            }
        }
    }

    @Test
    public void testIsAntAt() throws Exception {
        mc.createMapFromFile("C:\\Users\\Bradley\\Documents\\GitHub\\AntGame\\core\\assets\\tiny.world");
        Ant a1 = new Ant(Position.set(4, 1), Colour.Red, Direction.Right );
        mc.setAntAt(Position.set(4,1), a1);
        assertEquals(mc.isAntAt(Position.set(4, 1)), true);
    }

    @Test
    public void testGetAntAt() throws Exception {
        mc.createMapFromFile("C:\\Users\\Bradley\\Documents\\GitHub\\AntGame\\core\\assets\\tiny.world");
        Ant a1 = new Ant(Position.set(4, 1), Colour.Red, Direction.Right );
        mc.setAntAt(Position.set(4,1), a1);
        assertNotEquals(mc.getAntAt(Position.set(4, 1)), null);

    }

    @Test
    public void testSetAntAt() throws Exception {
        mc.createMapFromFile("C:\\Users\\Bradley\\Documents\\GitHub\\AntGame\\core\\assets\\tiny.world");
        Ant a1 = new Ant(Position.set(4, 1), Colour.Red, Direction.Right );
        mc.setAntAt(Position.set(4,1), a1);
        assertEquals(mc.isAntAt(Position.set(4, 1)), true);

    }

    @Test
    public void testClearAntAt() throws Exception {
        //SET ANT AT
        mc.createMapFromFile("C:\\Users\\Bradley\\Documents\\GitHub\\AntGame\\core\\assets\\tiny.world");
        Ant a1 = new Ant(Position.set(4, 1), Colour.Red, Direction.Right );
        mc.setAntAt(Position.set(4,1), a1);

        //CLEAR ANT
        mc.clearAntAt(Position.set(4,1));

    }

    @Test
    public void testFoodAt() throws Exception {

    }

    @Test
    public void testSetFoodAt() throws Exception {

    }

    @Test
    public void testAntHillAt() throws Exception {

    }

    @Test
    public void testSetMarkerAt() throws Exception {

    }

    @Test
    public void testClearMarkerAt() throws Exception {

    }

    @Test
    public void testCheckMarkerAt() throws Exception {

    }

    @Test
    public void testCheckIfAnyMarkerAt() throws Exception {

    }

    @Test
    public void testGetWidth() throws Exception {

    }

    @Test
    public void testGetHeight() throws Exception {

    }
}