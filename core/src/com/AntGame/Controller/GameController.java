package com.AntGame.Controller;

import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
import com.AntGame.Model.Helper.SenseDirection;
import com.AntGame.Model.TileType;
import javafx.geometry.Pos;

import java.io.IOException;
import java.util.List;

/**
 * Created by Bradley on 17/03/2015.
 */
public class GameController {

    private MapController mapController;
    private AntController antController;
    private List<Instruction> redAntInstructions, blackAntInstructions;


    public GameController() throws IOException {
        Initialize();
    }

    public void Initialize() throws IOException {
        mapController = new MapController();
        antController = new AntController();

    }

    public void setAntInstructions(String brain1, String brain2) {
        try {
            redAntInstructions = AntBrainReader.readBrainFile(brain1);
            blackAntInstructions = AntBrainReader.readBrainFile(brain2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Instruction get_instruction(Colour colour, int state)
    {

        //TODO: ADD MULTIPLE INSTRUCTION LIST SUPPORT FOR DIFFERENT COLOURS
        if (colour.equals(Colour.Black)) {

            return blackAntInstructions.get(state);
        } else {
            return redAntInstructions.get(state);

        }
    }

    public MapController getMapController() {
        return mapController;
    }

    public AntController getAntController() {
        return antController;
    }


    public boolean CellMatches(Position position, Condition condition, Colour colour)
    {

        if (mapController.getMap().getRow(position.get_y()).getTile(position.get_x()).getTileType() == TileType.Rocky) {
            if(condition == Condition.rock){
                return true;
            }
            return false;
        } else {

            switch (condition){
                case friend:
                    return mapController.isAntAt(position) && mapController.getAntAt(position).getAntColour() == colour;
                case foe:
                    return mapController.isAntAt(position) && mapController.getAntAt(position).getAntColour() != colour;
                case friendwithfood:
                    return mapController.isAntAt(position) && mapController.getAntAt(position).getAntColour() == colour
                            && mapController.getAntAt(position).hasFood();
                case foewithfood:
                    return mapController.isAntAt(position) && mapController.getAntAt(position).getAntColour() != colour
                            && mapController.getAntAt(position).hasFood();
                case food:
                    return mapController.getMap().getRow(position.get_y()).getTile(position.get_x()).getFood() > 0;
                case rock:
                    return false;
                case marker:
                    return mapController.checkMarkerAt(position, colour, condition.markerNum);
                case foemarker:
                    return mapController.checkIfAnyMarkerAt(position, antController.otherColour(colour));
                case home:
                    return mapController.antHillAt(colour, position);
                case foehome:
                    return mapController.antHillAt(antController.otherColour(colour), position);


            }




        }
        System.out.println("shouldnt happen");
        return false;

    }

    public void step(int id) throws OutOfMapException {
        if(antController.antIsAlive(id)) {
            Position p = antController.findAnt(id);
            Ant a = antController.getAnt(id);
            if(a.getResting() > 0)
                a.decrementRest();
            else{
                Instruction instr = get_instruction(a.getAntColour(),a.getBrainState());
                System.out.println(instr);
                switch (instr.instrType) {
                    case Sense:
                        Position p2 = SenseDirection.sensed_cell(p, a.getAntDirection(), instr.senseDirection);
                        int s1 = CellMatches(p2, instr.condition, a.getAntColour()) ? instr.state1 : instr.state2;
                        a.setBrainState(s1);
                        break;
                    case Mark:
                        mapController.setMarkerAt(p, a.getAntColour(), instr.marker.getMarkerNum());
                        a.setBrainState(instr.state1);
                        break;
                    case Unmark:
                        mapController.clearMarkerAt(p, a.getAntColour(), instr.marker.getMarkerNum());
                        a.setBrainState(instr.state1);
                        break;
                    case Pickup:
                        if(a.hasFood() || mapController.foodAt(p) == 0)
                            a.setBrainState(instr.state2);
                        else
                        {
                            mapController.setFoodAt(p, mapController.foodAt(p) - 1);
                            a.setHasFood(true);
                            a.setBrainState(instr.state1);
                        }
                        break;
                    case Drop:
                        if(a.hasFood()){
                            mapController.setFoodAt(p, mapController.foodAt(p) + 1);
                            a.setHasFood(false);
                        }
                        a.setBrainState(instr.state1);
                        break;
                    case Turn:
                        a.setAntDirection(Direction.fromInt(a.getAntDirection().turn(instr.lr)));
                        a.setBrainState(instr.state1);
                        break;
                    case Move:
                        Position newPos = SenseDirection.adjacent_cell(p, a.getAntDirection());

                        if(mapController.getMap().getRow(newPos.get_y()).getTile(newPos.get_x()).getTileType() == TileType.Rocky ||
                                mapController.isAntAt(newPos)){
                            a.setBrainState(instr.state2);
                        } else {
                            mapController.clearAntAt(p);
                            System.out.println(mapController.isAntAt(p));
                            mapController.setAntAt(newPos,a);
                            a.setBrainState(instr.state1);
                            a.setRestPeriod(14);
                            //TODO: CHECK IF SURROUNDED
                        }
                        break;
                    case Flip:
                        int state = (int)(Math.random()*instr.n) == 0 ? instr.state1 : instr.state2;
                        a.setBrainState(state);
                        break;
                }
            }

        }
    }



}