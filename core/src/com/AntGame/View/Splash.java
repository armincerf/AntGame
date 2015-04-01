package com.AntGame.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by alexdavis on 01/04/15.
 */
public class Splash implements Screen {
    private Texture texture = new Texture(Gdx.files.internal("/Users/alexdavis/Downloads/test/core/assets/badlogic.jpg"));
    private Image splashImage = new Image(texture);
    private Stage stage = new Stage();
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1); //sets clear color to black
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //clear the batch
        stage.act(); //update all actors
        stage.draw(); //draw all actors on the Stage.getBatch()

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        /*If your image is not the same size as your screen
                 *splashImage.setX(?);
                 *splashImage.setY(?);
                 */
        stage.addActor(splashImage); //adds the image as an actor to the stage
        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.5f), Actions.delay(2), Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Splash());
            }
        })));
    }

    @Override
    public void hide() {
        dispose();
    }
    @Override
    public void dispose() {
        texture.dispose();
        stage.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }



}
