package com.developernca.utility;

import java.util.Locale;

/**
 * Created on 4/11/2018.
 * <p>
 * Android device detail information.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class AndroidSpec {
    /**
     * Get base asset folder name without trailing slash. (--dpi).
     * The variable name baf is short for
     * [base asset folder]
     */
    public String baf;
    public Locale defaultLoc;
    private AndroidDpi androidDpi;

    public AndroidSpec(String baf, AndroidDpi androidDpi, Locale defaultLoc) {
        this.baf = baf;
        this.androidDpi = androidDpi;
        this.defaultLoc = defaultLoc;
    }

    public enum AndroidDpi {
        LDPI, MDPI, HDPI, XHDPI, XXHDPI, XXXHDPI;
    }

    /**
     * Get actual pixel value from logical point value.
     *
     * @param p density independent pixel value
     * @return actual pixel value base on device dpi
     */
    public float pt(float p) {
        switch (androidDpi) {
            case LDPI:
                return p * 0.75f;
            case MDPI:
                return p;
            case HDPI:
                return p * 1.5f;
            case XHDPI:
                return p * 2;
            case XXHDPI:
                return p * 3;
            case XXXHDPI:
                return p * 4;
            default:
                return 0.0f;
        }
    }
}
