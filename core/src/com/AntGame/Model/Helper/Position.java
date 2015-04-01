package com.AntGame.Model.Helper;

import com.AntGame.Controller.OutOfMapException;

/**
 * Created by Bradley on 16/03/2015.
 */
public class Position {
    private int _x,_y;

    public int get_x() { return _x;}
    public int get_y() { return _y;}

    public void setX(int x)
    {
        this._x = x;
    }

    public void setY(int y)
    {
        this._y = y;
    }

    public void setPosition(int x, int y)
    {
        setX(x);
        setY(y);
    }




    public Position(){
        this._x = 0;
        this._y = 0;
    }

    public Position(int x, int y) throws OutOfMapException
    {
        if(x>150 || y>150){
            throw new OutOfMapException("Outside world");
        }
        this._x = x;
        this._y = y;
    }


    public static Position set(int x, int y) throws OutOfMapException
    {
        return new Position(x,y);
    }


}
