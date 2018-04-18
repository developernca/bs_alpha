package com.developernca.utility;

import com.badlogic.gdx.graphics.Camera;

/**
 * Use to shake camera.
 * Created on 4/18/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class CameraShaker{
    private float shakeX;
    private float shakeY;
    private int shakeCount = 1;

    public CameraShaker(float shakeX, float shakeY) {
        this.shakeX = shakeX;
        this.shakeY = shakeY;
    }

    /**
     * Start camera shaking.
     *
     * @param camera Camera to shake
     * @return boolean true if camera is being shake, otherwise false
     */
    public boolean shake(Camera camera) {
        if (shakeCount % 2 == 0) {
            shakeX *= (-1);
            shakeY *= (-1);
            camera.translate(shakeX, shakeY, 0.0f);
        }
        if (shakeCount < 25) {
            ++shakeCount;
            return true;
        } else {
            shakeCount = 1;
            return false;
        }
    }

    /**
     * Reset with desire shake value.
     *
     * @param shakeX value to move on x
     * @param shakeY value to move on y
     */
    public void reset(float shakeX, float shakeY) {
        this.shakeX = shakeX;
        this.shakeY = shakeY;
    }
}
