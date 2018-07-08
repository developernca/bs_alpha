package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.developernca.game.BSGame;

/**
 * Clouds.
 * Created on 4/15/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class CloudActor extends StillAtlasActor {

    private int halfScreen;
    private int maxY;
    private float initialX;
    private float maxX;
    private float selfX;
    private float speed;

    public CloudActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        halfScreen = BSGame.gh / 2;
        maxY = (int) (BSGame.gh - getHeight());
        // x position
        initialX = (-1) * getWidth();
        selfX = initialX;
        // maximum xm
        maxX = BSGame.gw + getWidth();
        // set position x and y value, which is from one third of screen to max y value
        setPosition(initialX, MathUtils.random(halfScreen, maxY));
        // speed (1 to 4)
        speed = MathUtils.random(0.1f, 1.0f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getX() >= maxX) {
            selfX = initialX;// reset x to initial value
            setY(MathUtils.random(halfScreen, maxY));// reset y to some random value
            speed = MathUtils.random(0.1f, 1.0f);// reset speed
        } else {
            selfX += BSGame.as.pt(speed);
        }
        setX(selfX);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
