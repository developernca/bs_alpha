package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.developernca.game.BSGame;

/**
 * Created on 4/17/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BladeActor extends StillAtlasActor {

    public final MoveToAction moveToAction;

    public BladeActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        drawCircle();

        moveToAction = Actions.moveTo(getX(), BSGame.gh, 2.5f);
        addAction(Actions.forever(moveToAction));
    }

    @Override
    public void act(float delta) {
        if (shouldAct()) {
            super.act(delta);
            boundaryCircle.setPosition(getX(), getY());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void spawn(int x, int y) {
        moveToAction.setX(x);
        setPosition(x, y);
    }

    private boolean shouldAct() {
        return getY() < BSGame.gh;
    }

    public boolean canSpawn() {
        return getY() >= BSGame.gh;
    }
}
