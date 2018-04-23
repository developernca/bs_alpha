package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.developernca.game.BSGame;
import com.developernca.screen.ActorScreenConnector;

/**
 * Developer logo.
 * Created on 4/14/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class LogoActor extends StillAtlasActor {

    private boolean isLogoAnimationStarted = false;
    private boolean doAnim;
    private ActorScreenConnector screenConnector;

    public LogoActor(float x, float y, TextureRegion region) {
        this(x, y, region, false);
    }

    public LogoActor(float x, float y, TextureRegion region, boolean doAnim) {
        this(x, y, region, doAnim, null);
    }

    public LogoActor(float x, float y, TextureRegion region, boolean doAnim, ActorScreenConnector screenConnector) {
        super(x, y, region);
        this.doAnim = doAnim;
        this.screenConnector = screenConnector;
        setSize(region.getRegionWidth(), region.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (doAnim && !isLogoAnimationStarted) {
            animateLogo();
            isLogoAnimationStarted = true;
        }
    }

    /**
     * Set action to logo. Firstly rotate the logo and
     * make logo scale to be large and normal 2 times.
     */
    private void animateLogo() {
        if (screenConnector != null) {
            Action rotate = Actions.rotateBy(360, 0.5f);
            Action logoEnlarge = Actions.scaleBy(BSGame.as.pt(0.15f), BSGame.as.pt(0.15f), 0.5f);
            Action logoNormal = Actions.scaleBy(BSGame.as.pt(-0.15f), BSGame.as.pt(-0.15f), 0.5f);
            Action repeatAction = Actions.repeat(2, Actions.sequence(logoEnlarge, logoNormal));
            Action runnableAction = Actions.run(this::connectToScreen);
            addAction(Actions.sequence(rotate, repeatAction, runnableAction));
        }
    }

    private void connectToScreen() {
        screenConnector.connect();
    }

}
