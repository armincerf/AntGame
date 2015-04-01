package com.AntGame.Model.Helper;

import com.AntGame.Controller.OutOfMapException;

/**
 * Created by Bradley on 31/03/2015.
 */


public enum SenseDirection {
    Here, Ahead, LeftAhead, RightAhead;

    public static Position adjacent_cell(Position p, Direction d) throws OutOfMapException {
        switch (d.getEnumeratedDirection()) {
            case 0:
                return new Position(p.get_x() + 1, p.get_y());
            case 1:
                return  p.get_y() % 2 == 0 ? new Position(p.get_x(), p.get_y() + 1) : new Position(p.get_x() + 1, p.get_y() + 1);
            case 2:
                return  p.get_y() % 2 == 0 ? new Position(p.get_x() - 1, p.get_y() + 1) : new Position(p.get_x(), p.get_y() + 1);
            case 3:
                return new Position(p.get_x() -1, p.get_y());
            case 4:
                return  p.get_y() % 2 == 0 ? new Position(p.get_x() - 1, p.get_y() - 1) : new Position(p.get_x(), p.get_y() - 1);
            case 5:
                return  p.get_y() % 2 == 0 ? new Position(p.get_x(), p.get_y() - 1) : new Position(p.get_x() + 1, p.get_y() - 1);
            default:
                return p;
        }
    }


    public static Position sensed_cell(Position p, Direction d, SenseDirection sd) throws OutOfMapException {
        switch(sd) {
            case Here:
                return p;
            case Ahead:
                return adjacent_cell(p, d);
            case LeftAhead:
                return adjacent_cell(p, Direction.fromInt(d.turn(Left_or_Right.Left)));
            case RightAhead:
                return adjacent_cell(p, Direction.fromInt(d.turn(Left_or_Right.Right)));
        }
        return null;
    }




}