package com.developernca.game;

import com.developernca.screen.SplashScreen;
import com.developernca.utility.AndroidSpec;

public class GameMain extends BSGame {

    public SplashScreen splashScreen;

    public GameMain(AndroidSpec androidSpec, ActivityConnector activityConnector) {
        super(activityConnector, androidSpec);
    }

    @Override
    public void create() {
        super.create();
        // screens
        splashScreen = new SplashScreen(this);
        setScreen(splashScreen);
    }

    @Override
    public void dispose() {

    }

}
