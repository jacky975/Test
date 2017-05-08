package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprite.Bird;
import com.mygdx.game.sprite.Tube;

/**
 * Created by jacky975 on 5/8/17.
 */

public class PlayState extends State {

    private static final int TUBE_SPACTING = 125;
    private static final int TUBE_COUNT = 4;
    private Bird bird;
    private Texture bg;

    private Array<Tube> tubes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 200);
        cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT/2);
        bg = new Texture("bg.png");

        tubes = new Array<Tube>();

        for(int i = 1 ; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i* (TUBE_SPACTING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
            bird.jump();
    }

    @Override
    public void update(float deltaTime) {                   //Start reading from here
        handleInput();
        bird.update(deltaTime);
        cam.position.x = bird.getPosition().x + 80;

        for(Tube tube : tubes){
            if(cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTub().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTub().x + ((Tube.TUBE_WIDTH + TUBE_SPACTING) * TUBE_COUNT));
            }
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        batch.draw(bird.getBird(), bird.getPosition().x,bird.getPosition().y);
        for (Tube tube : tubes) {
            batch.draw(tube.getTopTube(), tube.getPosTopTub().x, tube.getPosTopTub().y);
            batch.draw(tube.getBottomTub(), tube.getPosBotTub().x, tube.getPosBotTub().y);
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
