package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Base class for atlas base animations.
 * Created on 4/21/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class AnimAtlasActor extends BaseActor {

    private Animation<TextureAtlas.AtlasRegion> animation;
    /**
     * Atlas region to use in draw method.
     */
    private TextureRegion region;
    private float frameDuration;
    float elapsedTime;
    private float width;
    private float height;

    AnimAtlasActor(float x, float y, float frameDuration, TextureAtlas spriteAtlas) {
        this(x, y, frameDuration, spriteAtlas, Animation.PlayMode.LOOP);
    }

    AnimAtlasActor(float x, float y, float frameDuration, TextureAtlas spriteAtlas, Animation.PlayMode loop) {
        super(x, y);
        setPosition(x, y);
        this.elapsedTime = 0.0f;
        this.frameDuration = frameDuration;
        this.width = spriteAtlas.getRegions().get(0).getRegionWidth();
        this.height = spriteAtlas.getRegions().get(0).getRegionHeight();
        setSize(width, height);
        animation = new Animation<>(frameDuration, spriteAtlas.getRegions(), loop);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
        region = animation.getKeyFrame(elapsedTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(region,
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
