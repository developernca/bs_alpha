package com.developernca;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.annotation.Nullable;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.developernca.utility.AndroidSpec;

import java.util.Locale;

/**
 * Base activity and it is not registered in manifest.
 * Created on 4/12/2018.
 *
 * @author Nyein Chan Aung
 * @since 1.0
 */

@SuppressLint("Registered")
public class BSGameActivity extends AndroidApplication {

    protected AndroidSpec andrSpec;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDeviceSpecData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    private void setDeviceSpecData() {
        // Screen density and asset folder
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
        // Default locale
        Locale loc;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList ll = getResources().getConfiguration().getLocales();
            loc = ll.get(0);
        } else {
            loc = getResources().getConfiguration().locale;
        }
        andrSpec = new AndroidSpec(assetFolder, androidDpi, loc);
    }
}
