package com.AntGame.View;

/**
 * Created by alexdavis on 01/04/15.
 */
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileFilter;

public class MainMenu implements Screen {

    private Stage stage = new Stage();
    private Table table = new Table();

    public static Skin skin;
    private static String worldFile = "";

    private TextButton buttonPlay ,
            buttonExit ;
    private Label world;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }
    public static void createBasicSkin(){

        //Create a font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create a texture
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create a button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.CYAN);
        textButtonStyle.checked = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.newDrawable("background", Color.DARK_GRAY);
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        skin.add("default", textButtonStyle);
        skin.add("default", labelStyle);


    }

    @Override
    public void show() {
        final JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "WORLD files", "world");
        chooser.setFileFilter(filter);
        createBasicSkin();
        buttonPlay = new TextButton("Play", skin);
        buttonExit = new TextButton("Choose a world", skin);
        world = new Label("Chosen world = ", skin);
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Same way we moved here from the Splash Screen
                //We set it to new Splash because we got no other screens
                //otherwise you put the screen there where you want to go
                if(chooser.getSelectedFile() == null){
                    world.setText("Please choose a world file first!!");
                }
                else {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new Screen2());
                }
            }
        });
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {


                    world.setText("Chosen world = " + chooser.getSelectedFile().getName());
                    worldFile = chooser.getSelectedFile().getAbsolutePath();

                }

            }
        });


        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(buttonPlay).size(150,60).padBottom(20).row();
        table.add(buttonExit).size(150,60).padBottom(20).row();
        table.add(world).size(280,40).padBottom(20).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();

    }

    public  static String getWorld(){
        return worldFile;
    }
}