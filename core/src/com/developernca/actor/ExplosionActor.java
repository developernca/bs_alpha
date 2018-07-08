package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

/**
 * Created on 5/14/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class ExplosionActor extends AnimAtlasActor {

    private float lastFrame;// There are 7 frames in explosion sheet.

    public ExplosionActor(float x, float y, float frameDuration, TextureAtlas spriteAtlas, PlayMode loop) {
        super(x, y, frameDuration, spriteAtlas, loop);
        lastFrame = frameDuration * 7.0f;
    }

    @Override
    public void act(float delta) {
        if (elapsedTime < lastFrame) {
            super.act(delta);
        } else {
            remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
