package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }
}
