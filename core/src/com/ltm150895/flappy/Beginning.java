package com.ltm150895.flappy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.ltm150895.flappy.States.GameStateManager;
import com.ltm150895.flappy.States.MenuState;

public class Beginning extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Intento 1";

	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music music;

	public static BitmapFont titleFont, textFont;
	
	@Override
	public void create () {
        createFont();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("cotton.mp3"));
		music.setLooping(true);
		music.setVolume(0.3f);
		//music.play(); TODO: music disabled temporarily, because reasons.
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	private void createFont(){
		FileHandle fontfile = Gdx.files.internal("Roboto-Regular.ttf");
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontfile);
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 12;
		textFont = generator.generateFont(parameter);
		parameter.size = 22;
		titleFont = generator.generateFont(parameter);
		System.out.println("Font generated motherfucker");
	}
}
