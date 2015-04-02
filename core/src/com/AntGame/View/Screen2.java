package com.AntGame.View;

import com.AntGame.Controller.MapController;
import com.AntGame.Controller.OutOfMapException;
import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
import com.AntGame.Model.TileType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.IOException;
import java.util.Random;

/**
 * Created by alexdavis on 01/04/15.
 */
public class Screen2 implements Screen {

    PolygonSprite polySprite;
    PolygonSpriteBatch polygonSpriteBatch = new PolygonSpriteBatch();

    // To assign at the beginning


    Texture textureSolid;
    private Table table = new Table();
    float x, y,  r, h, offset, multipleX, multipleY;
    float side = 0;
    Random rand = new Random();
    MapController mc = new MapController();
    int antX = 100, antY = 100;




    private TextButton button;
    private Stage stage = new Stage();
    private String worldFile;

    @Override
    public void render(float deltY){
        Gdx.gl.glClearColor(255, 255, 255, 100); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
         //draw all actors on the Stage.getBatch()
polygonSpriteBatch.begin();
        for (int j = 0; j < 150; j++) {
            for (int i = 0; i < 150; i++) {

                offset = i%2==0?multipleX/2:0;



                if (mc.getMap().getRow(i).getTile(j).getTileType().equals(TileType.Rocky)) {
                    drawCell(Color.BLACK, j, i);

                }
               
               
             

      }
        }
        polygonSpriteBatch.end();
        

        mc.getMap().getRow(antY).getTile(antX).clearAnt();
        try {
            mc.getMap().getRow(antY).getTile(antX).putAntOnTile(new Ant(new Position(antX, antY),Colour.Black, Direction.Left));
        } catch (OutOfMapException e) {
            e.printStackTrace();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)){
            mc.getMap().getRow(antY).getTile(antX).clearAnt();
                antX--;
            System.out.println(antX);

        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)){
            mc.getMap().getRow(antY).getTile(antX).clearAnt();
            antX++;

        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_1)){
            mc.getMap().getRow(antY).getTile(antX).clearAnt();
            if(antY%2==1) {
                antX--;
                antY--;
            }
            else{
                antY--;
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_3)){
            mc.getMap().getRow(antY).getTile(antX).clearAnt();
            antX++;
            antY--;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7)){
            mc.getMap().getRow(antY).getTile(antX).clearAnt();
            antX--;
            antY++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)){
            mc.getMap().getRow(antY).getTile(antX).clearAnt();
            antX++;
            antY++;
        }

        stage.draw();









    }

    private void drawCell(Color color, int x, int y) {
        polySprite = new PolygonSprite(makePoints(color));
        polySprite.setX(mc.getMap().getRow(y).getTile(x).getTilePosition().get_x() * multipleX + offset);
        polySprite.setY(mc.getMap().getRow(y).getTile(x).getTilePosition().get_y() * multipleY);
        polySprite.draw(polygonSpriteBatch);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        //anthill





        System.out.println(multipleX);

        try {
            mc.createMapFromFile(MainMenu.getWorld());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainMenu.createBasicSkin();
        button = new TextButton("Back", MainMenu.skin);
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
                                    }
            });


            //The elements are displayed in the order you add them.
            //The first appear on top, the last at the bottom.
            table.add(button).size(150,60).padBottom(20).padLeft(1000).row();

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
    public PolygonRegion makePoints(Color color){
        side =  5;
        h = CalculateH(side);
        r = CalculateR(side);
        multipleX = (float)Math.sqrt(3)*side;
        multipleY = side+(side/2);
        float[] points = {      // vertices
                x, y,
                x+r, y+h,
                x+r, y+side+h,
                x,y+side+h+h,
                x-r, y+side+h,
                x-r, y+h


        };
        return new PolygonRegion(new TextureRegion(getTexture(color)),points
                , new short[] { //4 triangles using vertices to make hexagon
                0, 1, 5,
                1, 4, 2,
                5, 1, 4,
                2, 3, 4});
    }
    public Texture getTexture(Color color){


        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);

        pix.setColor(color);
        pix.fill();
        textureSolid = new Texture(pix);

       return textureSolid;
    }
    @Override
    public void hide() {
        dispose();
    }
    @Override
    public void dispose() {

        stage.dispose();

        polygonSpriteBatch.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }



}
