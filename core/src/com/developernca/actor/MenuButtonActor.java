package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.developernca.game.BSGame;

/**
 * Created on 4/19/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class MenuButtonActor extends StillAtlasActor {

    public MenuButtonActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        setPosition(x, y);
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public boolean isTouched(int touchX, int touchY) {
        return super.isTouched(touchX, touchY);
    }

    /**
     * Animation for play button.
     */
    public void doPlayBtnAnim() {
        Action left = Actions.moveBy(BSGame.as.pt(-2.0f), 0.0f, 0.0f);
        Action right = Actions.moveBy(BSGame.as.pt(4.0f), 0.0f, 0.0f);
        Action mid = Actions.moveBy(BSGame.as.pt(-2.0f), 0.0f, 0.0f);
        Action repeat = Actions.repeat(3, Actions.sequence(left, right, mid));
        Action delayAction2 = Actions.delay(2.0f);
        Action seq = Actions.sequence(repeat, delayAction2);
        addAction(Actions.forever(seq));
    }
}