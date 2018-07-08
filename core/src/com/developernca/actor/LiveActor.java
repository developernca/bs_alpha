package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.developernca.game.BSGame;

/**
 * Created on 4/24/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class LiveActor extends StillAtlasActor {

    public LiveActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        setGeo();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    /**
     * Decrease bottle when a bird passed to the right side of screen
     * by setting region size.
     *
     * @param live actor live
     */
    public void decreaseLive(int live) {
        region.setRegionWidth(live * (int) BSGame.as.pt(16.0f));
        setGeo();
    }

    /**
     * Increase bottle when a bird passed to the right side of screen
     * by setting region size.
     *
     * @param live actor live
     */
    public void increaseLive(int live) {
        region.setRegionWidth(live * (int) BSGame.as.pt(16.0f));
        setGeo();
    }

    public void resetRegionWidth() {
        region.setRegionWidth(5 * (int) BSGame.as.pt(16.0f));
        setGeo();
    }

    /**
     * Set width, height, position
     */
    private void setGeo() {
        int w = region.getRegionWidth();
        int h = region.getRegionHeight();
        setSize(w, h);
        setPosition(BSGame.gw - w, BSGame.gh - h);
    }
}
