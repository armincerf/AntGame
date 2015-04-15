package com.AntGame.Controller;

import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.*;
import com.AntGame.Model.Map;
import com.AntGame.Model.Tile;
import com.AntGame.Model.TileType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bradley on 16/03/2015.
 */
public class MapController {


    private Map _gameMap;

    public Map getMap() {   return _gameMap;    }


    private int width, height;
    public MapController()
    {

    }


    public void createMapFromFile(String filename) throws IOException {

        int amountOfRocky, amountOfRedAntHills, amountOfBlackAntHills, amountOfFood;
        amountOfBlackAntHills = 0;
        amountOfFood = 0;
        amountOfRedAntHills = 0;
        amountOfRocky = 0;


        try {
            System.out.println(filename);
            BufferedReader br = new BufferedReader(new FileReader(filename));

            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());
            _gameMap = new Map(width, height);
            for(int i = 0;i < height; i++){
                _gameMap.setRow(i, new MapRow(i, width));
                for(int j = 0; j < width; j++){
                    try {
                        _gameMap.getRow(i).setTile(j, new Tile(TileType.Clear, Position.set(j, i)));
                    }
                    catch (OutOfMapException e) {
                        e.printStackTrace();
                    }

                }
            }

            int blank = 0;
            int y = height-1;
            String line = "2";
            while (true) {



                int x = 0;

                line = br.readLine();

                if (line == null)
                    break;

                line = removeSpace(line);
                for (int i = 0; i < width; i++) {


                    char c = line.charAt(i);

                    switch (c) {

                        case '#':
                            _gameMap.getRow(y).getTile(x).set_tileType(TileType.Rocky);
                            amountOfRocky++;
                            blank++;
                                x++;
                            break;
                        case '.':
                            _gameMap.getRow(y).getTile(x).set_tileType(TileType.Clear);
                            blank++;
                            x++;
                            break;
                        case '+':
                            _gameMap.getRow(y).getTile(x).set_antHill(Colour.Red);
                            amountOfRedAntHills++;
                            blank++;
                            x++;
                            break;

                        case '-':
                            _gameMap.getRow(y).getTile(x).set_antHill(Colour.Black);
                            amountOfBlackAntHills++;
                            blank++;
                            x++;
                            break;

                        default:
                            if (Character.isDigit(c)) {
                                _gameMap.getRow(y).getTile(x).putFood(Character.getNumericValue(c));
                                amountOfFood += Character.getNumericValue(c);
                                x++;
                                blank++;
                            }
                            break;


                    }
                }
                y--;

            }

            if (amountOfRocky != 610 || amountOfRedAntHills != 127 || amountOfBlackAntHills != 127 || amountOfFood != 275) {
                System.out.println("errer"); //TODO if this is called the user should be asked to add a different world

            }




        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }

        

    }

    public String removeSpace(String s) {
        return s.replaceAll("\\s", "");
    }

    //TODO: ADD METHOD FOR CREATING RANDOM MAP
    public void createRandomMap() {
        _gameMap = new Map(150, 150);
        width = 150;
        height = 150;
        for(int i = 0;i < 150; i++){
            _gameMap.setRow(i, new MapRow(i, 150));
            for(int j = 0; j < 150; j++){
                try {
                    _gameMap.getRow(i).setTile(j, new Tile(TileType.Clear, Position.set(j, i)));
                }
                catch (OutOfMapException e) {
                    e.printStackTrace();
                }

            }
        }

            for(int y =0; y < 150; y++) {
                if (y % 2 == 0) {
                    _gameMap.getRow(0).getTile(y).set_tileType(TileType.Rocky);
                } else {
                    _gameMap.getRow(0).getTile(y).set_tileType(TileType.Blank);
                    _gameMap.getRow(1).getTile(y).set_tileType(TileType.Rocky);
                }
            }
        for (int x = 0; x < 150; x++) {
            _gameMap.getRow(x).getTile(0).set_tileType(TileType.Rocky);
            }
            for(int y = 0; y < 150; y++) {
                if (y % 2 == 0) {
                    _gameMap.getRow(149).getTile(y).set_tileType(TileType.Rocky);
                } else {
                    _gameMap.getRow(149).getTile(y).set_tileType(TileType.Blank);
                    _gameMap.getRow(148).getTile(y).set_tileType(TileType.Rocky);
                }
            }
        for (int x = 0; x < 150; x++) {
            _gameMap.getRow(x).getTile(149).set_tileType(TileType.Rocky);

            }

            boolean okRedHillSpace = false;
            Random random = new Random();
            int randR1 = 0;
            int randR2 = 0;
        while (!okRedHillSpace) {
                randR1 = random.nextInt(150);
                randR2 = random.nextInt(150);

                okRedHillSpace = checkHillSpace(randR1, randR2);
            }

            int xR = randR1;
            int yR = randR2;

        for (Position pos : createHill(xR, yR)) {
            _gameMap.getRow(pos.get_y()).getTile(pos.get_x()).set_antHill(Colour.Red);
            }


            boolean okBlackHillSpace = false;
            int randB1 = 0;
            int randB2 = 0;
        while (!okBlackHillSpace) {
                randB1 = random.nextInt(150);
                randB2 = random.nextInt(150);
                okBlackHillSpace = checkHillSpace(randB1, randB2);
            }

            int xB = randB1;
            int yB = randB2;
        for (Position pos : createHill(xB, yB)) {
            _gameMap.getRow(pos.get_y()).getTile(pos.get_x()).set_antHill(Colour.Black);
            }


        for (int j = 0; j < 11; j++) { //Creates 10 randomly places food shapes NOTE: only does 1 shape, example maps have 2
                boolean okFoodSpace = false;
                int randF1 = 0;
                int randF2 = 0;
            while (!okFoodSpace) {
                    randF1 = random.nextInt(150);
                    randF2 = random.nextInt(150);
                    okFoodSpace = checkHillSpace(randF1, randF2);
                }

                int xF = randF1;
                int yF = randF2;

            ArrayList<Position> foodPositions = new ArrayList<>(); //Creates rhombus shape
                try {
                    for (int i = -2; i < 2; i++) {
                        foodPositions.add(new Position(xF + i, yF));
                    }
                    for (int i = -2; i < 2; i++) {
                        foodPositions.add(new Position(xF + i, yF + 1));
                    }
                    for (int i = -2; i < 2; i++) {
                        foodPositions.add(new Position(xF + i, yF + 2));
                    }
                    for (int i = -2; i < 2; i++) {
                        foodPositions.add(new Position(xF + i, yF - 1));
                    }
                    for (int i = -2; i < 2; i++) {
                        foodPositions.add(new Position(xF + i, yF - 2));
                    }

                } catch (OutOfMapException e) {
                    e.printStackTrace();
                }
                for (Position pos1 : foodPositions) {
                    setFoodAt(pos1, random.nextInt(9) + 1);
                }
            }
        for (int j = 0; j < 10; j++) {


            for (int i = 0; i < 20; i++) { //Creates a random number of rocks
                boolean okRockSpace = false;
                int randRockX = 0;
                int randRockY = 0;
                while (!okRockSpace) {
                    randRockX = random.nextInt(149);
                    randRockY = random.nextInt(149);
                    int randRock = random.nextInt(6);
                    if (randRock == 0) {
                        randRockX += 1;
                        randRockY += 1;
                    }
                    if (randRock == 1) {
                        randRockX += 1;
                    }
                    if (randRock == 2) {
                        randRockY += 1;
                    }
                    if (randRock == 3) {
                        randRockX += -1;
                    }
                    if (randRock == 4) {
                        randRockX += -1;
                        randRockY += -1;
                    }
                    if (randRock == 5) {

                        randRockY += -1;
                    }

                    okRockSpace = checkHillSpace(randRockX, randRockY) && _gameMap.getRow(randRockY).getTile(randRockX).getTileType().equals(TileType.Clear);


                }
                int xRock = randRockX;
                int yRock = randRockY;
                _gameMap.getRow(yRock).getTile(xRock).set_tileType(TileType.Rocky);


            }
        }


        }

    public ArrayList<Position> createHill(int x, int y) {
        try {
            ArrayList<Position> temp = new ArrayList<>();
            Position pos1 = new Position(x, y);
            temp.add(pos1);
            for (int i = -2; i < 2; i++) {
                temp.add(new Position(x + i, y - 4));
            }
            for (int i = -3; i < 3; i++) {
                temp.add(new Position(x + i, y - 3));
            }
            for (int i = -3; i < 3; i++) {
                temp.add(new Position(x + i, y - 2));
            }
            for (int i = -4; i < 4; i++) {
                temp.add(new Position(x + i, y - 1));
            }
            for (int i = -4; i < 4; i++) {
                temp.add(new Position(x + i, y));
            }
            for (int i = -4; i < 4; i++) {
                temp.add(new Position(x + i, y + 1));
            }
            for (int i = -4; i < 4; i++) {
                temp.add(new Position(x + i, y + 2));
            }
            for (int i = -3; i < 3; i++) {
                temp.add(new Position(x + i, y + 3));
            }
            for (int i = -3; i < 3; i++) {
                temp.add(new Position(x + i, y + 4));
            }
            for (int i = -2; i < 2; i++) {
                temp.add(new Position(x + i, y + 5));
            }

            return temp;
        } catch (OutOfMapException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean checkHillSpace(int x, int y) {
        return !(x < 7 || x > 142 || y < 7 || y > 142);
    }

    //Map - Ant relations
    public boolean isAntAt(Position position) {
        return _gameMap.getRow(position.get_y()).getTile(position.get_x()).hasAliveAnt();
    }

    public Ant getAntAt(Position position) {
        return _gameMap.getRow(position.get_y()).getTile(position.get_x()).getAntOnTile();
    }

    public void setAntAt(Position position, Ant ant) {
        _gameMap.getRow(position.get_y()).getTile(position.get_x()).putAntOnTile(ant);
        ant.set_antPosition(position);
    }

    public void clearAntAt(Position position) {
        _gameMap.getRow(position.get_y()).getTile(position.get_x()).clearAnt();
    }


    //Map - food relations
    public int foodAt(Position position) {
        return _gameMap.getRow(position.get_y()).getTile(position.get_x()).getFood();
    }

    public void setFoodAt(Position position, int amount) {
        _gameMap.getRow(position.get_y()).getTile(position.get_x()).putFood(amount);
    }


    //Map - Anthill relations
    public boolean antHillAt(Colour colour, Position position)
    {
        return _gameMap.getRow(position.get_y()).getTile(position.get_x()).isAntHill() && _gameMap.getRow(position.get_y()).getTile(position.get_x()).get_antHill().equals(colour);
    }

    //Map - marker relations
    public void setMarkerAt(Position position, Colour colour, int marker)
    {
        _gameMap.getRow(position.get_y()).getTile(position.get_x()).putMarkerOnTile(new Marker(colour, marker));
    }
    public void clearMarkerAt(Position position, Colour colour, int marker)
    {
        if(_gameMap.getRow(position.get_y()).getTile(position.get_x()).getMarkerOnTile()!=null) {
            _gameMap.getRow(position.get_y()).getTile(position.get_x()).removeMarker(colour, marker);
        }
    }
    public boolean checkMarkerAt(Position position, Colour colour, int marker)
    {
        if(_gameMap.getRow(position.get_y()).getTile(position.get_x()).getMarkerOnTile() != null) {
            Marker m = _gameMap.getRow(position.get_y()).getTile(position.get_x()).getMarkerOnTile();
            return m.getMarkerColour() == colour && (m.getMarkerNum() == marker);

        }
        else{
            return false;
        }
    }
    public boolean checkIfAnyMarkerAt(Position position, Colour colour) {
        Marker m;
        if(_gameMap.getRow(position.get_y()).getTile(position.get_x()).getMarkerOnTile() != null) {
             m = _gameMap.getRow(position.get_y()).getTile(position.get_x()).getMarkerOnTile();
            return m.getMarkerColour() == colour;
        }
        return  false; //TODO fix this


    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }





}
