package com.AntGame.Controller;



import com.AntGame.Model.Helper.Left_or_Right;
import com.AntGame.Model.Helper.Marker;
import com.AntGame.Model.Helper.SenseDirection;

/**
 * Created by Bradley on 17/03/2015.
 */



public enum Instruction {

    Sense,
    Mark,
    Unmark,
    Pickup,
    Drop,
    Turn,
    Move,
    Flip;

    public Condition condition;
    public Marker marker;
    public SenseDirection senseDirection;
    public int state1, state2, n;
    public Left_or_Right lr;

}
