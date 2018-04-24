package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.developernca.game.BSGame;

/**
 * Created on 4/21/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BirdActor extends AnimAtlasActor {

    public BirdActor(float x, float y, float frameDuration, TextureAtlas spriteAtlas) {
        super(x, y, frameDuration, spriteAtlas);
        drawCircle();
        flyAnimation();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        boundaryCircle.setPosition(getX(), getY());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void flyAnimation() {
        Action moveTo = Actions.moveTo(BSGame.gw, getY(), 10.0f);
        Action returnTo = Actions.moveTo(0, getY(), 0.0f);
        addAction(Actions.forever(Actions.sequence(moveTo, returnTo)));
    }

}
