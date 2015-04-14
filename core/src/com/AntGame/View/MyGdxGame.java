package com.AntGame.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;


public class MyGdxGame extends Game {
    public static final String TITLE="Game Project";
    Screen mainGameScreen;


    @Override
    public void create() {

        mainGameScreen = new MainMenu();
        setScreen(mainGameScreen);
    }

    public void resize(int width, int height) {


    }

}
