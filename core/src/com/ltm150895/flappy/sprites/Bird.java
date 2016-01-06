package com.ltm150895.flappy.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Luis on 02/01/2016.
 */
public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    public Bird(int x , int y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3 , texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if(position.y > 0){
            velocity.add(0, GRAVITY);
        }

        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y);

        if(position.y < 0){
            position.y = 0;
        }

        velocity.scl(1/dt);

        bounds.setPosition(position.x, position.y);
    }

    public void jump(){
        velocity.y = 250;
        flap.play(0.5f);
    }

    public Vector2 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public Rectangle getBounds() { return bounds; }

    public Vector2 getVelocity() { return velocity; }

    public void dispose(){
        texture.dispose();
        flap.dispose();
    }
}
