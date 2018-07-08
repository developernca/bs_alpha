package com.developernca.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created on 5/19/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class SimpleParticleActor extends BaseActor {

    protected ParticleEffect pe;

    public SimpleParticleActor(float x, float y, String particleFilePath, TextureAtlas atlas) {
        super(x, y);
        setPosition(x, y);
        pe = new ParticleEffect();
        pe.load(Gdx.files.internal(particleFilePath), atlas);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        pe.update(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        pe.draw(batch);
    }
}
