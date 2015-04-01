package com.AntGame.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MyGdxGame extends Game {
    public static final String TITLE="Game Project";
    Screen mainGameScreen;
    private Viewport viewport;
    private static OrthographicCamera camera;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 480);
        viewport = new FitViewport(640, 480, camera);
        mainGameScreen = new MainMenu();
        setScreen(mainGameScreen);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.update();

    }
    public static OrthographicCamera getCamera(){
        return camera;
    }

}
