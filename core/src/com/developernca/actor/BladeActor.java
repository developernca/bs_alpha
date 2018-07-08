package com.developernca.actor;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.developernca.game.BSGame;
import com.developernca.screen.GamePlayScreen;

/**
 * Created on 4/17/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BladeActor extends StillAtlasActor {

    private final MoveToAction moveToAction;
    private final GamePlayScreen gamePlayScreen;
    private int level;
    private float[] speed = {1.3f, 1.2f, 1.1f, 1.0f};

    public BladeActor(float x, float y, TextureRegion region, Screen screen) {
        super(x, y, region);
        this.gamePlayScreen = (GamePlayScreen) screen;
        level = 1;
        setSize(region.getRegionWidth(), region.getRegionHeight());
        drawCircle();
        moveToAction = Actions.moveTo(getX(), BSGame.gh, speed[level - 1]);
        Action runAction = Actions.run(() -> gamePlayScreen.makeBonusItem());
        addAction(Actions.forever(Actions.sequence(moveToAction, runAction)));
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

    public void levelUp() {
        ++level;
        moveToAction.setDuration(speed[level - 1]);
        region = BSGame.atlas1.findRegion("blade_lvl" + level);
    }

    private boolean shouldAct() {
        return getY() < BSGame.gh;
    }

    public boolean canSpawn() {
        return getY() >= BSGame.gh;
    }
}
