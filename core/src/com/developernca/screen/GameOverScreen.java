package com.developernca.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.developernca.actor.StillAtlasActor;
import com.developernca.game.BSGame;
import com.developernca.utility.BSUtils;

import java.util.Map;

/**
 * Created on 5/30/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class GameOverScreen extends BSScreen {
    // data
    private boolean touchedDownOnReplay = false;
    private boolean touchedDownOnMenu = false;
    private boolean touchedDownOnExit = false;
    // actors
    private StillAtlasActor replayButtonActor;
    private StillAtlasActor menuButtonActor;
    private StillAtlasActor exitButtonActor;
    private Table table;
    private Stage uiStage;
    private Stage bgStage;

    public GameOverScreen(BSGame game, Map<String, String> result) {
        super(game);
        // initialize stage
        uiStage = new Stage();
        bgStage = new Stage();
        // initialize background running clouds
        game.setupCloudActorList(bgStage);
        // initialize actors
        StillAtlasActor gameoverTitleActor = new StillAtlasActor(0, 0, BSGame.atlas1.findRegion("game_over"));
        Color textColor = new Color(200.0f / 255.0f, 113.0f / 255.0f, 55.0f / 255.0f, 255.0f / 255.0f);
        // label for score
        Label lblScoreText = BSUtils.makeLabel(game.i18NBundle.get("score"), game.ttfName, textColor, (int) BSGame.as.pt(20.0f));
        Label lblScoreValue = BSUtils.makeLabel(result.get("score"), game.ttfName, textColor, (int) BSGame.as.pt(20.0f));
        // label for high score
        Label lblHiScoreText = BSUtils.makeLabel(game.i18NBundle.get("hi_score"), game.ttfName, textColor, (int) BSGame.as.pt(20.0f));
        Label lblHiScoreValue = BSUtils.makeLabel(result.get("hi_score"), game.ttfName, textColor, (int) BSGame.as.pt(20.0f));
        // buttons init
        replayButtonActor = new StillAtlasActor(0, 0, BSGame.atlas1.findRegion("replay"));
        menuButtonActor = new StillAtlasActor(0, 0, BSGame.atlas1.findRegion("menu"));
        exitButtonActor = new StillAtlasActor(0, 0, BSGame.atlas1.findRegion("exit"));
        // initialize tables
        table = new Table();
        table.setFillParent(true);
        // table.align(Align.center);
        table.add(gameoverTitleActor).pad(BSGame.as.pt(20.0f)).colspan(2);
        table.row();
        table.add(lblScoreText, lblScoreValue);
        table.row();
        table.add(lblHiScoreText, lblHiScoreValue);
        table.row();
        table.add(replayButtonActor).pad(BSGame.as.pt(10.0f));
        table.add(menuButtonActor).pad(BSGame.as.pt(10.0f));
        table.row();
        table.add(exitButtonActor).pad(BSGame.as.pt(10.0f)).colspan(2);
        // add actors to stage
        uiStage.addActor(table);
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        bgStage.act(delta);
        bgStage.draw();
        uiStage.act(delta);
        uiStage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            Gdx.app.exit();
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int actualYTouchPos = BSGame.gh - screenY;
        if (replayButtonActor.isTouched(screenX, actualYTouchPos)) {// touch replay
            replayButtonActor.addAction(Actions.scaleBy(-0.1f, -0.1f));
            touchedDownOnReplay = true;
        } else if (menuButtonActor.isTouched(screenX, actualYTouchPos)) {// touch menu
            menuButtonActor.addAction(Actions.scaleBy(-0.1f, -0.1f));
            touchedDownOnMenu = true;
        } else if (exitButtonActor.isTouched(screenX, actualYTouchPos)) {
            exitButtonActor.addAction(Actions.scaleBy(-0.1f, -0.1f));
            touchedDownOnExit = true;
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (touchedDownOnReplay) {// replay up
            replayButtonActor.addAction(Actions.scaleBy(0.1f, 0.1f));
            touchedDownOnReplay = false;
            // go to play screen
            game.setScreen(new GamePlayScreen(game));
        } else if (touchedDownOnMenu) {// menu up
            menuButtonActor.addAction(Actions.scaleBy(0.1f, 0.1f));
            touchedDownOnMenu = false;
            // go to menu screen
            game.setScreen(BSGame.menuScreen);
        } else if (touchedDownOnExit) {
            Gdx.app.exit();
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (touchedDownOnReplay) {// replay up
            replayButtonActor.addAction(Actions.scaleBy(0.1f, 0.1f));
            touchedDownOnReplay = false;
        } else if (touchedDownOnMenu) {// menu up
            menuButtonActor.addAction(Actions.scaleBy(0.1f, 0.1f));
            touchedDownOnMenu = false;
        } else if (touchedDownOnExit) {
            exitButtonActor.addAction(Actions.scaleBy(0.1f, 0.1f));
            touchedDownOnExit = false;
        }
        return true;
    }
}
