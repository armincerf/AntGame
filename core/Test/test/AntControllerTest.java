package test;

import com.AntGame.Controller.AntController;
import com.AntGame.Controller.GameController;
import com.AntGame.Controller.MapController;
import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AntControllerTest {
    GameController gc;
    AntController ac;
    MapController mc;
    Ant a;
    Position p;

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

    @Test
    public void testGetAnt() throws Exception {
        mc.setAntAt(p, a);
        assertEquals(ac.getAnt(a.getID()).getAntColour(), Colour.Black);
        assertEquals(ac.getAnt(a.getID()).getAntPositon(), p);
        assertEquals(ac.getAnt(a.getID()), a);
    }


    @Test
    public void testFindAnt() throws Exception {
        assertEquals(ac.findAnt(a.getID()), p);
    }

    @Test
    public void testOtherColour() throws Exception {
        assertEquals(ac.otherColour(Colour.Black), Colour.Red);
    }

    @Test
    public void testGetMap() throws Exception {
        assertTrue(ac.getMap().containsValue(a));
        ac.getMap().clear();
        assertTrue(ac.getMap().isEmpty());
    }

    @Test
    public void testAddAnt() throws Exception {
        Ant b = new Ant(p, Colour.Black, Direction.DownLeft);
        assertEquals(ac.findAnt(b.getID()), null);
        ac.addAnt(b);
        assertEquals(ac.findAnt(b.getID()), b.getAntPositon());

    }
}