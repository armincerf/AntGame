package com.AntGame.Model.Helper;

import com.AntGame.Model.Tile;

/**
 * Created by Bradley on 16/03/2015.
 */
public class MapRow {
    private Tile[] _rowTiles;
    private int _rowNum;

    public MapRow(int rowNum, int width)
    {
        this._rowTiles = new Tile[width];
        this._rowNum = rowNum;
    }

    public int getRowNum()  { return _rowNum;    }
    public Tile[] GetRow() {   return _rowTiles;   }
    public Tile getTile(float tile){  return _rowTiles[(int)tile]; }
    public void setTile(int index, Tile tile) {     this._rowTiles[index] = tile;   }

}
