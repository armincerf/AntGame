package test;

import com.AntGame.Controller.AntController;
import com.AntGame.Controller.Condition;
import com.AntGame.Controller.GameController;
import com.AntGame.Controller.MapController;
import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
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
        try {
            mc.createMapFromFile("C:\\Users\\Bradley\\Documents\\GitHub\\AntGame\\core\\assets\\tiny.world");
        } catch (IOException e) {
            e.printStackTrace();
        }



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


        //Place two friendly ants next to each other
        Ant a1 = new Ant(Position.set(3, 1), Colour.Red, Direction.Right );
        a1.setHasFood(true); //give a1 food
        Ant a2 = new Ant(Position.set(4, 1), Colour.Black, Direction.Right );
        a2.setHasFood(true);
        mc.setAntAt(a1.getAntPositon(), a1);
        mc.setMarkerAt(Position.set(5, 1), Colour.Black, 2);

        //Check if sense friend works
        assertEquals(gc.CellMatches(a1.getAntPositon(), Condition.friend, Colour.Red), true);

        //Check if sense foe works
        assertEquals(gc.CellMatches(a1.getAntPositon(), Condition.foe, Colour.Black), true);

        //check if friend with food works
        assertEquals(gc.CellMatches(a1.getAntPositon(), Condition.friendwithfood, Colour.Red), true);

        //check if foe with food works
        assertEquals(gc.CellMatches(a1.getAntPositon(), Condition.foewithfood, Colour.Black), true);

        //Check food works
        assertEquals(gc.CellMatches(Position.set(1, 1), Condition.food, Colour.Black), true);

        //check if can sense rocks
        assertEquals(gc.CellMatches(Position.set(0,0), Condition.rock, Colour.Black), true);

        //Check if can sense markers

        Condition.marker.markerNum = 2;

        assertEquals(gc.CellMatches(Position.set(5,1), Condition.marker , Colour.Black), true);

        //Check if can sense foe markers

        Condition.marker.markerNum = 2;

        assertEquals(gc.CellMatches(Position.set(5,1), Condition.foemarker , Colour.Red), true);

        //Check if can sense home ant hill

        assertEquals(gc.CellMatches(Position.set(1, 2), Condition.home, Colour.Red), true);

        //Check if can sense foe ant hill

        assertEquals(gc.CellMatches(Position.set(1, 2), Condition.foehome, Colour.Black), true);

    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals(gc.getScore(Colour.Black), 0);

    }

    @Test
    public void testGetMoves() throws Exception {
        assertEquals(gc.getMoves(), 0);
    }

    @Test
    public void testRandomInt() throws Exception {
        //TODO:WRITE RANDOM INT
    }

    @Test
    public void testStep() throws Exception {
        Ant a1 = new Ant(Position.set(3, 1), Colour.Red, Direction.Right );

        int curmoves = gc.getMoves();

        //Call Step Method
        gc.step(a1.getID());

        // Check if step method worked by if moves have increased
        assertEquals(gc.getMoves(), curmoves + 1);

    }
}