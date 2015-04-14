package test;

import static org.junit.Assert.*;

import com.AntGame.Controller.AntBrainReader;
import com.AntGame.Controller.Instruction;
import com.AntGame.Model.Ant;
import org.junit.*;
import org.junit.Before;

import java.io.IOException;

public class AntBrainTest {
    String brain;

    @Before

    public void setUp() {
        brain = "/Users/alexdavis/Documents/AntGame/core/assets/brain1.brain";
    }

    @Test
    public void readBrainFromFile() throws IOException {
        for (Instruction i : AntBrainReader.readBrainFile(brain)) {
            assertTrue(i != null);
        }
    }
}