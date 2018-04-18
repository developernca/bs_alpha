package com.developernca.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.I18NBundle;
import com.developernca.actor.CloudActor;
import com.developernca.utility.AndroidSpec;

import java.util.ArrayList;

/**
 * Created on 4/11/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BSGame extends Game {

    public ActivityConnector activityConnector;
    public static AndroidSpec as;
    public I18NBundle i18NBundle;
    public TextureAtlas atlas1;
    public Sound playBtnSound;
    public Preferences pref;
    public String ttfName;

    public static float centerX;
    public static float centerY;
    public static float gw;
    public static float gh;

    private static final String PREF_NAME = "bs_pref";
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
        // game center x,y
        centerX = Gdx.graphics.getWidth() / 2;
        centerY = Gdx.graphics.getHeight() / 2;
        // game screen width & height
        gw = Gdx.graphics.getWidth();
        gh = Gdx.graphics.getHeight();
        // initialize language setting
        ttfName = "fonts/keifont.ttf";
        i18NBundle = I18NBundle.createBundle(Gdx.files.internal("strings/val"), as.defaultLoc);
        // load all necessary texture atlas
        atlas1 = new TextureAtlas(as.baf + "/" + Gdx.files.internal("atlas1.txt"));
        // sound
        playBtnSound = Gdx.audio.newSound(Gdx.files.internal("audio/sound_playbtn_click.wav"));
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

}
