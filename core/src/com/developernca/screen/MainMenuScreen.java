package com.developernca.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.developernca.actor.CloudActor;
import com.developernca.game.BSGame;

/**
 * Created on 4/15/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class MainMenuScreen extends BSScreen {

    CloudActor cloudActor1, cloudActor2, cloudActor3;
    Stage midStage;// for animation
    // Stage uiStage; // for menu items

    public MainMenuScreen(BSGame game) {
        super(game);
        // initialize stages
        midStage = new Stage();
        // initialize actors
        // initialize clouds
        cloudActor1 = new CloudActor(0, 0, game.atlas1.findRegion("cloud1"));
        cloudActor2 = new CloudActor(0, 0, game.atlas1.findRegion("cloud2"));
        cloudActor3 = new CloudActor(0, 0, game.atlas1.findRegion("cloud3"));
        // add actors to stage
        midStage.addActor(cloudActor1);
        midStage.addActor(cloudActor2);
        midStage.addActor(cloudActor3);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl20.glClearColor(24 / 255.0f, 220 / 255.0f, 255 / 255.0f, 1.0f);// rgba(24, 220, 255,1.0)
        midStage.act(delta);
        midStage.draw();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Inside td => MainMenuScreen...");
        return true;
    }
}
