package com.developernca.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.developernca.game.BSGame;


/**
 * Created on 4/22/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class TouchAreaActor extends BaseActor {

    private Texture repeatedTexture;
    private TextureRegion region;

    public TouchAreaActor(float x, float y, FileHandle repeatedTextureFileHandle) {
        super(x, y);
        this.repeatedTexture = new Texture(repeatedTextureFileHandle);
        repeatedTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        setSize(BSGame.gw, this.repeatedTexture.getHeight());
        setPosition(x, y);
        this.region = new TextureRegion(repeatedTexture, (int) getWidth(), (int) getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public boolean isTouched(int touchX, int touchY) {
        return super.isTouched(touchX, touchY);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
