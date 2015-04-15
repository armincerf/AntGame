package com.AntGame.Controller;

import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
import com.AntGame.Model.TileType;

import java.io.IOException;
import java.util.Map;

/**
 * Created by alexdavis on 27/03/15.
 */
public class testing {
    public static void main(String[] args) throws IOException {
        TestStep();
    }

    public static void testMap(MapController mc) throws IOException {


        for (int i = 0; i < mc.getMap().getHeight(); i++) {
            for (int j = 0; j < mc.getMap().getHeight(); j++) {
                //for testing

                if (mc.getMap().getRow(j).getTile(i).hasAliveAnt()) {
                    if (mc.getMap().getRow(j).getTile(i).getFood() != 0) {
                        System.out.print("AF");
                    } else {
                        System.out.print("A ");
                    }
                } else if (mc.getMap().getRow(j).getTile(i).getFood() != 0) {
                    System.out.print(mc.getMap().getRow(j).getTile(i).getFood() + " ");
                } else if (mc.getMap().getRow(j).getTile(i).isAntHill()) {
                    System.out.print("+ ");
                } else if (mc.getMap().getRow(j).getTile(i).getTileType().equals(TileType.Clear)) {
                    System.out.print(". ");
                } else if (mc.getMap().getRow(j).getTile(i).getTileType().equals(TileType.Rocky)) {
                    System.out.print("# ");
                }


            }
            System.out.print("\n");


        }
    }

    public static void TestReadBrain()
    {
        try {
            AntBrainReader.readBrainFile("C:\\Users\\Alexander\\Documents\\AntGame\\core\\assets\\brain1.brain");

        }catch(IOException ex){
            System.out.println(ex.getMessage());

        }
    }

    public static void TestStep() throws IOException {
        GameController gc = new GameController();
        gc.Initialize();
        gc.getMapController().createMapFromFile("C:\\Users\\Alexander\\Documents\\AntGame\\core\\assets\\1.world");
        gc.setAntInstructions("C:\\Users\\Alexander\\Documents\\AntGame\\core\\assets\\brain1.brain", "C:\\Users\\Alexander\\Documents\\AntGame\\core\\assets\\brain1.brain");

        try {

            Ant a = new Ant(new Position(100, 100), Colour.Black, Direction.Left);
            gc.getMapController().getMap().getRow(100).getTile(100).putAntOnTile(a);
            gc.getAntController().addAnt(a);

        } catch (OutOfMapException e) {
            e.printStackTrace();
        }

        Map map = gc.getAntController().getMap();
        for (int i = 0; i < 300000; i++) {


            for (Object a : map.values()) {
                Ant ant = (Ant) a;

                try {
                    gc.step(ant.getID());
                } catch (OutOfMapException e) {
                    e.printStackTrace();
                }


            }
        }
        testMap(gc.getMapController());
    }

}
