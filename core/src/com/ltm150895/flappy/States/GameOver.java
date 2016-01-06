package com.ltm150895.flappy.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ltm150895.flappy.Beginning;


/**
 * Created by Luis on 03/01/2016.
 */
public class GameOver extends State {
    private Texture Background;
    private Texture GameOver;
    private String text;
    private GlyphLayout glyphLayout;
    public GameOver(GameStateManager gsm, int score) {
        super(gsm);
        cam.setToOrtho(false, Beginning.WIDTH /2, Beginning.HEIGHT / 2);
        Background = new Texture("bg.png");
        GameOver = new Texture("gameover.png");
        text = "Score " + Integer.toString(score);
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(Beginning.titleFont, text);
    }

    @Override
    protected void HandleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        HandleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(Background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(GameOver, cam.position.x - GameOver.getWidth() / 2 , cam.position.y);
        Beginning.titleFont.draw(sb,glyphLayout, cam.position.x - glyphLayout.width / 2 , cam.position.y + Gdx.graphics.getHeight() / 8);
        sb.end();
    }

    @Override
    public void dispose() {
        Background.dispose();
        GameOver.dispose();
    }
}
