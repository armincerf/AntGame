package com.AntGame.Model;

import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.IDGenerator;
import com.AntGame.Model.Helper.Position;

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

    /**
     * Creates a new ant object
     * @param position The coordinates of the ant.
     * @param colour The colour of the ant (red or black).
     * @param direction The direction the ant is facing.
     */
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

    /**
     *
     * @return ID of the ant.
     */
    public int getID(){ return _antID;  }

    /**
     *
     * @return Position of the ant.
     */
    public Position getAntPositon() {   return _antPosition;  }

    /**
     *
     * @return Colour of the ant (red or black)
     */
    public Colour getAntColour() {   return _antColour;  }

    /**
     *
     * @return Integer representing the current brain state.
     */
    public int getBrainState() {    return _brainState; }

    /**
     *
     * @param state sets the ants brain state.
     */
    public void setBrainState(int state){   this._brainState = state;   }

    /**
     *
     * @return amount of time to rest
     */
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

    public void setAntDirection(Direction direction) {
        this._antDirection = direction;
    }
    public void set_antPosition(Position position) { this._antPosition = position;}



}
