package com.ltm150895.flappy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ltm150895.flappy.Beginning;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Beginning.WIDTH;
		config.height = Beginning.HEIGHT;
		config.title = Beginning.TITLE;
		new LwjglApplication(new Beginning(), config);
	}
}
