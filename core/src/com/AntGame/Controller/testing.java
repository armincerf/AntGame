package com.AntGame.Controller;

import com.AntGame.Model.TileType;

import java.io.IOException;

/**
 * Created by alexdavis on 27/03/15.
 */
public class testing {
    public static void main(String[] args) throws IOException {
            testMap();
    }

    public static void testMap() throws IOException {
        MapController mc = new MapController();
        mc.createMapFromFile("/Users/alexdavis/IdeaProjects/Ant-Game/2.world");

        for (int i = 0; i < mc.getMap().getHeight(); i++) {
            for (int j = 0; j < mc.getMap().getHeight(); j++) {
                //for testing

                if (mc.getMap().getRow(j).getTile(i).hasAnt()) {
                    System.out.print("A");
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
    public static void TestBrain()
    {
        try { System.out.println(
                AntBrainReader.readBrainFile("/Users/alexdavis/Downloads/test/core/assets/brain.txt")
        );
        }catch(IOException ex){
            System.out.println(ex.getMessage());

        }
    }
}
