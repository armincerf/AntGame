package com.AntGame.View.desktop;

import com.AntGame.View.MyGdxGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Title";
        cfg.height = 980;
        cfg.width = 1820;
        new LwjglApplication(new MyGdxGame(), cfg);
	}
}
