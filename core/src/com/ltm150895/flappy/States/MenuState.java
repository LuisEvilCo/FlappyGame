package com.ltm150895.flappy.States;

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
        Background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void HandleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(Background, 0 , 0 , Beginning.WIDTH , Beginning.HEIGHT );
        sb.draw(playBtn, (Beginning.WIDTH / 2 ) - (playBtn.getWidth() /2 ), (Beginning.HEIGHT / 2 ) - (playBtn.getHeight() / 2));
        sb.end();
    }
}
