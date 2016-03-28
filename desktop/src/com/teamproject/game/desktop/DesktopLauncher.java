package com.teamproject.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.teamproject.game.additions.Constant;
import com.teamproject.game.STGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constant.WIDTH;
		config.height = Constant.HEIGHT;
		config.title = Constant.TITLE;
		new LwjglApplication(new STGame(), config);
	}
}
