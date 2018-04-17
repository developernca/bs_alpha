package com.developernca.actor;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.developernca.game.BSGame;


/**
 * Created 4/17/2018
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

class BaseActor extends Actor {

    protected float x;
    protected float y;
    public Circle boundaryCircle;
    private static float occupyX;
    private static float occupyY;

    BaseActor(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set actor origin (anchor) to self center.
     */
    public void setSelfOriginToCenter() {
        setOrigin(getWidth() / 2, getHeight() / 2);
    }

    /**
     * Set actor origin to screen center.
     */
    public void setOriginToScreenCenter() {
        setPosition(BSGame.centerX - getWidth() / 2, BSGame.centerY - getHeight() / 2);
    }

    /**
     * Draw boundary circle for this actor.
     */
    public void drawCircle() {
        boundaryCircle = new Circle(getX(), getY(), getWidth() / 2);
    }

    /**
     * Check overlapping between boundary circle of two actors.
     *
     * @param anotherCircle The circle to be check overlap.
     * @return boolean true on overlap, false on not overlap and both or one of
     * circle is null.
     */
    public boolean isCircleOverlap(Circle anotherCircle) {
        return this.boundaryCircle != null && anotherCircle != null &&
                this.boundaryCircle.overlaps(anotherCircle);
    }

    /**
     * Check this actor is touched by user.
     *
     * @param touchX touch x position in screen.
     * @param touchY touch y position in screen.
     * @return boolean true on touch, otherwise false.
     */
    protected boolean isTouched(int touchX, int touchY) {
        occupyX = getX() + getWidth();
        occupyY = getY() + getHeight();
        return (touchX >= x && touchX <= occupyX)
                && (touchY >= y && touchY <= occupyY);
    }

}

