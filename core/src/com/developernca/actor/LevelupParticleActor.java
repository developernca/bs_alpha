package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created on 5/28/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class LevelupParticleActor extends SimpleParticleActor {

    public LevelupParticleActor(float x, float y, String particleFilePath, TextureAtlas atlas) {
        super(x, y, particleFilePath, atlas);
        pe.setPosition(getX(), getY());
        pe.scaleEffect(2.0f, 2.0f);
        pe.allowCompletion();
        pe.start();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (pe.isComplete()) {
            pe.reset();
            pe.scaleEffect(2.0f, 2.0f);
            remove();
            return;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
