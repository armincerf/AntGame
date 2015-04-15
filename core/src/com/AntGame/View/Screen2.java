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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
@SuppressWarnings("ALL")
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
    public PolygonSprite polySpriteRedAnt = new PolygonSprite(makePoints(Color.PURPLE));
    public PolygonSprite polySpriteBlackAnt = new PolygonSprite(makePoints(Color.BLUE));


    public GameController gc;
    private TextButton back, start, pause, speedUp, slowDown;
    private Label blackScore, redScore;
    private Stage stage = new Stage();
    private boolean run = false;
    private boolean draw = true;
    private int speed = 1;
    private int moves;
    private int width, height;
    FPSLogger log = new FPSLogger();

    /**
     * This method is called up to 60 times per second (depending on PC performance)
     * its draws a hexagon for each Tile object stored in the map controller
     *
     * @param deltY the number of frames rendered per second
     */
    @Override
    public void render(float deltY) {

        Gdx.gl.glClearColor(200, 200, 200, 200); //sets bg colour
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clears all objects
        stage.act(); //update all actors
        blackScore.setText("Black Score = " + gc.getScore(Colour.Black));
        redScore.setText("Red Score = " + gc.getScore(Colour.Red));
        if (Splash.getRounds() < moves) {
            run = false;
            Dialog dialog = new Dialog("", MainMenu.skin, "dialog") {
                protected void result(Object object) {
                    System.out.println("Chosen: " + object);
                }
            }.text("Game Over!").show(stage);
            TextButton dbutton = new TextButton("Game Over - Click to go back\n Final score: \n" + redScore.getText() + "\n" + blackScore.getText(), MainMenu.skin, "dialog");
            dialog.button(dbutton, true);
            dbutton.addListener(new ClickListener() {

                /**
                 * Action listener for the play button
                 *
                 * @param event an event
                 * @param x     mouse position x
                 * @param y     mouse position y
                 */
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());

                }
            });
        }
        //draw all actors on the Stage.getBatch()
        Map map = gc.getAntController().getMap();
        if (run) {
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
            moves += 1 * speed;
            if (moves % 500 == 0) {
                System.out.println(moves + " moves");
            }

        }
        polygonSpriteBatch.begin();
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                offset = i % 2 == 0 ? multipleX / 2 : 0;
                if (draw) {
                    if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.Rocky)) {
                        drawCell(polySpriteRock, j, i);
                        System.out.println("draw");
                    }
                    if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.Clear)) {
                        drawCell(polySpriteClear, j, i);
                    }
                    if (gc.getMapController().getMap().getRow(i).getTile(j).getFood() > 0) {
                        float scale = gc.getMapController().getMap().getRow(i).getTile(j).getFood();
                        scale = (scale / 100) * 4;
                        scale = (float) (scale + 0.6);
                        drawCell(polySpriteClear, j, i);
                        drawCell(polySpriteFood, j, i, scale);

                    }

                    try {
                        if (gc.getMapController().isAntAt(new Position(j, i))) {
                            if (gc.getMapController().getMap().getRow(i).getTile(j).getAntOnTile().getAntColour().equals(Colour.Black)) {
                                drawCell(polySpriteBlackAnt, j, i);
                            } else {
                                drawCell(polySpriteRedAnt, j, i);
                            }


                        } else if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.antHill)) {

                            if (gc.getMapController().getMap().getRow(i).getTile(j).get_antHill().equals(Colour.Black)) {
                                if (gc.getMapController().getMap().getRow(i).getTile(j).getFood() > 0) {
                                    float scale = gc.getMapController().getMap().getRow(i).getTile(j).getFood();
                                    scale = (scale / 100) * 4;
                                    scale = (float) (scale + 0.6);
                                    drawCell(polySpriteBHill, j, i);
                                    drawCell(polySpriteFood, j, i, scale);

                                } else {
                                    drawCell(polySpriteBHill, j, i);
                                }
                            } else {
                                if (gc.getMapController().getMap().getRow(i).getTile(j).getFood() > 0) {
                                    float scale = gc.getMapController().getMap().getRow(i).getTile(j).getFood();
                                    scale = (scale / 100) * 4;
                                    scale = (float) (scale + 0.6);
                                    drawCell(polySpriteRHill, j, i);
                                    drawCell(polySpriteFood, j, i, scale);

                                } else {
                                    drawCell(polySpriteRHill, j, i);
                                }
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

    private void showEndPop() {

    }


    private void drawCell(PolygonSprite polySprite, int x, int y) {
        polySprite.setX(gc.getMapController().getMap().getRow(y).getTile(x).getTilePosition().get_x() * multipleX + offset);
        polySprite.setY(gc.getMapController().getMap().getRow(y).getTile(x).getTilePosition().get_y() * multipleY);
        polySprite.draw(polygonSpriteBatch);
    }

    private void drawCell(PolygonSprite polySprite, int x, int y, float scale) {
        polySprite.setX(gc.getMapController().getMap().getRow(y).getTile(x).getTilePosition().get_x() * multipleX + offset);
        polySprite.setY(gc.getMapController().getMap().getRow(y).getTile(x).getTilePosition().get_y() * multipleY);
        if (scale < 0.9) {
            polySprite.setScale(scale);

        }

        polySprite.draw(polygonSpriteBatch);
        polySprite.setScale(1);
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
            if (Splash.getWorld().equals("RANDOM")) {
                gc.getMapController().createRandomMap();
            } else {
                gc.getMapController().createMapFromFile(Splash.getWorld());
            }
            width = gc.getMapController().getWidth();
            height = gc.getMapController().getHeight();
            System.out.println(width);

        } catch (IOException e) {
            e.printStackTrace();
        }


        MainMenu.createBasicSkin();
        blackScore = new Label("Black Team Score = " + gc.getScore(Colour.Black), MainMenu.skin);
        redScore = new Label("Red Team Score = " + gc.getScore(Colour.Red), MainMenu.skin);
        speedUp = new TextButton("Speed Up", MainMenu.skin);
        speedUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                speed++;
            }
        });
        slowDown = new TextButton("Slow Down", MainMenu.skin);
        slowDown.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (speed > 1) {
                    speed--;
                }
                System.out.println(speed + "Speed");
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
        table.add(speedUp).size(150, 60).padBottom(20).padLeft(1300).row();
        table.add(slowDown).size(150, 60).padBottom(20).padLeft(1300).row();


        table.setFillParent(true);
        stage.addActor(table);
        boolean aa = false;
        boolean b = false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                if (gc.getMapController().getMap().getRow(i).getTile(j).getTileType().equals(TileType.antHill)) {
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
