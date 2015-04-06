package com.AntGame.View;

import com.AntGame.Controller.GameController;
import com.AntGame.Controller.OutOfMapException;
import com.AntGame.Model.Ant;
import com.AntGame.Model.Helper.Colour;
import com.AntGame.Model.Helper.Direction;
import com.AntGame.Model.Helper.Position;
import com.AntGame.Model.TileType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    PolygonSpriteBatch polygonSpriteBatch = new PolygonSpriteBatch();

    // To assign at the beginning


    Texture textureSolid;
    private Table table = new Table();
    float x, y, offset;

    Random rand = new Random();
    int antX = 100, antY = 100;
    float side = 5;
    float h = (float) (Math.sin(DegreesToRadians(30)) * side);
    float r = (float) (Math.cos(DegreesToRadians(30)) * side);


    float multipleX = (float) Math.sqrt(3) * side;
    float multipleY = side + (side / 2);
    final float[] points = {      // vertices
            x, y,
            x + r, y + h,
            x + r, y + side + h,
            x, y + side + h + h,
            x - r, y + side + h,
            x - r, y + h


    };
    public PolygonSprite polySpriteRock = new PolygonSprite(makePoints(Color.BLACK));
    public PolygonSprite polySpriteClear = new PolygonSprite(makePoints(Color.LIGHT_GRAY));
    public PolygonSprite polySpriteFood = new PolygonSprite(makePoints(Color.GREEN));
    public PolygonSprite polySpriteRHill = new PolygonSprite(makePoints(Color.RED));
    public PolygonSprite polySpriteBHill = new PolygonSprite(makePoints(Color.BLACK));
    public PolygonSprite polySpriteAnt = new PolygonSprite(makePoints(Color.CYAN));


    public GameController gc;
    private TextButton back, start;
    private Stage stage = new Stage();
    private String worldFile;
    private boolean run = false;

    @Override
    public void render(float deltY) {
        Gdx.gl.glClearColor(255, 255, 255, 100); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
        //draw all actors on the Stage.getBatch()
        polygonSpriteBatch.begin();
        for (int j = 0; j < 150; j++) {
            for (int i = 0; i < 150; i++) {
                offset = i % 2 == 0 ? multipleX / 2 : 0;
                if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.Rocky)) {
                    drawCell(polySpriteRock, j, i);
                }
                if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.Clear)) {
                    drawCell(polySpriteClear, j, i);
                }
                if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.Food)) {
                    drawCell(polySpriteFood, j, i);
                }
                if (gc.getMapController().getMap().getRow(i).getTile(j).hasAnt()) {
                    drawCell(polySpriteAnt, j, i);
                } else if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.antHill)) {
                    if (gc.getMapController().getMap().getRow(i).getTile(j).get_antHill().equals(Colour.Black)) {
                        drawCell(polySpriteBHill, j, i);
                    } else {
                        drawCell(polySpriteRHill, j, i);
                    }
                }
            }
        }
        polygonSpriteBatch.end();

        gc.getMapController().getMap().getRow(antY).getTile(antX).clearAnt();

        //game step all ants this doesnt work set as the step function needs debugging
        /*
        Map map = gc.getAntController().getMap();
        if(run){
        for (Object a : map.values()) {
            Ant ant = (Ant) a;
            try {
                gc.step(ant.getID());
            } catch (OutOfMapException e) {
                e.printStackTrace();
            }
        }
        }
        */
        stage.draw();
    }


    private void drawCell(PolygonSprite polySprite, int x, int y) {
        polySprite.setX(gc.getMapController().getMap().getRow(y).getTile(x).getTilePosition().get_x() * multipleX + offset);
        polySprite.setY(gc.getMapController().getMap().getRow(y).getTile(x).getTilePosition().get_y() * multipleY);
        polySprite.draw(polygonSpriteBatch);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        //ad ants to ant hill
        try {
            gc = new GameController();
            gc.Initialize();
            gc.setAntInstructions(Splash.getBrainFile1(), Splash.getBrainFile2());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(multipleX);
        try {
            gc.getMapController().createMapFromFile(Splash.getWorld());
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainMenu.createBasicSkin();
        back = new TextButton("Back", MainMenu.skin);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        });
        start = new TextButton("Start", MainMenu.skin);
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                run = true;
            }
        });

        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(start).size(150, 60).padBottom(20).padLeft(1300).row();

        table.add(back).size(150, 60).padBottom(20).padLeft(1300).row();

        table.setFillParent(true);
        stage.addActor(table);
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.antHill)) {
                    if (gc.getMapController().getMap().getRow(i).getTile(j).get_antHill().equals(Colour.Black)) {
                        try {
                            Ant a = new Ant(new Position(j, i), Colour.Black, Direction.Left);
                            gc.getMapController().getMap().getRow(i).getTile(j).putAntOnTile(a);
                            gc.getAntController().addAnt(a);
                        } catch (OutOfMapException e) {
                            e.printStackTrace();
                        }

                    } else {
                        try {
                            Ant a = new Ant(new Position(j, i), Colour.Red, Direction.Left);
                            gc.getMapController().getMap().getRow(i).getTile(j).putAntOnTile(a);
                            gc.getAntController().addAnt(a);
                        } catch (OutOfMapException e) {
                            e.printStackTrace();
                        }


                    }

                }
            }
        }

        Gdx.input.setInputProcessor(stage);

    }

    public static double DegreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public PolygonRegion makePoints(Color color) {

        return new PolygonRegion(new TextureRegion(getTexture(color)), points
                , new short[]{ //4 triangles using vertices to make hexagon
                0, 1, 5,
                1, 4, 2,
                5, 1, 4,
                2, 3, 4});
    }

    public Texture getTexture(Color color) {


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
