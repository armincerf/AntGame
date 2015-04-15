package com.AntGame.Controller;

import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
import com.AntGame.Model.Helper.SenseDirection;
import com.AntGame.Model.TileType;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by Bradley on 17/03/2015.
 */
public class GameController {

    private MapController mapController;
    private AntController antController;
    private List<Instruction> redAntInstructions, blackAntInstructions;
    private int moves;
    private Random rand = new Random();
    private int blackScore, redScore, count;


    public GameController() throws IOException {
        Initialize();
    }

    /**
     * sets up map controller and ant controller and resets scores and moves
     *
     * @throws IOException
     */
    public void Initialize() throws IOException {
        mapController = new MapController();
        antController = new AntController();
        moves = 0;
        blackScore = 0;
        redScore = 0;
    }

    /**
     * Sets the instructions for each brain
     * @param brain1 a string containing the file path of the 1st brain
     * @param brain2 a string containing the file path of the 2nd brain
     */
    public void setAntInstructions(String brain1, String brain2) {
        try {
            redAntInstructions = AntBrainReader.readBrainFile(brain1);
            blackAntInstructions = AntBrainReader.readBrainFile(brain2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets list of instructions by ant colour
     * @param colour an Colour Enum Value
     */
    public List<Instruction> getAntInstructions(Colour colour)
    {
        return colour == Colour.Red ? redAntInstructions : blackAntInstructions;
    }


    /**
     * Gets the instruction at a state for a colours brain
     * @param colour color of ant
     * @param state line number in brain
     * @return instruction
     */
    public Instruction get_instruction(Colour colour, int state)
    {

        if (colour.equals(Colour.Black)) {

            return blackAntInstructions.get(state);
        } else {
            return redAntInstructions.get(state);

        }
    }

    /**
     *
     * @return the map controller
     */
    public MapController getMapController() {
        return mapController;
    }

    public AntController getAntController() {
        return antController;
    }

    /**
     *
     * @param position the position of the ant
     * @param condition the condition to check
     * @param colour color of the ant
     * @return true if the cell matches the condition
     */
    public boolean CellMatches(Position position, Condition condition, Colour colour)
    {

        if (mapController.getMap().getRow(position.get_y()).getTile(position.get_x()).getTileType() == TileType.Rocky) {
            return condition == Condition.rock;
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

    /**
     * Returns the score of a specified team
     * @param colour the colour of the team
     * @return the score of that specific team
     */
    public int getScore(Colour colour) {
        if (colour.equals(Colour.Black)) {
            return blackScore;
        }
        return redScore;
    }

    public int getMoves() {
        return moves;
    }

    public int randomInt(int n) {
        /*
        int seed = 12345;

        seed = (seed * 22695477) + 1;
        count++;
        int value = (int) ((Math.floor((double) seed / 65536)) % 16384);
        if (value < 0) {
            value = ((value + 16384) % 16384);
        }
        int output = value % n;
        return output;
        */
        return rand.nextInt(n);
    }

    public void killAntAt(Position p) {
        mapController.getMap().getRow(p.get_y()).getTile(p.get_x()).clearAnt();
    }

    public void checkForSurroundedAntAt(Position p) throws OutOfMapException {
        if (mapController.isAntAt(p)) {
            Ant a = mapController.getAntAt(p);
            if (adjacentAnts(p, antController.otherColour(a.getAntColour())) > 5) {
                killAntAt(p);
                mapController.setFoodAt(p, mapController.foodAt(p) + 3 + (a.hasFood() ? 1 : 0));
            }
        }
    }

    public void checkForSurroundedAnts(Position p) throws OutOfMapException {
        checkForSurroundedAntAt(p);
        for (int i = 0; i < 5; i++) {
            checkForSurroundedAntAt(SenseDirection.adjacent_cell(p, Direction.fromInt(i)));
        }
    }

    public int adjacentAnts(Position p, Colour ac) throws OutOfMapException {
        int no = 0;
        for (int i = 0; i < 5; i++) {
            Position pos = adjacentCell(p, i);
            if (mapController.isAntAt(pos) && mapController.getAntAt(p).getAntColour() == ac) {
                no++;
            }
        }
        return no;
    }

    public Position adjacentCell(Position p, int d) throws OutOfMapException {

        Position position = new Position(0, 0); // init

        switch (d) {
            case 0:
                position = new Position(p.get_x() + 1, p.get_y();
                break;

            case 1:
                if (p.get_y() % 2 == 0) {
                    position = new Position(p.get_x(), p.get_y() + 1);
                } else {
                    position = new Position(p.get_x() + 1, p.get_y() + 1);
                }
                break;
            case 2:
                if (p.get_y() % 2 == 0) {
                    position = new Position(p.get_x() - 1, p.get_y() + 1);
                } else {
                    position = new Position(p.get_x(), p.get_y() + 1);
                }
                break;

            case 3:
                position = new Position(p.get_x() - 1, p.get_y());
                break;

            case 4:
                if (p.get_y() % 2 == 0) {
                    position = new Position(p.get_x() - 1, p.get_y() - 1);
                } else {
                    position = new Position(p.get_x(), p.get_y() - 1);
                }
                break;

            case 5:
                if (p.get_y() % 2 == 0) {
                    position = new Position(p.get_x(), p.get_y() - 1);
                } else {
                    position = new Position(p.get_x() + 1, p.get_y() - 1);
                }
                break;
        }
        return position;
    }
    /**
     * This determines the action a specific ant is to take on the next turn
     * @param id the id of the ant
     * @throws OutOfMapException thrown if an ant exceeds the maps bounds
     */
    public void step(int id) throws OutOfMapException {

        moves++;

        if (antController.antIsAlive(id)) {
            Position p = antController.findAnt(id);
            Ant a = antController.getAnt(id);
            if (a.getResting() > 0) {
                a.decrementRest();
            } else {
                Instruction instr = get_instruction(a.getAntColour(), a.getBrainState());
                switch (instr.instrType) {
                    case Sense:
                        //System.out.println("sense");
                        Position p2 = SenseDirection.sensed_cell(p, a.getAntDirection(), instr.senseDirection);
                        int s1 = CellMatches(p2, instr.condition, a.getAntColour()) ? instr.state1 : instr.state2;
                        a.setBrainState(s1);
                        break;
                    case Mark:
                        mapController.setMarkerAt(p, a.getAntColour(), instr.marker.getMarkerNum());
                            a.setBrainState(instr.state1);
                        break;
                    case Unmark:
                        //System.out.println("unmark");
                        mapController.clearMarkerAt(p, a.getAntColour(), instr.marker.getMarkerNum());
                        a.setBrainState(instr.state1);
                        break;
                    case Pickup:
                        // System.out.println("pickup");
                        if (a.hasFood() || mapController.foodAt(p) == 0)
                            a.setBrainState(instr.state2);
                        else {
                            if (mapController.antHillAt(Colour.Black, p)) {
                                blackScore--;
                            }
                            if (mapController.antHillAt(Colour.Red, p)) {
                                redScore--;
                            }
                            mapController.setFoodAt(p, mapController.foodAt(p) - 1);
                            a.setHasFood(true);
                            a.setBrainState(instr.state1);
                        }
                        break;
                    case Drop:
                        // System.out.println("drop");
                        if (a.hasFood()) {
                            if (mapController.antHillAt(Colour.Black, p)) {
                                blackScore++;
                                System.out.println(blackScore);
                            }
                            if (mapController.antHillAt(Colour.Red, p)) {
                                redScore++;
                            }
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
                        //System.out.println("move");
                        Position newPos = SenseDirection.adjacent_cell(p, a.getAntDirection());

                        if (mapController.getMap().getRow(newPos.get_y()).getTile(newPos.get_x()).getTileType() == TileType.Rocky) {
                            a.setBrainState(instr.state2);
                        } else {
                            mapController.clearAntAt(p);
                            mapController.setAntAt(newPos, a);
                            a.setBrainState(instr.state1);
                            a.setRestPeriod(14);
                            //TODO: CHECK IF SURROUNDED
                        }
                        break;
                    case Flip:
                        //System.out.println("flip");
                        int state = randomInt(instr.n) == 0 ? instr.state1 : instr.state2;
                        a.setBrainState(state);
                        break;
                }
                }

        }

    }



}