package com.AntGame.View.desktop;

import com.AntGame.View.MyGdxGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
                cfg.title = "Title";
        cfg.height = 1180;
                cfg.width = 1440;


        cfg.fullscreen = false;
        cfg.vSyncEnabled = false; // Setting to false disables vertical sync
        cfg.foregroundFPS = 800; // Setting to 0 disables foreground fps throttling
        cfg.backgroundFPS = 60; // Setting to 0 disables background fps throttling
                new LwjglApplication(new MyGdxGame(), cfg);
	}
}
