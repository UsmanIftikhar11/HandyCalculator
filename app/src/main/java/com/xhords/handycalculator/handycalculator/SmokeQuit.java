package com.xhords.handycalculator.handycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import android.os.Handler;

public class SmokeQuit extends AppCompatActivity {

    TextView textView ;

    InterstitialAd interstitialAd;
    private  InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoke_quit);

        textView = (TextView)findViewById(R.id.textview_smoke_quit);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toast.makeText(getApplicationContext() , "Loading Ad" , Toast.LENGTH_SHORT).show();

        MobileAds.initialize(this , getString(R.string.app_id));

        AdRequest adRequest  =new AdRequest.Builder().build();

        interstitial = new InterstitialAd(SmokeQuit.this);
        interstitial.setAdUnitId(getString(R.string.smoke_interstitial));

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
