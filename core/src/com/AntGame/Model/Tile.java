package com.AntGame.Model;

import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Marker;
import com.AntGame.Model.Helper.Position;

public class Tile {
    private Position _tilePos;
    private int _food;
    private TileType _tileType;
    private Ant _antOnTile;
    private Marker _markerOnTile;
    private Colour _antHill;

    /**
     * Constructor for Tile
     * @param tileType type of tile
     * @param position the position of the tile
     */
    public Tile(TileType tileType, Position position)
    {
        this._tilePos = position;
        this._tileType = tileType;
        this._food = 0;
        Marker _markerOnTile = new Marker(null, 0);
        this._antHill = null;
    }

    /**
     * Get the tile position
     * @return tile position
     */
    public Position getTilePosition() {    return _tilePos;    }

    /**
     * Get the tile type
     * @return tile type
     */
    public TileType getTileType(){   return _tileType ; }

    /**
     * Place the food
     * @param food number id
     */
    public void putFood(int food) {
        this._food = food;

    }

    /**
     * Get food number
     * @return the food number
     */
    public int getFood(){   return _food;   }

    /**
     * Boolean
     * @return true if anthill is present, false otherwise
     */
    public boolean isAntHill() {
        return _antHill != null;
    }

    /**
     * Get the anthill
     * @return the ant hill
     */
    public Colour get_antHill() {
        return _antHill;
    }

    /**
     *Set the tile type
     * @param type the type of tile
     */
    public void set_tileType(TileType type){ this._tileType = type;}

    /**
     *Set the tile position
     * @param position tile position
     */
    public void set_tilePos(Position position){this._tilePos = position;}

    /**
     * Set ant hill
     * @param colour of the anthill (red or black)
     */
    public void set_antHill(Colour colour) {
        this._antHill = colour;
        this.set_tileType(TileType.antHill);
    } //TODO

    /**
     * Decrement the food
     */
    public void decrementFood() { this._food--;  }

    /**
     * Does the tile have an ant that is alive
     * @return boolean, if the ant on the tile is alive return true, false otherwise.
     */
    public boolean hasAliveAnt() {   return _antOnTile != null;  }

    /**
     * Remove the ant from the tile
     */
    public void clearAnt(){
        this._antOnTile = null;
    }

    /**
     * Is the ant on the tile?
     * @return boolean, if the ant is on the tile return true, false otherwise.
     */
    public Ant getAntOnTile(){  return _antOnTile;  }

    /**
     * Place the ant on a tile
     * @param ant ant object
     */
    public void putAntOnTile(Ant ant){     this._antOnTile = ant;}

    /**
     *Get the marker
     * @return The marker number
     */
    public Marker getMarkerOnTile() {    return _markerOnTile;    }

    /**
     * Place a marker number
     * @param marker the marker number
     */
    public void putMarkerOnTile(Marker marker) { this._markerOnTile = marker;   }

    /**
     *Remove the marker number
     * @param colour ant colour
     * @param marker marker number
     */
    public void removeMarker(Colour colour, int marker)
    {
        if(_markerOnTile.getMarkerColour() == colour && _markerOnTile.getMarkerNum() == marker) {
            this._markerOnTile = null;
        }
    }


}
