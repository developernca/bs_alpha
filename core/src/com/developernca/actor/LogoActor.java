package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 * Developer logo.
 * Created on 4/14/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class LogoActor extends StillAtlasActor {

    private boolean isLogoAnimationStarted = false;
    public boolean isLogoAnimationFinished = false;

    public LogoActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        setSelfOriginToCenter();
        setOriginToScreenCenter();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!isLogoAnimationStarted) {
            animateLogo();
            isLogoAnimationStarted = true;
        }
    }

    /**
     * Set action to logo. Firstly rotate the logo and
     * make logo scale to be large and normal 2 times.
     */
    private void animateLogo() {
        Action rotate = Actions.rotateBy(360, 0.5f);
        Action logoEnlarge = Actions.scaleBy(0.5f, 0.5f, 0.5f);
        Action logoNormal = Actions.scaleBy(-0.5f, -0.5f, 0.5f);
        Action repeatAction = Actions.repeat(2, Actions.sequence(logoEnlarge, logoNormal));
        Action runnableAction = Actions.run(this::setLogoAnimated);
        addAction(Actions.sequence(rotate, repeatAction, runnableAction));
    }

    private void setLogoAnimated() {
        isLogoAnimationFinished = true;
    }

}
