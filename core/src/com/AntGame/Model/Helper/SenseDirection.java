package com.AntGame.Model.Helper;

import com.AntGame.Controller.OutOfMapException;

/**
 * Created by Bradley on 31/03/2015.
 */


public enum SenseDirection {
    here, ahead, leftahead, rightahead;

    /**
     * Get the position of the adjacent cells in a given direction
     * @param p position
     * @param d direction
     * @return position of the adjacent cells in the given direction
     * @throws OutOfMapException
     */
    public static Position adjacent_cell(Position p, Direction d) throws OutOfMapException {
        switch (d.getEnumeratedDirection()) {
            case 0:
                return new Position(p.get_x() + 1, p.get_y());
            case 1:
                return  p.get_y() % 2 == 1 ? new Position(p.get_x(), p.get_y() + 1) : new Position(p.get_x() + 1, p.get_y() + 1);
            case 2:
                return  p.get_y() % 2 == 1 ? new Position(p.get_x() - 1, p.get_y() + 1) : new Position(p.get_x(), p.get_y() + 1);
            case 3:
                return new Position(p.get_x() -1, p.get_y());
            case 4:
                return  p.get_y() % 2 == 1 ? new Position(p.get_x() - 1, p.get_y() - 1) : new Position(p.get_x(), p.get_y() - 1);
            case 5:
                return  p.get_y() % 2 == 1 ? new Position(p.get_x(), p.get_y() - 1) : new Position(p.get_x() + 1, p.get_y() - 1);
            default:
                return p;
        }
    }

    /**
     * The the position of a sensed cell in a given direction
     * @param p position
     * @param d direction
     * @param sd sense direction
     * @return the position of the sensed cells
     * @throws OutOfMapException
     */
    public static Position sensed_cell(Position p, Direction d, SenseDirection sd) throws OutOfMapException {
        switch(sd) {
            case here:
                return p;
            case ahead:
                return adjacent_cell(p, d);
            case leftahead:
                return adjacent_cell(p, Direction.fromInt(d.turn(Left_or_Right.left)));
            case rightahead:
                return adjacent_cell(p, Direction.fromInt(d.turn(Left_or_Right.right)));
        }
        return null;
    }




}