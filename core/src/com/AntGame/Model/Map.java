package com.AntGame.Model;

import com.AntGame.Model.Helper.MapRow;
import com.AntGame.Model.Helper.Position;

/**
 * Created by Bradley on 16/03/2015.
 */
public class Map {

    private MapRow[] _rows;


    private int height;
    public Map(int width, int height){
        this._rows = new MapRow[height];
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
    public MapRow getRow(float Row){  return _rows[(int)Row];  }
    public void setRow(float index, MapRow mapRow) {  this._rows[(int)index] = mapRow; }

    public MapRow getPosition(Position pos) {
        return _rows[pos.get_y()];
    }



}
