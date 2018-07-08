package com.developernca.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.developernca.game.BSGame;
import com.developernca.screen.GamePlayScreen;

/**
 * Created on 5/31/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BonusActor extends StillAtlasActor {

    private boolean isActionAdded;
    private Action sequenceAction;
    private GamePlayScreen gamePlayScreen;
    public Type type;

    public enum Type {
        BIRD_BOMB_LVL0("bomb_bird_lvl0"),
        BIRD_BOMB_LVL1("bomb_bird_lvl1"),
        LIVE("live");

        private String img_name;

        Type(String s) {
            this.img_name = s;
        }

        public String get() {
            return img_name;
        }
    }

    /**
     * Constructor.
     *
     * @param region         TextureRegion for current actor.
     * @param type           Type of bonus.
     * @param gamePlayScreen GamePlayScreen class to communicate between actor and game screen.
     */
    public BonusActor(TextureRegion region, Type type, GamePlayScreen gamePlayScreen) {
        this(0.0f, 0.0f, region, type, gamePlayScreen);
    }

    private BonusActor(float x, float y, TextureRegion region, Type type, GamePlayScreen gamePlayScreen) {
        super(x, y, region);
        this.isActionAdded = false;
        this.type = type;
        this.gamePlayScreen = gamePlayScreen;
        setOriginToScreenCenter();
        Action enlargeAction = Actions.scaleBy(0.2f, 0.2f, 0.5f);
        Action smallAction = Actions.scaleBy(-0.2f, -0.2f, 0.5f);
        if (this.type == Type.LIVE) {
            Action moveToAction = Actions.moveTo(BSGame.gw, BSGame.gh, 0.4f);
            Action runAction = Actions.run(this::actionForLiveBonus);
            sequenceAction = Actions.sequence(enlargeAction, smallAction, moveToAction, runAction);
        } else {
            Action runAction = Actions.run(this::actionForBombBonus);
            sequenceAction = Actions.sequence(enlargeAction, smallAction, runAction);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!isActionAdded) {
            addAction(sequenceAction);
            isActionAdded = true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    private void actionForLiveBonus() {
        remove();
        gamePlayScreen.increaseLive();
    }

    private void actionForBombBonus() {
        remove();
        gamePlayScreen.applyBonusOnBird(type);
    }
}
