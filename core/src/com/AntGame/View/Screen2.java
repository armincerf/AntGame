package com.AntGame.View;

import com.AntGame.Controller.MapController;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.TileType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.IOException;
import java.util.Random;

/**
 * Created by alexdavis on 01/04/15.
 */
public class Screen2 implements Screen {
    PolygonSprite polyRedHill;
    PolygonSpriteBatch polyRedHillBatch = new PolygonSpriteBatch();
    PolygonSprite polyBlackHill;
    PolygonSpriteBatch polyBlackHillBatch = new PolygonSpriteBatch();
    PolygonSprite polyFood;
    PolygonSpriteBatch polyFoodBatch = new PolygonSpriteBatch();
    PolygonSprite polyRock;
    PolygonSpriteBatch polyRockBatch = new PolygonSpriteBatch();
    PolygonSprite polyClear;
    PolygonSpriteBatch polyClearBatch = new PolygonSpriteBatch();
    // To assign at the beginning


    Texture textureSolid;
    private Table table = new Table();
    float x, y,  r, h, offset, multipleX, multipleY;
    float side = 0;
    Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
    Random rand = new Random();
    MapController mc = new MapController();




    private TextButton button;
    private Stage stage = new Stage();
    @Override
    public void render(float deltY){
        Gdx.gl.glClearColor(0, 0, 0, 100); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
         //draw all actors on the Stage.getBatch()

        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                polyClear.setSize(1,1);
                if(i%2==1){
                    offset = multipleX /2;
                }
                else{
                    offset = 0;
                }

                if (mc.getMap().getRow(i).getTile(j).getTileType().equals(TileType.Rocky)) {
                    polyRock.setX((mc.getMap().getRow(i).getTile(j).getTilePosition().get_x() * multipleX + offset));
                    polyRock.setY(mc.getMap().getRow(i).getTile(j).getTilePosition().get_y()* multipleY);
                }
                if (mc.getMap().getRow(i).getTile(j).getTileType().equals(TileType.antHill)) {
                    if(mc.getMap().getRow(i).getTile(j).get_antHill().equals(Colour.Black)){
                        polyBlackHill.setX((mc.getMap().getRow(i).getTile(j).getTilePosition().get_x() * multipleX + offset));
                        polyBlackHill.setY(mc.getMap().getRow(i).getTile(j).getTilePosition().get_y()* multipleY);
                    }
                    else{
                        polyRedHill.setX((mc.getMap().getRow(i).getTile(j).getTilePosition().get_x() * multipleX + offset));
                        polyRedHill.setY(mc.getMap().getRow(i).getTile(j).getTilePosition().get_y()* multipleY);
                    }
                }
                if (mc.getMap().getRow(i).getTile(j).getTileType().equals(TileType.Food)) {
                    polyFood.setX((mc.getMap().getRow(i).getTile(j).getTilePosition().get_x() * multipleX + offset));
                    polyFood.setY(mc.getMap().getRow(i).getTile(j).getTilePosition().get_y()* multipleY);
                }
                if (mc.getMap().getRow(i).getTile(j).getTileType().equals(TileType.Clear)) {
                    polyClear.setX((mc.getMap().getRow(i).getTile(j).getTilePosition().get_x() * multipleX + offset));
                    polyClear.setY(mc.getMap().getRow(i).getTile(j).getTilePosition().get_y()* multipleY);
                }




                polyRockBatch.begin();

                polyRock.draw(polyRockBatch);

                polyRockBatch.end();

                polyBlackHillBatch.begin();

                polyBlackHill.draw(polyBlackHillBatch);

                polyBlackHillBatch.end();

                polyRedHillBatch.begin();

                polyRedHill.draw(polyRedHillBatch);

                polyRedHillBatch.end();

                polyClearBatch.begin();

                polyClear.draw(polyClearBatch);

                polyClearBatch.end();

                polyFoodBatch.begin();

                polyFood.draw(polyFoodBatch);

                polyFoodBatch.end();




            }
        }


        stage.draw();









    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        //anthill

        polyRedHill = new PolygonSprite(getPoly(Color.RED, 0, 0));
        polyRedHillBatch = new PolygonSpriteBatch();

        polyBlackHill = new PolygonSprite(getPoly(Color.BLACK, 0, 0));
        polyBlackHillBatch = new PolygonSpriteBatch();

        polyFood = new PolygonSprite(getPoly(Color.GREEN, 0, 0));
        polyFoodBatch = new PolygonSpriteBatch();

        polyRock = new PolygonSprite(getPoly(Color.BLACK, 0, 0));
        polyRockBatch = new PolygonSpriteBatch();

        polyClear = new PolygonSprite(getPoly(Color.PINK, 0, 0));
        polyClearBatch = new PolygonSpriteBatch();
        multipleX = (float)Math.sqrt(3)*side;
        multipleY = side+(side/2);
        System.out.println(multipleX);

        try {
            mc.createMapFromFile("/Users/alexdavis/Downloads/test/core/assets/1.world");
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainMenu.createBasicSkin();
        button = new TextButton("hi", MainMenu.skin);
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    ((Game) Gdx.app.getApplicationListener()).setScreen(new Splash());
                                    }
            });


            //The elements are displayed in the order you add them.
            //The first appear on top, the last at the bottom.
            table.add(button).size(150,60).padBottom(20).row();

            table.setFillParent(true);
            stage.addActor(table);

            Gdx.input.setInputProcessor(stage);
        }
    public static float CalculateH(float side)
    {
        return (float)(Math.sin(DegreesToRadians(30)) * side);
    }

    public static float CalculateR(float side)
    {
        return (float)(Math.cos(DegreesToRadians(30)) * side);
    }
    public static double DegreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180;
    }
    public void makePoints(){
        side =  5;
        h = CalculateH(side);
        r = CalculateR(side);
    }
    public PolygonRegion getPoly(Color color, float x, float y){

        this.x = x;
        this.y = y;

        pix.setColor(color);
        pix.fill();
        makePoints();
        textureSolid = new Texture(pix);
        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
                new float[] {      // vertices
                        x, y,
                        x+r, y+h,
                        x+r, y+side+h,
                        x,y+side+h+h,
                        x-r, y+side+h,
                        x-r, y+h


                }, new short[] { //4 triangles using vertices to make hexagon
                0, 1, 5,
                1, 4, 2,
                5, 1, 4,
                2, 3, 4



        });
       return polyReg;
    }
    @Override
    public void hide() {
        dispose();
    }
    @Override
    public void dispose() {

        stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }



}
