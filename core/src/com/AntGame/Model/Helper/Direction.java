package com.AntGame.Model.Helper;

public enum Direction {
    Right(0),
    DownRight(1),
    DownLeft(2),
    Left(3),
    UpLeft(4),
    UpRight(5);

    private int _intDirection;

    Direction(int dir)
    {
        this._intDirection = dir;
    }

    public int getEnumeratedDirection()
    {
        return _intDirection;
    }

    public int turn(Left_or_Right lr)
    {
        return lr == Left_or_Right.left ? (_intDirection + 5) % 6 : (_intDirection + 1) % 6;
    }

    public static Direction fromInt(int x)
    {
        switch(x){
            case 0:
                return Direction.Right;
            case 1:
                return Direction.DownRight;
            case 2:
                return Direction.DownLeft;
            case 3:
                return Direction.Left;
            case 4:
                return Direction.UpLeft;
            case 5:
                return Direction.UpRight;
            default:
                return null;
        }
    }

}