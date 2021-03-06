package com.ltm150895.flappy.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ltm150895.flappy.Beginning;
import com.ltm150895.flappy.sprites.Bird;
import com.ltm150895.flappy.sprites.Bonus;
import com.ltm150895.flappy.sprites.Tube;

/**
 * Created by Luis on 02/01/2016.
 */
public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    private int score =0;

    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1 , groundPos2;

    private Array<Tube> tubes;

    private GlyphLayout glyphLayout;
    private float scoreStringWidth;

    private Bonus star;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(25,Beginning.HEIGHT/2);
        cam.setToOrtho(false, Beginning.WIDTH /2, Beginning.HEIGHT / 2);
        bg = new Texture("bg.png");

        ground = new Texture("ground.png");
        groundPos1 = new Vector2((cam.position.x - cam.viewportWidth / 2), GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);

        tubes = new Array<Tube>();
        for(int i = 1 ; i <= TUBE_COUNT ; i+=1){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        glyphLayout = new GlyphLayout();
        glyphLayout.setText(Beginning.titleFont, Integer.toString(score));
        scoreStringWidth = glyphLayout.width;
        star = new Bonus(tubes.get(1).getPosTopTube().x , tubes.get(1).getPosTopTube().y);
    }

    @Override
    protected void HandleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    private void dead(){
        //gsm.set(new PlayState(gsm));
        gsm.set(new GameOver(gsm,score));
    }

    @Override
    public void update(float dt) {
        HandleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        for(int i = 0 ; i < tubes.size; i+=1){ // for(Tube tube : tubes) //fot in the iterative form generates GdxRuntimeException #iterator()
            Tube tube = tubes.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING)* TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())){
                dead();
            }

            if(tube.cleared(bird.getBounds())){
                score++;
                System.out.println("Score = " + score);
            }
        }

        if(star.collides(bird.getBounds())){
            score += 2;
            System.out.println("bonus!!!");
        }

        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            dead();
        }

        glyphLayout.setText(Beginning.titleFont, Integer.toString(score));

        star.reposition(tubes.get(1).getPosTopTube().x + tubes.get(1).getTopTube().getWidth() / 4 ,tubes.get(1).getPosTopTube().y - 50 );

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x , tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x , tube.getPosBottomTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        Beginning.titleFont.draw(sb,glyphLayout, cam.position.x - scoreStringWidth / 2 , cam.position.y + Gdx.graphics.getHeight() / 8);
        sb.draw(star.getTexture(), star.getPosition().x, star.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for(Tube tube:tubes){
            tube.dispose();
        }
        ground.dispose();
        star.dispose();
        System.out.println("PlayState disposed");
    }

    private void updateGround(){
        if(cam.position.x - cam.viewportWidth/2 > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(cam.position.x - cam.viewportWidth/2 > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}