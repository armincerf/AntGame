package com.AntGame.View;

/**
 * Created by alexdavis on 01/04/15.
 */

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

@SuppressWarnings("ALL")
public class MainMenu implements Screen {

    private Stage stage = new Stage();
    private Table table = new Table();

    public static Skin skin;

    private TextButton buttonPlay, buttonTourney,
            buttonExit ;

    /**
     * Renders the screen.
     * Clears all objects on the screen and redraws.
     * @param delta The time since the last refresh
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 10, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    /**
     * Resize the screen
     * @param width Width of the pane
     * @param height Height of the pane
     */
    @Override
    public void resize(int width, int height) {
    }

    /**
     * Create a basic background
     */
    public static void createBasicSkin(){

        //Create  font
        BitmapFont font = new BitmapFont();
        skin = new Skin();
        skin.add("default", font);

        //Create texture
        Pixmap pixmap = new Pixmap((int)Gdx.graphics.getWidth()/4,(int)Gdx.graphics.getHeight()/10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background",new Texture(pixmap));

        //Create button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.CYAN);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");

        //Create label style
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.background = skin.newDrawable("background", Color.DARK_GRAY);
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        //Create text field style
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = skin.newDrawable("background", Color.DARK_GRAY);
        textFieldStyle.font = font;
        textFieldStyle.fontColor = Color.WHITE;

        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = skin.newDrawable("background", Color.BLACK);
        windowStyle.titleFont = font;
        windowStyle.titleFontColor = Color.WHITE;
        skin.add("dialog", windowStyle);





        skin.add("default", textButtonStyle);
        skin.add("default", labelStyle);
        skin.add("default", textFieldStyle);
        skin.add("dialog", textButtonStyle);
        skin.add("dialog", labelStyle);

    }

    /**
     * Initialises GUI
     */
    @Override
    public void show() {

        final JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "WORLD files", "world");
        chooser.setFileFilter(filter);
        createBasicSkin();
        buttonPlay = new TextButton("Play", skin);
        buttonExit = new TextButton("Exit", skin);
        buttonTourney = new TextButton("Start Tournement", skin);
        buttonPlay.addListener(new ClickListener(){

            /**
             * Action listener for the play button
             * @param event an event
             * @param x mouse position x
             * @param y mouse position y
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Same way we moved here from the Splash Screen
                //We set it to new Splash because we got no other screens
                //otherwise you put the screen there where you want to go

                ((Game) Gdx.app.getApplicationListener()).setScreen(new Splash());

            }
        });
        buttonExit.addListener(new ClickListener(){

            /**
             * Action listener for the play button
             * @param event an event
             * @param x mouse position x
             * @param y mouse position y
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {

                System.exit(0);
            }
        });
        buttonTourney.addListener(new ClickListener() {

            /**
             * Action listener for the play button
             * @param event an event
             * @param x mouse position x
             * @param y mouse position y
             */
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((Game) Gdx.app.getApplicationListener()).setScreen(new TournamentPlayerNumberPicker());
            }
        });


        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        table.add(buttonPlay).size(250, 60).padBottom(20).row();
        table.add(buttonExit).size(250, 60).padBottom(20).row();
        table.add(buttonTourney).size(250, 60).padBottom(20).row();



        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Dispose of the GUI
     */
    @Override
    public void hide() {
        dispose();
    }

    /**
     * Pause the GUI
     */
    @Override
    public void pause() {
    }

    /**
     * Resume the GUI
     */
    @Override
    public void resume() {
    }

    /**
     * Dispose of the stage and skin.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();

    }

}