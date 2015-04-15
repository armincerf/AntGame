package com.AntGame.Model;

import com.AntGame.Model.Helper.MapRow;
import com.AntGame.Model.Helper.Position;

/**
 * Created by Bradley on 16/03/2015.
 */
public class Map {

    private MapRow[] _rows;


    private int width;
    private int height;

    /**
     *  Map Constructor
     *  initialises map row
     * @param width width of the map
     * @param height height of the map
     */
    public Map(int width, int height){
        this._rows = new MapRow[height];
        this.height = height;
        this.width = width;
    }

    /**
     * Get the height of the map
     * @return the hieght of the map.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the row
     * @param Row
     * @return  a row
     */
    public MapRow getRow(float Row){  return _rows[(int)Row];  }

    /**
     * Set the row
     * @param index row number
     * @param mapRow row object
     */
    public void setRow(float index, MapRow mapRow) {  this._rows[(int)index] = mapRow; }

    /**
     * Gets a specified row
     * @param pos position of object
     * @return a map row at a specified column
     */
    public MapRow getPosition(Position pos) {
        return _rows[pos.get_y()];
    }



}
