package com.ltm150895.flappy.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Luis on 02/01/2016.
 */
public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube, bottomTube;
    private Vector2 postopTube, posbottomTube;
    private Random rand;
    private Rectangle boundsTop, boundsBottom;

    private boolean clearedFlag = false;
    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();

        postopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posbottomTube = new Vector2(x, postopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(postopTube.x, postopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(posbottomTube.x, posbottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return postopTube;
    }

    public Vector2 getPosBottomTube() {
        return posbottomTube;
    }

    public void reposition(float x){
        postopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posbottomTube.set(x, postopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop.setPosition(postopTube.x, postopTube.y);
        boundsBottom.setPosition(posbottomTube.x, posbottomTube.y);
        clearedFlag = false;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public boolean cleared(Rectangle player){
        if((!clearedFlag) && (player.x >= postopTube.x)){
            clearedFlag = true;
            return clearedFlag;
        }else{
            return false;
        }
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
