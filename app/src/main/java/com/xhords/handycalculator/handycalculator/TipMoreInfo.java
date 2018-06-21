package com.xhords.handycalculator.handycalculator;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class TipMoreInfo extends AppCompatActivity {

    private InterstitialAd interstitial ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_more_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this , getString(R.string.app_id));

        AdRequest adRequest  =new AdRequest.Builder().build();

        interstitial = new InterstitialAd(TipMoreInfo.this);
        interstitial.setAdUnitId(getString(R.string.tip_interstitial));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {

                Toast.makeText(getApplicationContext() , "Loading Ad" , Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayInterstitial();
                    }
                }, 3000);
                // Call displayInterstitial() function
               // displayInterstitial();
            }
        });
    }

    public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
}
