package com.AntGame.Model.Helper;

/**
 * Created by Bradley on 16/03/2015.
 */

public class IDGenerator {


    private static int idCount = 0;

    /**
     * Creates a new ID number.
     * @return the next avaiable ID number
     */
    public static int AssignID(){
        return ++idCount;
    }


}
