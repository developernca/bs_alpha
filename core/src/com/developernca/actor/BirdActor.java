package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.developernca.game.BSGame;
import com.developernca.screen.GamePlayScreen;

/**
 * Created on 4/21/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BirdActor extends AnimAtlasActor {

    public Action flyAction;
    private MoveToAction moveToAction;
    public static GamePlayScreen parentScreen;
    public final int level;
    private static float[] moveDuration = {10.0f, 9.0f, 8.0f, 7.0f};
    private static float[][] delayDuration = {{1.0f, 1.4f}, {1.5f, 2.0f}, {2.0f, 2.5f}, {2.6f, 2.8f}};

    public BirdActor(float y, float frameDuration, TextureAtlas spriteAtlas, int level) {
        super(0.0f, 0.0f, frameDuration, spriteAtlas);
        setRandomPosition(y);
        this.level = level;
        drawCircle();
        setFlyAnimation();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        boundaryCircle.setPosition(getX(), getY());
    }

    /**
     * Set position with random x,y value.
     *
     * @param lowestY minimum available value for y position (above touch area).
     */
    public void setRandomPosition(float lowestY) {
        x = MathUtils.random(getWidth(), BSGame.as.pt(75.0f) - getWidth()) * (-1);
        y = MathUtils.random(lowestY, BSGame.gh - getHeight());
        setPosition(x, y);
        if (moveToAction != null) {
            moveToAction.setY(y);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    private void setFlyAnimation() {
        float randomDelay = MathUtils.random(delayDuration[level][0], delayDuration[level][1]);
        Action delay = Actions.delay(randomDelay);
        moveToAction = Actions.moveTo(BSGame.gw, getY(), moveDuration[level]);
        Action makeUserFailure = Actions.run(this::makePlayerFailure);
        Action returnTo = Actions.moveTo(x, getY(), 0.0f);
        flyAction = Actions.forever(Actions.sequence(delay, moveToAction, makeUserFailure, returnTo));
        addAction(flyAction);
    }

    private void makePlayerFailure() {
        parentScreen.reducePlayerLive();
    }
}