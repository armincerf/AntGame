package com.AntGame.Model.Helper;

/**
 * Created by Bradley on 16/03/2015.
 */
public class Marker {
    private Colour _markerColour;
    private int markerNum;

    /**
     * Marker Constructor
     * @param colour colour of the ant
     * @param markerNum numerical value corresponding to marker
     */
    public Marker(Colour colour, int markerNum)
    {
        this._markerColour = colour;
        this.markerNum = markerNum;
    }

    /**
     * Alternate Marker Constructor (Overloading)
     * @param markerNum numerical value corresponding to marker
     */
    public Marker(int markerNum)
    {
        this.markerNum = markerNum;
    }


    /**
     * Get the Marker number
     * @return the marker number
     */
    public int getMarkerNum()   {   return markerNum;   }

    /**
     * Get the colour of the marker
     * @return the colour of the marker
     */
    public Colour getMarkerColour() {   return _markerColour;   }

}
