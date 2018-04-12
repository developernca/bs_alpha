package com.developernca;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.developernca.game.GameMain;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class GameMainActivity extends BSGameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        View gameView = initializeForView(new GameMain(andrSpec, this::connectToActivity), config);
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.idGameMainScreens);
        rl.addView(gameView);
        // initialize ad mob
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        AdView adv = (AdView) findViewById(R.id.idAdvGameMain);
        AdRequest adr = new AdRequest.Builder().build();
        adv.loadAd(adr);
        adv.loadAd(adr);
    }

    public void connectToActivity() {
        System.out.println("Inside connectToActivity() method...");
    }
}
