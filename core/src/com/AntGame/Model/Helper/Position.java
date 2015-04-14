package com.AntGame.Model.Helper;

import com.AntGame.Controller.OutOfMapException;

/**
 * Created by Bradley on 16/03/2015.
 */
public class Position {
    private int _x,_y;

    public int get_x() { return _x;}
    public int get_y() { return _y;}

    /**
     * Set x coordinate
     * @param x x coordinate
     */
    public void setX(int x)
    {
        this._x = x;
    }

    /**
     * Set y coordinate
     * @param y y coordinate
     */
    public void setY(int y)
    {
        this._y = y;
    }

    /**
     * Set x and y coordinates
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPosition(int x, int y)
    {
        setX(x);
        setY(y);
    }


    /**
     * Alter the position
     */
    public Position(){
        this._x = 0;
        this._y = 0;
    }

    /**
     * Alter the position
     * @param x x coordinate
     * @param y y coordinate
     * @throws OutOfMapException
     */
    public Position(int x, int  y) throws OutOfMapException
    {
        if(x>150 || y>150){
            throw new OutOfMapException("Outside world");
        }
        this._x = x;
        this._y = y;
    }

    /**
     * Set the position
     * @param x x coordinate
     * @param y y coordinate
     * @return the new position
     * @throws OutOfMapException
     */
    public static Position set(int x, int y) throws OutOfMapException
    {
        return new Position(x,y);
    }


}
