package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created on 4/14/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class StillAtlasActor extends BaseActor {

    protected TextureRegion region;

    public StillAtlasActor(float x, float y, TextureRegion region) {
        super(x, y);
        this.region = region;
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
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}