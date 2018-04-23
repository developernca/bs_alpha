package com.developernca.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.Logger;
import com.developernca.actor.CloudActor;
import com.developernca.screen.MainMenuScreen;
import com.developernca.utility.AndroidSpec;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created on 4/11/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BSGame extends Game {

    // preferences name
    private static final String PREF_NAME = "bs_pref";

    // preference keys
    private static final String H_SCORE = "hs";// high score

    // preference data
    public static int highScore;

    public ActivityConnector activityConnector;
    public static AndroidSpec as;
    public I18NBundle i18NBundle;
    public TextureAtlas atlas1;
    public static Sound playBtnSound;
    public Preferences pref;
    public String ttfName;

    public static float centerX;
    public static float centerY;
    public static int gw;
    public static int gh;
    public static MainMenuScreen menuScreen;
    public static int bladeLevel;
    public static float bladeSpeed;
    /**
     * Currently, there are 4 cloud images in assets.
     */
    private static final int NUM_CLOUDS = 4;
    private ArrayList<CloudActor> cloudActorList;

    public interface ActivityConnector {
        void connectToActivity();
    }

    public BSGame(ActivityConnector activityConnector, AndroidSpec androidSpec) {
        this.activityConnector = activityConnector;
        as = androidSpec;
    }

    @Override
    public void create() {
        // preferences
        pref = Gdx.app.getPreferences(PREF_NAME);
        // init high score
        highScore = pref.getInteger(H_SCORE, 0);
        // game center x,y
        centerX = Gdx.graphics.getWidth() / 2;
        centerY = Gdx.graphics.getHeight() / 2;
        // game screen width & height
        gw = Gdx.graphics.getWidth();
        gh = Gdx.graphics.getHeight();
        // initialize language setting
        i18NBundle = I18NBundle.createBundle(Gdx.files.internal("strings/val"), as.defaultLoc);
        if (as.defaultLoc.getISO3Language().equals(Locale.JAPANESE.getISO3Language())) {
            ttfName = "fonts/keifont.ttf";
        } else {
            ttfName = "fonts/chlorinar.regular.ttf";
        }
        // load all necessary texture atlas
        atlas1 = new TextureAtlas(as.baf + "/" + Gdx.files.internal("atlas1.txt"));
        // sound
        playBtnSound = Gdx.audio.newSound(Gdx.files.internal("audio/sound_playbtn_click.mp3"));
        // initialize blade data
        initBladeData();
    }

    private void initBladeData() {
        if (highScore < 20) {
            bladeSpeed = as.pt(5);
        }
    }

    public ActivityConnector getActivityConnector() {
        return this.activityConnector;
    }

    /**
     * Setting up cloud actors that will be just flowing in the background and
     * has no effect to game play. The name is from cloud(1) to cloud(n) inside
     * the assets folder.
     *
     * @param stage Stage where the cloud actor will be added.
     */
    public void setupCloudActorList(Stage stage) {
        if (cloudActorList == null) {
            cloudActorList = new ArrayList<>(NUM_CLOUDS);
            for (int i = 1; i <= NUM_CLOUDS; i++) {
                // use atlas1, because all clouds contain in it.
                cloudActorList.add(new CloudActor(0.0f, 0.0f, atlas1.findRegion("cloud" + i)));
            }
        }
        for (int i = 0; i < NUM_CLOUDS; i++) {
            stage.addActor(cloudActorList.get(i));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        menuScreen = null;
        playBtnSound.dispose();
        atlas1.dispose();
    }
}
