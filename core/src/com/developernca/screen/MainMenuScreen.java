package com.developernca.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.developernca.actor.BladeActor;
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
    BladeActor playButtonActor;// menu buttons
    Sound playBtnClickSound;
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
        // initialize menu buttons
        playButtonActor = new BladeActor(0, 0, game.atlas1.findRegion("play_btn"), true);
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
        // sound for button click
        playBtnClickSound = Gdx.audio.newSound(Gdx.files.internal("audio/sound_playbtn_click.wav"));
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(this);
        // BSGame.playBtnSound.play();
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
        if (playButtonActor.isTouched(screenX, Gdx.graphics.getHeight() - screenY)) {
            Action rotation1 = Actions.rotateBy(-360, 0.5f);
            playButtonActor.addAction(rotation1);
        }
        return true;
    }


}
