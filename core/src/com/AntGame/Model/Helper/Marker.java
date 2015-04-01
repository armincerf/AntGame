package com.AntGame.Model.Helper;

/**
 * Created by Bradley on 16/03/2015.
 */
public class Marker {
    private Colour _markerColour;
    private int markerNum;

    public Marker(Colour colour, int markerNum)
    {
        this._markerColour = colour;
        this.markerNum = markerNum;
    }

    public Marker(int markerNum)
    {
        this.markerNum = markerNum;
    }


    public int getMarkerNum()   {   return markerNum;   }
    public Colour getMarkerColour() {   return _markerColour;   }

}
