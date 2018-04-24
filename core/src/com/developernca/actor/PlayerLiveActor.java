package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.developernca.game.BSGame;

/**
 * Created by nyeinchanaung on 4/24/2018.
 */

public class PlayerLiveActor extends StillAtlasActor {


    public PlayerLiveActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        int w = region.getRegionWidth();
        int h = region.getRegionHeight();
        setSize(w, h);
        setPosition(BSGame.gw - w, BSGame.gh - h);
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
