package com.developernca.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.developernca.actor.MenuButtonActor;
import com.developernca.actor.GameTitleActor;
import com.developernca.game.BSGame;
import com.developernca.utility.BSUtils;


/**
 * Created on 4/15/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class MainMenuScreen extends BSScreen {
    private MenuButtonActor playButtonActor;// menu buttons
    private Stage midStage;// for animation
    private Stage uiStage; // for menu items
    private Label lblCatcherLevelTitle;
    private Label lblCatcherLevel;
    private Label lblHighScore;
    private GameTitleActor gameTitleActor;
    /**
     * To determine whether the screen is started by {@link SplashScreen}
     * or other screens.
     */
    private boolean screenShowAsNew;

    public MainMenuScreen(BSGame game) {
        super(game);
        // the screen already started
        screenShowAsNew = true;
        // initialize stages
        midStage = new Stage();
        uiStage = new Stage();
        // initialize actors
        // initialize clouds
        game.setupCloudActorList(midStage);
        // initialize menu buttons
        playButtonActor = new MenuButtonActor(0, 0, game.atlas1.findRegion("play_btn"));
        // initialize title
        gameTitleActor = new GameTitleActor(0, 0, game.atlas1.findRegion("game_name"));
        // initialize catcher level, high score and level title Labels
        lblCatcherLevel = BSUtils.makeLabel(getCatcherLevel(),
                game.ttfName, Color.PURPLE, (int) BSGame.as.pt(30.0f));
        lblCatcherLevel.setPosition(BSGame.centerX - lblCatcherLevel.getWidth() / 2, BSGame.as.pt(15));

        lblCatcherLevelTitle = BSUtils.makeLabel(game.i18NBundle.get("catcher_level_title"), game.ttfName, Color.BLACK, (int) BSGame.as.pt(35.0f));
        float levelTitleYPos = lblCatcherLevel.getY() + lblCatcherLevel.getHeight() + BSGame.as.pt(25.0f);
        lblCatcherLevelTitle.setPosition(BSGame.centerX - lblCatcherLevelTitle.getWidth() / 2, levelTitleYPos);

        lblHighScore = BSUtils.makeLabel(String.valueOf(BSGame.highScore), game.ttfName, Color.BLACK, (int) BSGame.as.pt(20.0f));
        float highScoreYPos = lblCatcherLevelTitle.getY() + lblCatcherLevelTitle.getHeight() + BSGame.as.pt(20.0f);
        lblHighScore.setPosition(BSGame.centerX - lblHighScore.getWidth() / 2, highScoreYPos);
        // add actors to stage
        // ui stage
        uiStage.addActor(gameTitleActor);
        uiStage.addActor(playButtonActor);
        uiStage.addActor(lblCatcherLevelTitle);
        uiStage.addActor(lblCatcherLevel);
        uiStage.addActor(lblHighScore);
    }

    /**
     * Get catcher level text. Total 9 levels.
     *
     * @return current catcher
     * level.
     */
    private String getCatcherLevel() {
        int score = game.pref.getInteger(BSGame.H_SCORE, 0);
        if (score >= 0 && score <= 10) {
            return game.i18NBundle.get("catcher_level_hobbyist");
        } else if (score > 10 && score <= 20) {
            return game.i18NBundle.get("catcher_level_beginner");
        } else if (score > 21 && score <= 30) {
            return game.i18NBundle.get("catcher_level_normal");
        } else if (score > 31 && score <= 40) {
            return game.i18NBundle.get("catcher_level_moderate");
        } else if (score > 41 && score <= 50) {
            return game.i18NBundle.get("catcher_level_experienced");
        } else if (score > 51 && score <= 60) {
            return game.i18NBundle.get("catcher_level_master");
        } else if (score > 61 && score <= 70) {
            return game.i18NBundle.get("catcher_level_pro");
        } else if (score > 61 && score <= 80) {
            return game.i18NBundle.get("catcher_level_ultra");
        } else if (score > 81) {
            return game.i18NBundle.get("catcher_level_ultimate");
        }
        return null;
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void pause() {
        super.pause();
        BSGame.playBtnSound.stop();
    }

    @Override
    public void show() {
        if (screenShowAsNew) {
            super.show();
            playButtonActor.doPlayBtnAnim();
            screenShowAsNew = false;
        } else {
            Gdx.input.setCatchBackKey(false);
            lblHighScore.setText(String.valueOf(game.pref.getInteger(BSGame.H_SCORE, 0)));
            lblCatcherLevel.setText(getCatcherLevel());
        }
        playButtonActor.setOriginToScreenCenter();
        playButtonActor.setSelfOriginToCenter();
        gameTitleActor.setY(playButtonActor.getY() + playButtonActor.getHeight() + BSGame.as.pt(30.0f));
        game.setupCloudActorList(midStage);
        Gdx.input.setInputProcessor(this);
        BSGame.gameUiBgMusic.setVolume(0.5f);
        BSGame.gameUiBgMusic.setLooping(true);
        BSGame.gameUiBgMusic.play();
    }

    @Override
    public void hide() {
        super.hide();
        BSGame.playBtnSound.stop();
        BSGame.gameUiBgMusic.stop();
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
        if (playButtonActor.isTouched(screenX, BSGame.gh - screenY)) {
            // Action flow => [rotate and move] with sound play and turn to next screen
            Action rotation = Actions.rotateBy(-360, 0.5f);
            Action move = Actions.moveTo(BSGame.gw, playButtonActor.getY(), 0.5f);
            Action sound = Actions.run(() -> BSGame.playBtnSound.play(1.0f));
            Action screenChange = Actions.run(() -> game.setScreen(new GamePlayScreen(game)));
            playButtonActor.addAction(Actions.sequence(Actions.parallel(Actions.sequence(rotation, move), sound),
                    screenChange));
        }
        return true;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}