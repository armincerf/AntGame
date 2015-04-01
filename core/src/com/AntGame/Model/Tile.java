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

    public Tile(TileType tileType, Position position)
    {
        this._tilePos = position;
        this._tileType = tileType;
        this._food = 0;
        Marker _markerOnTile = new Marker(null, 0);
        this._antHill = null;
    }


    public Position getTilePosition() {    return _tilePos;    }
    public TileType getTileType(){   return _tileType ; }

    public void putFood(int food) {
        this._food = food;
        this.set_tileType(TileType.Food);
    }
    public int getFood(){   return _food;   }

    public boolean isAntHill() {
        return _antHill == null ? false : true;
    }

    public Colour get_antHill() {
        return _antHill;
    }
    public void set_tileType(TileType type){ this._tileType = type;}
    public void set_tilePos(Position position){this._tilePos = position;}

    public void set_antHill(Colour colour) {
        this._antHill = colour;
        this.set_tileType(TileType.antHill);
    } //TODO
    public void decrementFood() { this._food--;  }
    public boolean hasAnt() {   return _antOnTile != null;  }
    public void clearAnt(){    this._antOnTile = null;   }
    public Ant getAntOnTile(){  return _antOnTile;  }
    public void putAntOnTile(Ant ant){     this._antOnTile = ant;}
    public Marker getMarkerOnTile() {    return _markerOnTile;    }
    public void putMarkerOnTile(Marker marker) { this._markerOnTile = marker;   }
    public void removeMarker(Colour colour, int marker)
    {
        if(_markerOnTile.getMarkerColour() == colour && _markerOnTile.getMarkerNum() == marker) {
            this._markerOnTile = null;
        }
    }


}
