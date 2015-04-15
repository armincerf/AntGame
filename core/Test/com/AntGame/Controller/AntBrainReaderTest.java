package com.AntGame.Controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AntBrainReaderTest {

    AntBrainReader ar;

    @Before
    public void setUp() throws Exception
    {
        ar = new AntBrainReader();

    }


    @Test
    public void testReadBrainFile() throws Exception {
        String brain = "assets/brain1.brain";
        assertNotEquals(ar.readBrainFile(brain), null);
    }



    @Test
    public void testSyntaxCheck() throws Exception {
        ar.syntaxCheck("sense ahead 1 3 Food");

        assertEquals(ar.getCorrect(), true);
    }

    @Test
    public void testBuildInstruction() throws Exception {
        assertNotEquals(ar.buildInstruction("sense ahead 1 3 Food"), null);

    }
}











