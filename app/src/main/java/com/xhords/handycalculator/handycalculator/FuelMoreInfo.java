package com.xhords.handycalculator.handycalculator;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class FuelMoreInfo extends AppCompatActivity {

    TextView fuel_info ;

    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_more_info);

        fuel_info = (TextView)findViewById(R.id.textview_fuel_info);
        fuel_info.setMovementMethod(LinkMovementMethod.getInstance());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this , getString(R.string.app_id));

        AdRequest adRequest  =new AdRequest.Builder().build();

        interstitial = new InterstitialAd(FuelMoreInfo.this);
        interstitial.setAdUnitId(getString(R.string.fuel_interstitial));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function

                Toast.makeText(getApplicationContext() , "Loading Ad" , Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        displayInterstitial();
                    }
                }, 3000);
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
