package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by nyeinchanaung on 4/17/2018.
 */

public class BladeActor extends StillAtlasActor {

    public BladeActor(float x, float y, TextureRegion region, boolean
            boundWithCircle) {
        super(x, y, region);
        setPosition(x, y);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        if (boundWithCircle) {
            drawCircle();
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (boundaryCircle != null) {
            boundaryCircle.setPosition(getX(), getY());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public boolean isTouched(int touchX, int touchY) {
        return super.isTouched(touchX, touchY);
    }

}
