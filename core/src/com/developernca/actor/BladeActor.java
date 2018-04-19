package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created on 4/17/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BladeActor extends StillAtlasActor {
    public BladeActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        setPosition(x, y);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        drawCircle();
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
}
