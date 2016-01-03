package com.ltm150895.flappy.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ltm150895.flappy.Beginning;

/**
 * Created by Luis on 26/12/2015.
 */
public class MenuState extends State {
    private Texture Background;
    private Texture playBtn;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Beginning.WIDTH /2, Beginning.HEIGHT / 2);
        Background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
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
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //for pc
        /*sb.draw(Background, 0 , 0 , Beginning.WIDTH , Beginning.HEIGHT );
        sb.draw(playBtn, (Beginning.WIDTH / 2 ) - (playBtn.getWidth() /2 ), (Beginning.HEIGHT / 2 ) - (playBtn.getHeight() / 2));*/

        //for phone
        sb.draw(Background, 0 , 0);
        sb.draw(playBtn, cam.position.x - playBtn.getWidth() / 2 , cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        Background.dispose();
        playBtn.dispose();
        System.out.println("MenuState disposed");
    }
}
