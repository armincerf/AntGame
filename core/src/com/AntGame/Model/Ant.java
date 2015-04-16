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
     * Get the id of the ant
     * @return ID of the ant.
     */
    public int getID(){ return _antID;  }

    /**
     * Get the position of the ant
     * @return Position of the ant.
     */
    public Position getAntPositon() {   return _antPosition;  }

    /**
     * Get the colour of the ant
     * @return Colour of the ant (red or black)
     */
    public Colour getAntColour() {   return _antColour;  }

    /**
     * Get the integer brain state
     * @return Integer representing the current brain state.
     */
    public int getBrainState() {    return _brainState; }

    /**
     * Set the ants brain state
     * @param state sets the ants brain state.
     */
    public void setBrainState(int state){   this._brainState = state;   }

    /**
     * Get the amount of time to rest
     * @return amount of time to rest
     */
    public int getResting() {   return _resting;    }

    /**
     * Set the amount of time to rest
     */
    public void setRestPeriod() {
        this._resting = 14;
    }

    /**
     * Decrement the time of resting
     */
    public void decrementRest(){    this._resting--;}

    /**
     * Gets true or false depending if the ant has food
     * @return true if the ant has food, false otherwise
     */
    public boolean hasFood(){   return _hasFood;    }

    /**
     * set true if the ant has food, false otherwise
     * @param hasFood true if the ant has food, false otherwise
     */
    public void setHasFood(boolean hasFood) {   _hasFood = hasFood; }

    /**
     * Get the ant marker number
     * @return the ant marker number
     */
    public int getAntMarkerNum()
    {
        return _markerNum++ % 5;
    }

    /**
     * Get the ants direction
     * @return the ants direction
     */
    public Direction getAntDirection() {    return _antDirection;   }

    /**
     * Set the ants direction
     * @param direction the ants direction
     */
    public void setAntDirection(Direction direction) {
        this._antDirection = direction;
    }

    /**
     * Set the position of the ant.
     * @param position ant position
     */
    public void set_antPosition(Position position) { this._antPosition = position;}



}
