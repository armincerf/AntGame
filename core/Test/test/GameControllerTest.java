package test;

import com.AntGame.Controller.AntController;
import com.AntGame.Controller.GameController;
import com.AntGame.Controller.MapController;
import com.AntGame.Model.Helper.Colour;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GameControllerTest {

    GameController gc;
    MapController mc;
    AntController ac;

    @Before
    public void SetUp()
    {
        try {
            gc = new GameController();
        } catch (IOException e) {
            e.printStackTrace();
        }


        mc = gc.getMapController();
        ac = gc.getAntController();

        String brain = "C:\\Users\\Bradley\\Documents\\GitHub\\AntGame\\core\\assets\\brain1.brain";
        gc.setAntInstructions(brain, brain);
        mc.createRandomMap();

        
    }


    @Test
    public void testSetAntInstructions() throws Exception {
        assertNotEquals(gc.getAntInstructions(Colour.Red).size(), 0);
        assertNotEquals(gc.getAntInstructions(Colour.Black).size(), 0);
    }

    @Test
    public void testGet_instruction() throws Exception {
        assertNotEquals(gc.get_instruction(Colour.Red, 0), null);
    }

    @Test
    public void testGetMapController() throws Exception {
        assertNotEquals(gc.getMapController(), null);
    }

    @Test
    public void testGetAntController() throws Exception {
        assertNotEquals(gc.getAntController(), null);

    }

    @Test
    public void testCellMatches() throws Exception {

    }

    @Test
    public void testGetScore() throws Exception {

    }

    @Test
    public void testGetMoves() throws Exception {

    }

    @Test
    public void testRandomInt() throws Exception {

    }

    @Test
    public void testStep() throws Exception {

    }
}