package com.developernca.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.developernca.actor.CloudActor;
import com.developernca.actor.GameTitleActor;
import com.developernca.actor.StillAtlasActor;
import com.developernca.game.BSGame;

/**
 * Created on 4/15/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class MainMenuScreen extends BSScreen {

    CloudActor cloudActor1, cloudActor2, cloudActor3, cloudActor4;// clouds
    StillAtlasActor playButtonActor;// menu buttons
    GameTitleActor gameTitleActor; // game name text
    Stage midStage;// for animation
    Stage uiStage; // for menu items

    public MainMenuScreen(BSGame game) {
        super(game);
        // initialize stages
        midStage = new Stage();
        uiStage = new Stage();
        // initialize actors
        // initialize clouds
        cloudActor1 = new CloudActor(0, 0, game.atlas1.findRegion("cloud1"));
        cloudActor2 = new CloudActor(0, 0, game.atlas1.findRegion("cloud2"));
        cloudActor3 = new CloudActor(0, 0, game.atlas1.findRegion("cloud3"));
        cloudActor4 = new CloudActor(0, 0, game.atlas1.findRegion("cloud4"));
        // initialize menu butttons
        TextureRegion playBtnRegion = game.atlas1.findRegion("play_btn");
        playButtonActor = new StillAtlasActor(0, 0, playBtnRegion);
        playButtonActor.setSize(playBtnRegion.getRegionWidth(), playBtnRegion.getRegionHeight());
        playButtonActor.setSelfOriginToCenter();
        playButtonActor.setOriginToScreenCenter();
        // initialize title
        gameTitleActor = new GameTitleActor(0, 0, game.atlas1.findRegion("game_name"));
        gameTitleActor.setY(playButtonActor.getY() + playButtonActor.getHeight() + BSGame.as.pt(30.0f));
        // add actors to stage
        // mid stage
        midStage.addActor(cloudActor1);
        midStage.addActor(cloudActor2);
        midStage.addActor(cloudActor3);
        midStage.addActor(cloudActor4);
        // ui stage
        uiStage.addActor(gameTitleActor);
        uiStage.addActor(playButtonActor);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl20.glClearColor(24 / 255.0f, 220 / 255.0f, 255 / 255.0f, 1.0f);// rgba(24, 220, 255,1.0)
        midStage.act(delta);
        midStage.draw();
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Inside td => MainMenuScreen...");
        return true;
    }
}
