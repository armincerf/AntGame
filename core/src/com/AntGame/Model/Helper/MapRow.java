package com.AntGame.Model.Helper;

import com.AntGame.Model.Tile;

/**
 * Created by Bradley on 16/03/2015.
 */
public class MapRow {
    private Tile[] _rowTiles;
    private int _rowNum;

    /**
     * Constructor for MapRow
     * @param rowNum Row Number
     * @param width Width of row
     */
    public MapRow(int rowNum, int width)
    {
        this._rowTiles = new Tile[width];
        this._rowNum = rowNum;
    }

    /**
     * Get the Row Number
     * @return the row number
     */
    public int getRowNum()  { return _rowNum;    }

    /**
     * Get the row
     * @return the row, with the tiles.
     */
    public Tile[] GetRow() {   return _rowTiles;   }

    /**
     * Get a Tile from a Row
     * @param tile desired tile
     * @return the tile from the row
     */
    public Tile getTile(float tile){  return _rowTiles[(int)tile]; }

    /**
     * Set a tile
     * @param index index of the tile to set
     * @param tile the tile that is placed
     */
    public void setTile(int index, Tile tile) {     this._rowTiles[index] = tile;   }

}
