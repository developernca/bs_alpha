package com.developernca.game;

import com.developernca.screen.SplashScreen;
import com.developernca.utility.AndroidSpec;

public class GameMain extends BSGame {

    public static AndroidSpec as;
    public SplashScreen splashScreen;

    public GameMain(AndroidSpec androidSpec, ActivityConnector activityConnector) {
        super(activityConnector);
        as = androidSpec;
    }

    @Override
    public void create() {
        activityConnector.connectToActivity();
        splashScreen = new SplashScreen(this);
        setScreen(splashScreen);
    }

    @Override
    public void dispose() {

    }

}
