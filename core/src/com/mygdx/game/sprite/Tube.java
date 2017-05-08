package com.mygdx.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by jacky975 on 5/8/17.
 */

public class Tube {

    public static final int TUBE_WIDTH = 52;
    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTub() {
        return bottomTub;
    }

    public Vector2 getPosTopTub() {
        return posTopTub;
    }

    public Vector2 getPosBotTub() {
        return posBotTub;
    }

    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 100;
    private Texture topTube, bottomTub;
    private Vector2 posTopTub, posBotTub;
    private Random rand;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTub = new Texture("bottomtube.png");
        rand = new Random();
        posTopTub = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTub = new Vector2(x, posTopTub.y - TUBE_GAP - bottomTub.getHeight());
    }

    public void reposition(float x) {
        posTopTub.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTub.set(x, posTopTub.y - TUBE_GAP - bottomTub.getHeight());
    }
}
