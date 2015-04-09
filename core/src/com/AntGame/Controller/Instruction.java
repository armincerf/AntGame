package com.AntGame.Controller;



import com.AntGame.Model.Helper.Left_or_Right;
import com.AntGame.Model.Helper.Marker;
import com.AntGame.Model.Helper.SenseDirection;

/**
 * Created by Bradley on 17/03/2015.
 */


enum InstructionEnum {
    Sense,
    Mark,
    Unmark,
    Pickup,
    Drop,
    Turn,
    Move,
    Flip;
}

public class Instruction {


    public Condition condition;
    public Marker marker;
    public SenseDirection senseDirection;
    public int state1, state2, n;
    public Left_or_Right lr;
    public InstructionEnum instrType;

    @Override
    public String toString() {
        return ("Instruction type \t condition \n" + instrType + "\t\t\t\t\t" + senseDirection);
    }


}
