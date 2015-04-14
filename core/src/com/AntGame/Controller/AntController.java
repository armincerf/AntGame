package com.AntGame.Controller;

import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Position;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Bradley on 16/03/2015.
 */
public class AntController {
    private Map<Integer, Ant> _ants;

    public AntController()
    {
        _ants = new HashMap<>();
    }

    public Ant getAnt(int id)
    {
        return _ants.get(id);
    }

    public boolean antIsAlive(int id)
    {
        return _ants.containsKey(id);
    }

    public Position findAnt(int id)
    {
        return antIsAlive(id) ? getAnt(id).getAntPositon(): null;
    }

    public Colour otherColour(Colour colour)
    {
        return colour == Colour.Red ? Colour.Black : Colour.Red;
    }

    public Map getMap() {
        return _ants;
    }

    public void addAnt(Ant a) {
        _ants.put(a.getID(), a);
    }




}
