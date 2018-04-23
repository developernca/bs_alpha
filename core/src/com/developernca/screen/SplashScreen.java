package com.developernca.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.developernca.actor.LogoActor;
import com.developernca.game.BSGame;
import com.developernca.utility.BSUtils;

/**
 * Splash screen.
 * Created on 4/12/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class SplashScreen extends BSScreen implements ActorScreenConnector {

    private LogoActor actorLogo;
    private Label lblTapAnywhere;
    private Stage bgStage;
    private boolean canGotoNextScreen = false;

    public SplashScreen(BSGame game) {
        super(game);
        // initialize stage
        bgStage = new Stage();
        // initialize actors
        // actorLogo
        actorLogo = new LogoActor(0.0f, 0.0f, game.atlas1.findRegion("looper"), true, this);
        actorLogo.setSelfOriginToCenter();
        actorLogo.setOriginToScreenCenter();
        // label [tap anywhere]
        lblTapAnywhere = BSUtils.makeLabel(game.i18NBundle.get("tap_to_continue"), game.ttfName, Color.WHITE, (int) BSGame.as.pt(25.0f));
        lblTapAnywhere.setPosition(BSGame.centerX - lblTapAnywhere.getWidth() / 2, actorLogo.getY() - BSGame.as.pt(50.0f));
        lblTapAnywhere.setVisible(false);
        // add all actors to stage
        bgStage.addActor(actorLogo);
        bgStage.addActor(lblTapAnywhere);

    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        bgStage.act(delta);
        bgStage.draw();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (canGotoNextScreen) {
            BSGame.menuScreen = new MainMenuScreen(game);
            game.setScreen(BSGame.menuScreen);
        }
        return true;
    }

    @Override
    public void connect() {
        lblTapAnywhere.setVisible(true);
        canGotoNextScreen = true;
    }
}
