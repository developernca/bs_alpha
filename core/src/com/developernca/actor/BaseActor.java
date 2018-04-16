package com.developernca.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.developernca.game.BSGame;

/**
 * @author Nyein Chan Aung
 * @since 1.0
 */

class BaseActor extends Actor {

    private float x;
    private float y;

    BaseActor(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSelfOriginToCenter() {
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    public void setOriginToScreenCenter() {
        setPosition(BSGame.centerX - getWidth() / 2, BSGame.centerY - getHeight() / 2);
    }



}
