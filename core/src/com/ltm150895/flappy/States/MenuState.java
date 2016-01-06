package com.ltm150895.flappy.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ltm150895.flappy.Beginning;
import com.ltm150895.flappy.sprites.Bird;
import com.ltm150895.flappy.sprites.Bonus;

/**
 * Created by Luis on 26/12/2015.
 */
public class MenuState extends State {
    private Texture Background;
    private Texture playBtn;

    private String text;
    private GlyphLayout glyphLayout;
    private float fontWidth;

    private Bird bird;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Beginning.WIDTH /2, Beginning.HEIGHT / 2);
        Background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        text = "Menu State";
        glyphLayout = new GlyphLayout();
        glyphLayout.setText(Beginning.titleFont, text);
        fontWidth = glyphLayout.width;
        bird = new Bird(0,0);
    }

    @Override
    public void HandleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        HandleInput();
        bird.update(dt);
        cam.position.x = bird.getPosition().x;
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //for pc
        /*sb.draw(Background, 0 , 0 , Beginning.WIDTH , Beginning.HEIGHT );
        sb.draw(playBtn, (Beginning.WIDTH / 2 ) - (playBtn.getWidth() /2 ), (Beginning.HEIGHT / 2 ) - (playBtn.getHeight() / 2));*/

        //for phone and also seems to work on pc regardless of reasons.
        sb.draw(Background, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2 , cam.position.y);
        Beginning.titleFont.draw(sb,glyphLayout, cam.position.x - fontWidth / 2 , cam.position.y + Gdx.graphics.getHeight() / 8);
        sb.draw(bird.getTexture(), cam.position.x - bird.getBounds().getWidth()/2, cam.position.y - playBtn.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        Background.dispose();
        playBtn.dispose();
        System.out.println("MenuState disposed");
    }
}
