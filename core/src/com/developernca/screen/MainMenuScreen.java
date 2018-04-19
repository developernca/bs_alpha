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
    private Sound playBtnClickSound;
    private Stage midStage;// for animation
    private Stage uiStage; // for menu items
    private Label lblCatcherLevelTitle;
    private Label lblCatcherLevel;

    public MainMenuScreen(BSGame game) {
        super(game);
        // initialize stages
        midStage = new Stage();
        uiStage = new Stage();
        // initialize actors
        // initialize clouds
        game.setupCloudActorList(midStage);
        // initialize menu buttons
        playButtonActor = new MenuButtonActor(0, 0, game.atlas1.findRegion("play_btn"));
        playButtonActor.setSelfOriginToCenter();
        playButtonActor.setOriginToScreenCenter();
        // initialize title
        GameTitleActor gameTitleActor = new GameTitleActor(0, 0, game.atlas1.findRegion("game_name"));
        gameTitleActor.setY(playButtonActor.getY() + playButtonActor.getHeight() + BSGame.as.pt(30.0f));
        // initialize catcher level and level title Label
        lblCatcherLevel = BSUtils.makeLabel(game.i18NBundle.get("catcher_level_beginner"),
                game.ttfName, Color.PURPLE, (int) BSGame.as.pt(25.0f));
        lblCatcherLevel.setPosition(BSGame.centerX - lblCatcherLevel.getWidth() / 2, BSGame.as.pt(15));

        lblCatcherLevelTitle = BSUtils.makeLabel(game.i18NBundle.get("catcher_level_title"), game.ttfName, Color.BLACK, (int) BSGame.as.pt(28.0f));
        float levelTitleYPos = lblCatcherLevel.getY() + lblCatcherLevel.getHeight() + BSGame.as.pt(15.0f);
        lblCatcherLevelTitle.setPosition(BSGame.centerX - lblCatcherLevelTitle.getWidth() / 2, levelTitleYPos);
        // add actors to stage
        // ui stage
        uiStage.addActor(gameTitleActor);
        uiStage.addActor(playButtonActor);
        uiStage.addActor(lblCatcherLevelTitle);
        uiStage.addActor(lblCatcherLevel);
        // sound for button click
        playBtnClickSound = Gdx.audio.newSound(Gdx.files.internal("audio/sound_playbtn_click.wav"));
    }

    @Override
    public void show() {
        super.show();
        playButtonActor.doPlayBtnAnim();
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
        if (playButtonActor.isTouched(screenX, Gdx.graphics.getHeight() - screenY)) {
            Action rotation1 = Actions.rotateBy(-360, 0.5f);
            playButtonActor.addAction(rotation1);
            BSGame.playBtnSound.play();
        }
        return true;
    }

}
