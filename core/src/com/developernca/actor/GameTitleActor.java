package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by nyeinchanaung on 4/16/2018.
 */

public class GameTitleActor extends StillAtlasActor {

    public GameTitleActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        setOriginToScreenCenter();
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
