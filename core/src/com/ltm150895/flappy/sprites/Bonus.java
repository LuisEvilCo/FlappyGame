package com.ltm150895.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Luis on 05/01/2016.
 */
public class Bonus {
    private Texture bonus;
    private Vector2 position;
    private Rectangle bounds;
    public Bonus(){
        bonus = new Texture("draw.png");
    }

    public Bonus(float x, float y){
        bonus = new Texture("draw.png");
        position = new Vector2(x,y);
        bounds = new Rectangle(x,y, bonus.getWidth(), bonus.getHeight());
    }

    public Texture getTexture(){
        return bonus;
    }

    public Vector2 getPosition(){ return position; }

    public void reposition(float x, float y){
        position.set(x,y);
        bounds.setPosition(x,y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(bounds);
    }

    public void dispose(){ bonus.dispose(); }
}
