package com.AntGame.View;

import com.AntGame.Controller.AntBrainReader;
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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by alexdavis on 12/04/15.
 */
@SuppressWarnings("ALL")
public class Tournament implements Screen {

    private Stage stage = new Stage();
    private TextButton selectBrain, selectWorld, start, back;
    private Label brain1Label, worldLabel;
    private static String brainFile1 = "/Users/alexdavis/Downloads/test/core/assets/brain1.brain", brainFile2 = "/Users/alexdavis/Downloads/test/core/assets/brain1.brain", worldFile = "/Users/alexdavis/Downloads/test/core/assets/1.world";
    private Table table = new Table();
    private TextField input;
    private int players;
    Skin winSkin;
    private int currentPlayer = 0;
    private ArrayList<String> brainList;
    private int blackScore, redScore;

    public Tournament(int players) {
        this.players = players;
        brainList = new ArrayList<>();
        System.out.println(players);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 10, 1); //sets clear color to blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
        stage.draw(); //draw all actors on the Stage.getBatch()

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        createSkin();

        final AntBrainReader reader = new AntBrainReader();
        final JFileChooser chooser = new JFileChooser();

        MainMenu.createBasicSkin();
        selectBrain = new TextButton("Select Brain for Player " + currentPlayer, MainMenu.skin);
        selectWorld = new TextButton("Select world file", MainMenu.skin);
        start = new TextButton("Next", MainMenu.skin);
        back = new TextButton("Back", MainMenu.skin);

        input = new TextField("1", winSkin);


        brain1Label = new Label("No brain selected", MainMenu.skin);
        worldLabel = new Label("No world selected", MainMenu.skin);


        selectBrain.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "BRAIN files", "brain");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    brain1Label.setText("Chosen world = " + chooser.getSelectedFile().getName());
                    brainFile1 = chooser.getSelectedFile().getAbsolutePath();
                    checkBrain(brainFile1, "Brain 1", reader, chooser, brain1Label);
                }

            }
        });

        selectWorld.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileNameExtensionFilter worldFilter = new FileNameExtensionFilter(
                        "WORLD files", "world");
                chooser.setFileFilter(worldFilter);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    worldLabel.setText("World = " + chooser.getSelectedFile().getName());
                    worldFile = chooser.getSelectedFile().getAbsolutePath();
                }


            }
        });


        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (players == currentPlayer) {
                    start.setText("Start");


                    if (brainFile1 == null) {
                        brain1Label.setText("Please choose a brain file first!!");
                    } else {

                        try {
                            runTourney(brainList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (brainFile1 != null) {
                        brainList.add(brainFile1);
                        System.out.println(brainList.size() + " brains added");

                        brain1Label.setText("Add another brain");
                        currentPlayer++;
                        if (players == currentPlayer) {
                            start.setText("Start");
                        }
                    } else {
                        brain1Label.setText("Please choose a brain file first!!");

                    }
                }

                }
        });


        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());


            }
        });



        table.add(selectBrain).size(180, 60).padBottom(20).padRight(20);
        table.add(selectWorld).size(180, 60).padBottom(20).padRight(20);

        table.add(brain1Label).size(230, 40).padBottom(20).padRight(20);
        table.add(worldLabel).size(230, 40).padBottom(20).padRight(20);

        table.row();
        table.add(start).size(150, 60).padBottom(20);
        table.add(back).size(150, 60).padBottom(20);
        table.add(input).size(200, 60).padBottom(20);

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);


    }

    private void runTourney(ArrayList<String> brainList) throws IOException {

        int[] scores = new int[players];
        for (int i = 0; i < players; i++) {
            for (int j = 0; j < players; j++) {
                if (i == j) {
                    break;
                }
                System.out.println("player " + i + " vs player " + j + brainList.get(i));
                GameController gc = new GameController();

                gc.Initialize();
                gc.setAntInstructions(brainList.get(i), brainList.get(j));
                gc.getMapController().createMapFromFile(worldFile);
                addAnts(gc);
                Map map = gc.getAntController().getMap();
                System.out.println(map.toString());

                for (int k = 0; k < 100000; k++) {
                    for (Object a : map.values()) {
                        Ant ant = (Ant) a;
                        try {
                            gc.step(ant.getID());
                            if (k % 10000 == 0) {
                                System.out.println(k);
                            }
                        } catch (OutOfMapException e) {
                            e.printStackTrace();
                        }
                    }
                }
                blackScore = gc.getScore(Colour.Black);
                redScore = gc.getScore(Colour.Red);
                System.out.println("moves = " + gc.getMoves());
                System.out.println("black score = " + blackScore + " red score = " + redScore);


            }
        }


    }

    private void addAnts(GameController gc) {
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {

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
    }

    private void createSkin() {
        BitmapFont font = new BitmapFont();
        winSkin = new Skin();
        winSkin.add("default", font);

        //Create texture
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        winSkin.add("background", new Texture(pixmap));
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = winSkin.newDrawable("background", Color.BLACK);
        windowStyle.titleFont = font;
        windowStyle.titleFontColor = Color.WHITE;
        winSkin.add("dialog", windowStyle);
        //Create button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = winSkin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = winSkin.newDrawable("background", Color.CYAN);
        textButtonStyle.checked = winSkin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = winSkin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = winSkin.getFont("default");

        //Create label style
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = winSkin.newDrawable("background", Color.DARK_GRAY);
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;
        //Create text field style
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = winSkin.newDrawable("background", Color.GREEN);
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.WHITE;
        winSkin.add("default", labelStyle);
        winSkin.add("dialog", textButtonStyle);
        winSkin.add("default", textFieldStyle);
    }

    public void checkBrain(String brainFile, String brain, AntBrainReader reader, JFileChooser chooser, Label label) {
        try {
            AntBrainReader.readBrainFile(brainFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (reader.getCorrect()) {
            label.setText("BRAIN INVALID");
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                brain1Label.setText(brain + " = " + chooser.getSelectedFile().getName());
                brainFile1 = chooser.getSelectedFile().getAbsolutePath();
            }
        }
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

    public static String getWorld() {
        return worldFile;
    }

    public static String getBrainFile1() {
        return brainFile1;
    }

    public static String getBrainFile2() {
        return brainFile2;
    }


}

