package com.manju7.admob_demo;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    AdView adView;
    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String android_id = Settings.Secure.getString(getBaseContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        MobileAds.initialize(this , "ca-app-pub-6817573369431351~4160025801");

        adView = (AdView) findViewById(R.id.bAd);

        AdRequest adRequest = new AdRequest.Builder().
                addTestDevice(android_id).
                build();
        adView.loadAd(adRequest);


        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-6817573369431351/8862893427");
        interstitialAd.loadAd(new AdRequest.Builder().addTestDevice(android_id).build());
        interstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                finish();
            }
        });
    }

    public void showInterst(){

        if (interstitialAd.isLoaded()){

            interstitialAd.show();
        } else {

            finish();
        }
    }

    @Override
    public void onBackPressed() {
        showInterst();
    }
}
