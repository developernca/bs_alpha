package com.developernca.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.developernca.actor.BirdActor;
import com.developernca.actor.BladeActor;
import com.developernca.actor.PlayerLiveActor;
import com.developernca.actor.TouchAreaActor;
import com.developernca.game.BSGame;
import com.developernca.utility.BSUtils;

import java.util.ArrayList;

/**
 * Created on 4/21/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class GamePlayScreen extends BSScreen {

    private Stage bgStage;
    private Stage gameStage;
    private Stage uiStage;
    private BirdActor birdActor;
    private TouchAreaActor touchAreaActor;
    private BladeActor bladeActor;
    private int actualYTouchPos;
    private int bladeHalfWidth;
    private ArrayList<BirdActor> birdList;
    private Label lblHighScore;
    private String scoreDefaultTxt;
    private PlayerLiveActor playerLiveActor;

    public GamePlayScreen(BSGame game) {
        super(game);
        // initialize stages
        bgStage = new Stage();
        gameStage = new Stage();
        uiStage = new Stage();
        // initialize actors
        // initialize clouds
        game.setupCloudActorList(bgStage);
        birdActor = new BirdActor(150.0f, 600.0f, 0.06f, new TextureAtlas(Gdx.files.internal(BSGame.as.baf + "/lvl1_bird_atlas.txt")));
        // initialize touch area
        touchAreaActor = new TouchAreaActor(0.0f, 300.0f, Gdx.files.internal(BSGame.as.baf + "/touch_area.png"));
        // label high score
        scoreDefaultTxt = game.i18NBundle.get("score") + " : " + String.format("%04d", 0);
        lblHighScore = BSUtils.makeLabel(scoreDefaultTxt, game.ttfName, Color.BLACK, (int) BSGame.as.pt(18.0f));
        lblHighScore.setPosition(0.0f, BSGame.gh - lblHighScore.getHeight());
        // player live actor
        playerLiveActor = new PlayerLiveActor(0, 0, game.atlas1.findRegion("live"));
        // blade actor
        bladeActor = new BladeActor(0.0f, BSGame.gh, game.atlas1.findRegion("blade_lvl1"));
        bladeHalfWidth = (int) bladeActor.getWidth() / 2;
        // bird actors
        birdList = new ArrayList<>();
        birdList.add(birdActor);
        // add actors to stage
        // game stage
        gameStage.addActor(birdActor);
        gameStage.addActor(touchAreaActor);
        gameStage.addActor(bladeActor);
        // ui stage
        uiStage.addActor(lblHighScore);
        uiStage.addActor(playerLiveActor);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update();
        bgStage.act(delta);
        bgStage.draw();
        gameStage.act(delta);
        gameStage.draw();
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            game.setScreen(BSGame.menuScreen);
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        actualYTouchPos = BSGame.gh - screenY;
        if (touchAreaActor.isTouched(screenX, actualYTouchPos) && bladeActor.canSpawn()) {
            bladeActor.spawn(screenX - bladeHalfWidth, actualYTouchPos);
        }
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private void update() {
        if (checkBladeAndActorOverlap()) {

        }
    }

    private boolean checkBladeAndActorOverlap() {
        for (BirdActor birdActor : birdList) {
            if (birdActor.isCircleOverlap(bladeActor.boundaryCircle)) {
                return true;
            }
        }
        return false;
    }
}
