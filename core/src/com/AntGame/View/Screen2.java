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
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.io.IOException;
import java.util.Map;
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
    int antX, antY;

    Random rand = new Random();
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
    public PolygonSprite polySpriteRedAnt = new PolygonSprite(makePoints(Color.PINK));
    public PolygonSprite polySpriteBlackAnt = new PolygonSprite(makePoints(Color.DARK_GRAY));



    public GameController gc;
    private TextButton back, start, pause, show;
    private Label blackScore, redScore;
    private Stage stage = new Stage();
    private boolean run = false;
    private boolean draw = true;
    private int speed = 1;
    private int moves;
    FPSLogger log = new FPSLogger();
    @Override
    public void render(float deltY) {


        Gdx.gl.glClearColor(255, 255, 255, 100); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
        blackScore.setText("Black Team Score = " + gc.getScore(Colour.Black));
        redScore.setText("Red Team Score = " + gc.getScore(Colour.Red));

        log.log();
        //draw all actors on the Stage.getBatch()
        Map map = gc.getAntController().getMap();
        if(run) {
            for (Object a : map.values()) {
                Ant ant = (Ant) a;

                try {
                    for (int i = 0; i < speed; i++) {
                        gc.step(ant.getID());

                    }
                } catch (OutOfMapException e) {
                    e.printStackTrace();
                }


            }
        }
        polygonSpriteBatch.begin();
        for (int j = 0; j < 150; j++) {
            for (int i = 0; i < 150; i++) {
                offset = i % 2 == 0 ? multipleX / 2 : 0;
                if (draw) {
                    if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.Rocky)) {
                        drawCell(polySpriteRock, j, i);
                        }
                    if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.Clear)) {
                        drawCell(polySpriteClear, j, i);
                    }
                    if (gc.getMapController().getMap().getRow(i).getTile(j).getFood() > 0) {
                        drawCell(polySpriteFood, j, i);
                    }
                    try {
                        if (gc.getMapController().isAntAt(new Position(j, i))) {
                            if (gc.getMapController().getMap().getRow(i).getTile(j).getAntOnTile().hasFood()) {
                                drawCell(polySpriteRHill, j, i);
                            } else {
                                if (gc.getMapController().getMap().getRow(i).getTile(j).getAntOnTile().getAntColour().equals(Colour.Black)) {
                                    drawCell(polySpriteBlackAnt, j, i);
                                    antX = i;
                                    antY = j;
                                } else {
                                    drawCell(polySpriteRedAnt, j, i);
                                }
                            }

                        } else if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.antHill)) {
                            if (gc.getMapController().getMap().getRow(i).getTile(j).get_antHill().equals(Colour.Black)) {
                                drawCell(polySpriteBHill, j, i);
                            } else {
                                drawCell(polySpriteRHill, j, i);
                            }
                            }
                    } catch (OutOfMapException e) {
                        e.printStackTrace();
                        }
                    }

                }
            }

        polygonSpriteBatch.end();

        //game step all ants this doesnt work set as the step function needs debugging


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
        moves = 0;
        try {
            gc = new GameController();
            gc.Initialize();
            gc.setAntInstructions(Splash.getBrainFile1(), Splash.getBrainFile2());
            gc.getMapController().createMapFromFile(Splash.getWorld());

        } catch (IOException e) {
            e.printStackTrace();
        }


        MainMenu.createBasicSkin();
        blackScore = new Label("Black Team Score = " + gc.getScore(Colour.Black), MainMenu.skin);
        redScore = new Label("Red Team Score = " + gc.getScore(Colour.Red), MainMenu.skin);
        show = new TextButton("Show/Hide GUI", MainMenu.skin);
        show.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                speed++;
            }
        });
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
        pause = new TextButton("Pause", MainMenu.skin);
        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                run = false;
            }
        });

        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(start).size(150, 60).padBottom(20).padLeft(1300).row();
        table.add(pause).size(150, 60).padBottom(20).padLeft(1300).row();

        table.add(back).size(150, 60).padBottom(20).padLeft(1300).row();
        table.add(redScore).size(150, 60).padBottom(20).padLeft(1300).row();
        table.add(blackScore).size(150, 60).padBottom(20).padLeft(1300).row();
        table.add(show).size(150, 60).padBottom(20).padLeft(1300).row();



        table.setFillParent(true);
        stage.addActor(table);
        boolean aa = false;
        boolean b = false;
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {

                if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.antHill) ) {
                    if (gc.getMapController().getMap().getRow(i).getTile(j).get_antHill().equals(Colour.Black)) {
                        try {
                            Ant a = new Ant(new Position(j, i), Colour.Black, Direction.Right);
                                gc.getMapController().getMap().getRow(i).getTile(j).putAntOnTile(a);
                                gc.getAntController().addAnt(a);
                        } catch (OutOfMapException e) {
                            e.printStackTrace();
                        }
                    }
                    if (gc.getMapController().getMap().getRow(i).getTile(j).get_antHill().equals(Colour.Red)) {
                        try {
                            Ant a = new Ant(new Position(j, i), Colour.Red, Direction.Right);
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
