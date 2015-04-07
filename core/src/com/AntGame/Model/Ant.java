package com.AntGame.Model;

import com.AntGame.Model.Helper.*;

/**
 * Created by Bradley on 16/03/2015.
 */
public class Ant {

    private int _antID;
    private Position _antPosition;
    private Colour _antColour;
    private Direction _antDirection;
    private int _brainState;
    private int _resting;
    private boolean _hasFood;
    private int _markerNum;

    public Ant(Position position, Colour colour, Direction direction)
    {
        this._antID = IDGenerator.AssignID();
        this._antPosition = position;
        this._antColour = colour;
        this._antDirection = direction;
        this._brainState = 0;
        this._resting = 0;
        this._markerNum = 0;
        this._hasFood = false;
    }

    public int getID(){ return _antID;  }
    public Position getAntPositon() {   return _antPosition;  }
    public Colour getAntColour() {   return _antColour;  }
    public int getBrainState() {    return _brainState; }
    public void setBrainState(int state){   this._brainState = state;   }
    public int getResting() {   return _resting;    }
    public void setRestPeriod(int periodLength){    this._resting = periodLength;    }
    public void decrementRest(){    this._resting--;}
    public boolean hasFood(){   return _hasFood;    }
    public void setHasFood(boolean hasFood) {   _hasFood = hasFood; }
    public int getAntMarkerNum()
    {
        return _markerNum++ % 5;
    }
    public Direction getAntDirection() {    return _antDirection;   }
    public void setAntDirection(Direction direction) {  this._antDirection = direction;
        System.out.println(direction);}
    public void set_antPosition(Position position) { this._antPosition = position;}



}
