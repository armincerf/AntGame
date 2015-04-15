package com.AntGame.View;

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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by alexdavis on 12/04/15.
 */
public class TournamentPlayerNumberPicker implements Screen {


    TextButton start;
    TextField input;
    int noPlayers;
    private Stage stage = new Stage();
    private Table table = new Table();

    @Override
    public void show() {
        MainMenu.createBasicSkin();
        BitmapFont font = new BitmapFont();
        final Skin winSkin = new Skin();
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
        winSkin.add("default", labelStyle);
        winSkin.add("dialog", textButtonStyle);


        start = new TextButton("Start tourney", MainMenu.skin);
        input = new TextField("2", MainMenu.skin);
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (isInteger(input.getText())) {
                    noPlayers = Integer.parseInt(input.getText());
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new Tournament(noPlayers));
                } else {
                    Label label = new Label("Please enter a number.", winSkin);
                    label.setWrap(true);
                    label.setFontScale(2f);
                    label.setAlignment(Align.center);

                    Dialog dialog =
                            new Dialog("", winSkin, "dialog") {
                                protected void result(Object object) {
                                    System.out.println("Chosen: " + object);
                                }
                            };

                    dialog.padTop(50).padBottom(50);
                    dialog.getContentTable().add(label).width(200).row();
                    dialog.getButtonTable().padTop(50);

                    TextButton dbutton = new TextButton("Ok", winSkin, "dialog");
                    dialog.button(dbutton, true);

                    dialog.layout();
                    dialog.show(stage);
                }
            }

        });
        table.add(start).size(250, 60).padBottom(20).row();
        table.add(input).size(250, 60).padBottom(20).row();


        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);


    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 50, 50, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
