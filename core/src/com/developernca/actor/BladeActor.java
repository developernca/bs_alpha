package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.developernca.game.BSGame;

/**
 * Created on 4/17/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BladeActor extends StillAtlasActor {

    public BladeActor(float x, float y, TextureRegion region) {
        super(x, y, region);
        setSize(region.getRegionWidth(), region.getRegionHeight());
        drawCircle();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (y >= BSGame.gh) {
            setVisible(false);
        }
        if (isVisible()) {
            y += BSGame.bladeSpeed;
            setPosition(x, y);
        }
        boundaryCircle.setPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void spawn(float x, float y) {
        this.x = x;
        this.y = y;
        setPosition(x, y);
        setVisible(true);
    }

    public void setXY(float x, float y){
        setPosition(x, y);
        this.x = x;
        this.y = y;
    }
}
