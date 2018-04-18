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

public class SplashScreen extends BSScreen {

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
        actorLogo = new LogoActor(100.0f, 100.0f, game.atlas1.findRegion("looper"));
        // label [tap anywhere]
        lblTapAnywhere = BSUtils.makeLabel(game.i18NBundle.get("tap_to_continue"), game.ttfName, Color.WHITE, (int) BSGame.as.pt(25.0f));
        lblTapAnywhere.setPosition(BSGame.centerX - lblTapAnywhere.getWidth() / 2, actorLogo.getY() - BSGame.as.pt(50.0f));
        lblTapAnywhere.setVisible(false);
        // add all actors to stage
        bgStage.addActor(actorLogo);
        bgStage.addActor(lblTapAnywhere);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (actorLogo.isLogoAnimationFinished) {
            lblTapAnywhere.setVisible(true);
            canGotoNextScreen = true;
        }
        Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        bgStage.act(delta);
        bgStage.draw();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Inside TD => SplashScreen");
        if (canGotoNextScreen) {
            game.setScreen(new MainMenuScreen(game));
        }
        return true;
    }
}
