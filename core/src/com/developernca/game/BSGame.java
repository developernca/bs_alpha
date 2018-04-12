package com.developernca.game;

import com.badlogic.gdx.Game;

/**
 * Created by nyeinchanaung on 4/11/2018.
 */

public class BSGame extends Game {

    protected ActivityConnector activityConnector;

    public interface ActivityConnector {
        void connectToActivity();
    }

    public BSGame(ActivityConnector activityConnector){
        this.activityConnector = activityConnector;
    }

    @Override
    public void create() {

    }

    public ActivityConnector getActivityConnector() {
        return this.activityConnector;
    }

}
