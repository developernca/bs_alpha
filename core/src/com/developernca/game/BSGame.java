package com.developernca.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.I18NBundle;
import com.developernca.utility.AndroidSpec;

import java.util.Locale;

/**
 * Created by nyeinchanaung on 4/11/2018.
 */

public class BSGame extends Game {

    protected ActivityConnector activityConnector;
    public static AndroidSpec as;
    public I18NBundle i18NBundle;
    public TextureAtlas atlas1;
    public static float centerX;
    public static float centerY;
    public static float gw;
    public static float gh;
    public static Sound playBtnSound;

    public interface ActivityConnector {
        void connectToActivity();
    }

    public BSGame(ActivityConnector activityConnector, AndroidSpec androidSpec) {
        this.activityConnector = activityConnector;
        as = androidSpec;
    }

    @Override
    public void create() {
        // game center x,y
        centerX = Gdx.graphics.getWidth() / 2;
        centerY = Gdx.graphics.getHeight() / 2;
        // game screen width & height
        gw = Gdx.graphics.getWidth();
        gh = Gdx.graphics.getHeight();
        // load language properties file
        i18NBundle = I18NBundle.createBundle(Gdx.files.internal("strings/val"), Locale.ENGLISH);
        // load all necessary texture atlas
        atlas1 = new TextureAtlas(as.baf + "/" + Gdx.files.internal("atlas1.txt"));
        // sound
        playBtnSound = Gdx.audio.newSound(Gdx.files.internal("audio/sound_playbtn_click.wav"));
    }

    public ActivityConnector getActivityConnector() {
        return this.activityConnector;
    }

}
