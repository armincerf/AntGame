package com.AntGame.View;

import com.AntGame.Controller.MapController;
import com.AntGame.Model.TileType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.AntGame.*;

import java.io.IOException;
import java.util.Random;

/**
 * Created by alexdavis on 01/04/15.
 */
public class Screen2 implements Screen {
    PolygonSprite poly;
    PolygonSpriteBatch polyBatch = new PolygonSpriteBatch(); // To assign at the beginning
    Texture textureSolid;
    private Table table = new Table();
    float x, y, side, r, h;
    Random rand = new Random();
    MapController mc = new MapController();

    private TextButton button;
    private Stage stage = new Stage();
    @Override
    public void render(float delta) {
        polyBatch.setTransformMatrix(MyGdxGame.getCamera().view);
        polyBatch.setProjectionMatrix(MyGdxGame.getCamera().projection);
        Gdx.gl.glClearColor(100, 0, 10, 1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
        stage.draw(); //draw all actors on the Stage.getBatch()
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {


                Color color = Color.CYAN;
                if (mc.getMap().getRow(i).getTile(j).getTileType().equals(TileType.Rocky)) {
                    color = Color.BLACK;
                }
                if (mc.getMap().getRow(i).getTile(j).getTileType().equals(TileType.antHill)) {
                    color = Color.RED;
                }
                if (mc.getMap().getRow(i).getTile(j).getTileType().equals(TileType.Food)) {
                    color = Color.GREEN;
                }
                if(i%2==1) {
                    makepoly(color, (mc.getMap().getRow(i).getTile(j).getTilePosition().get_x()*18+9), mc.getMap().getRow(i).getTile(j).getTilePosition().get_y()*14 );
                }
                else{
                    makepoly(color, (mc.getMap().getRow(i).getTile(j).getTilePosition().get_x()*18), mc.getMap().getRow(i).getTile(j).getTilePosition().get_y()*14 );

                }

                polyBatch.begin();
                poly.draw(polyBatch);

                polyBatch.end();

            }
        }











    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
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
        side =  8;
        h = CalculateH(side);
        r = CalculateR(side);
    }
    public void makepoly(Color color, float x, float y){

    this.x = x;
        this.y = y;
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
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
        poly = new PolygonSprite(polyReg);
        poly.setOrigin(200,200);
        polyBatch = new PolygonSpriteBatch();
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
