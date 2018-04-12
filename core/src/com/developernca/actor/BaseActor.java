package com.developernca.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BaseActor extends Actor {

    protected float x;
    protected float y;

    protected BaseActor(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSelfOriginToCenter() {

    }

    public void setOriginToScreenCenter() {

    }

}
