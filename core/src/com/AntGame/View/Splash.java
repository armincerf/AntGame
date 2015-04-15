package com.AntGame.View;

import com.AntGame.Controller.AntBrainReader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;


/**
 * Created by alexdavis on 01/04/15.
 */
public class Splash implements Screen {
    private Stage stage = new Stage();
    private TextButton selectBrain1, selectBrain2, selectWorld, start, back;
    private Label brain1Label, brain2Label, worldLabel;
    private static String brainFile1 = "/Users/Siren/AntGame/core/assets/brain1.brain", brainFile2 = "/Users/Siren/AntGame/core/assets/brain1.brain", worldFile = "/Users/Siren/AntGame/core/assets/1.world";
    private Table table = new Table();
    final JFileChooser chooser = new JFileChooser();
    private boolean open = false;


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 10, 1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
        stage.draw(); //draw all actors on the Stage.getBatch()

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        final AntBrainReader reader = new AntBrainReader();
        final JFileChooser chooser = new JFileChooser();

        MainMenu.createBasicSkin();
        selectBrain1 = new TextButton("Select Brain for Player 1", MainMenu.skin);
        selectBrain2 = new TextButton("Select Brain for player2", MainMenu.skin);
        selectWorld = new TextButton("Select world file", MainMenu.skin);
        start = new TextButton("Start", MainMenu.skin);
        back = new TextButton("Back", MainMenu.skin);

        brain1Label = new Label("No brain selected", MainMenu.skin);
        brain2Label = new Label("No brain selected", MainMenu.skin);
        worldLabel = new Label("No world selected", MainMenu.skin);


        selectBrain1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "BRAIN files", "brain");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    brain1Label.setText("Chosen world = " + chooser.getSelectedFile().getName());
                    brainFile1 = chooser.getSelectedFile().getAbsolutePath();
                    checkBrain(brainFile1, reader, brain1Label);
                }

            }

        });
        selectBrain2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                System.out.println(1);
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "BRAIN files", "brain");
                chooser.setFileFilter(filter);
                System.out.println(2);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    brain2Label.setText("Brain 2 = " + chooser.getSelectedFile().getName());
                    brainFile2 = chooser.getSelectedFile().getAbsolutePath();
                    checkBrain(brainFile2, reader, brain2Label);
                }
                System.out.println(3);

            }
        });
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (brainFile1 == null) {
                    brain1Label.setText("Please choose a world file first!!");
                } else {

                    ((Game) Gdx.app.getApplicationListener()).setScreen(new Screen2());
                }

            }
        });
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());


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

        table.add(selectBrain1).size(180, 60).padBottom(20).padRight(20);
        table.add(selectWorld).size(180, 60).padBottom(20).padRight(20);
        table.add(selectBrain2).size(180, 60).padBottom(20).row();
        table.add(brain1Label).size(230, 40).padBottom(20).padRight(20);
        table.add(worldLabel).size(230, 40).padBottom(20).padRight(20);

        table.add(brain2Label).size(200, 40).padBottom(20).row();
        table.row();
        table.add(start).size(150, 60).padBottom(20);
        table.add(back).size(150, 60).padBottom(20);


        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);


    }

    public void checkBrain(String brainFile, AntBrainReader reader, Label label) {
        try {
            //noinspection AccessStaticViaInstance
            reader.readBrainFile(brainFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (reader.getCorrect()) {
            label.setText("BRAIN INVALID");
            brainFile1 = null;
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
