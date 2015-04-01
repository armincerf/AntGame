package com.AntGame.Model.Helper;

/**
 * Created by Bradley on 16/03/2015.
 */
public class IDGenerator {

    private static int idCount = 0;

    public static int AssignID(){
        return ++idCount;
    }


}
