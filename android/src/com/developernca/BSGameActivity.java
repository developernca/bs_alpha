package com.developernca;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.developernca.utility.AndroidSpec;

/**
 * Created on 4/12/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

public class BSGameActivity extends AndroidApplication {

    protected AndroidSpec andrSpec;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDeviceSpecData();
    }

    private void setDeviceSpecData() {
        int dpi = (int) getResources().getDisplayMetrics().xdpi;
        String assetFolder;
        AndroidSpec.AndroidDpi androidDpi;
        if (dpi <= 120) {// ldpi
            assetFolder = "ldpi";
            androidDpi = AndroidSpec.AndroidDpi.LDPI;
        } else if (dpi > 120 && dpi <= 160) {
            assetFolder = "mdpi";
            androidDpi = AndroidSpec.AndroidDpi.MDPI;
        } else if (dpi > 160 && dpi <= 240) {
            assetFolder = "hdpi";
            androidDpi = AndroidSpec.AndroidDpi.HDPI;
        } else if (dpi > 240 && dpi <= 320) {
            assetFolder = "xhdpi";
            androidDpi = AndroidSpec.AndroidDpi.XHDPI;
        } else if (dpi > 320 && dpi <= 480) {
            assetFolder = "xxhdpi";
            androidDpi = AndroidSpec.AndroidDpi.XXHDPI;
        } else {
            assetFolder = "xxxhdpi";
            androidDpi = AndroidSpec.AndroidDpi.XXXHDPI;
        }
        andrSpec = new AndroidSpec(assetFolder, androidDpi);
    }
}
