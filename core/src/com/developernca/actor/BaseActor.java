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
     * Get center x,y value of current screen related to this actor. Index 0 will be x and 1
     * will be 1 => [x, y].
     *
     * @return a float array
     */
    public float[] getOriginToScreenCenter() {
        return new float[]{BSGame.centerX - getWidth() / 2.0f, BSGame.centerY - getHeight() / 2.0f};
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
    public boolean isTouched(int touchX, int touchY) {
        float occupyX = getX() + getWidth();
        float occupyY = getY() + getHeight();
        return (touchX >= getX() && touchX <= occupyX)
                && (touchY >= getY() && touchY <= occupyY);
    }

    /**
     * Check whether the actor is appearing on the screen or not.
     *
     * @return true if actor is visible on the screen, false otherwise
     */
    public boolean isOnScreen() {
        if ((getX() + getWidth() > BSGame.gw) || (getY() + getHeight() > BSGame.gh)) {// Not visible on screen
            return false;
        }
        return true;
    }

}

