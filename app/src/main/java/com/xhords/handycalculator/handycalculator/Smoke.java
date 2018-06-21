package com.xhords.handycalculator.handycalculator;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.skyfishjy.library.RippleBackground;


/**
 * Created by mAni on 27/07/2017.
 */

public class Smoke extends Fragment {

    Button smoke_btn ;
    public float pay_for_cig , cig_in_pack , cig_in_day , year_of_smoke ;
    RangeBar rangeBar , rangeBar1  ,rangeBar2 , rangeBar3 ;
    public float cost_for_one , daily_cost , weekly_cost , monthly_cost , yearly_cost , until_now_cost , saved_cost ;
    TextView daily_value , weekly_value , monthly_value , yearly_value , txt_until_now_cost;
    NestedScrollView smoke_scroll ;



    AdView adView;


    ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.smoke, container, false);



      final RippleBackground rippleBackground = rootView.findViewById(R.id.content);
        rippleBackground.bringToFront();
        rippleBackground.startRippleAnimation();

        rangeBar = rootView.findViewById(R.id.pay_fro_cig);
        rangeBar1 = rootView.findViewById(R.id.cig_in_pack);
        rangeBar2 = rootView.findViewById(R.id.cig_in_day);
        rangeBar3 = rootView.findViewById(R.id.year_of_smoking);
        txt_until_now_cost = rootView.findViewById(R.id.until_noe_cost);
        smoke_scroll = rootView.findViewById(R.id.smoke_scroll);
        smoke_btn = rootView.findViewById(R.id.smoke_btn);

        imageButton = rootView.findViewById(R.id.img_btn);
        daily_value = rootView.findViewById(R.id.daily_value);
        weekly_value = rootView.findViewById(R.id.weekly_value);
        monthly_value = rootView.findViewById(R.id.monthly_value);
        yearly_value = rootView.findViewById(R.id.yearly_value);

        smoke_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity() , SmokeQuit.class);
                startActivity(intent);
            }
        });





        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK )== Configuration.SCREENLAYOUT_SIZE_NORMAL)
                {
                    smoke_scroll.scrollTo(0 , smoke_scroll.getMaxScrollAmount());
                }

                pay_for_cig = Float.parseFloat(rangeBar.getRightPinValue());
                cig_in_pack = Float.parseFloat(rangeBar1.getRightPinValue());
                cig_in_day = Float.parseFloat(rangeBar2.getRightPinValue());
                year_of_smoke = Float.parseFloat(rangeBar3.getRightPinValue());

                cost_for_one = pay_for_cig / cig_in_pack ;
                daily_cost = cig_in_day * cost_for_one ;
                weekly_cost = daily_cost * 7 ;
                monthly_cost = daily_cost * 30 ;
                yearly_cost = daily_cost * 365 ;
                until_now_cost = yearly_cost * year_of_smoke ;

                String daily_string = String.format("%.2f" , daily_cost);
                String weekly_string = String.format("%.2f" , weekly_cost);
                String monthly_string = String.format("%.2f" , monthly_cost);
                String yearly_string = String.format("%.2f" , yearly_cost);
                String until_now_string = String.format("%.2f" , until_now_cost);

               daily_value.setText( "$" + daily_string);
                weekly_value.setText("$" + weekly_string);
                monthly_value.setText("$" + monthly_string);
                yearly_value.setText("$" + yearly_string);
                txt_until_now_cost.setText("$" + until_now_string);
               // smoke_scroll.scrollTo(0 , 5000);
               /* pay_for_cig = 0;
                cig_in_pack = 0;
                cig_in_day = 0;
                year_of_smoke = 0;*/
              // Toast.makeText(getActivity() , "Clicked!!!!!!!!!!!!!" , Toast.LENGTH_LONG).show();
              //  imageButton.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.smoke_primary_light));
               /* Toast.makeText(getActivity() , rangeBar.getRightPinValue() , Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity() , rangeBar1.getRightPinValue() , Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity() , rangeBar2.getRightPinValue() , Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity() , rangeBar3.getRightPinValue() , Toast.LENGTH_LONG).show();*/

            }
        });

       MobileAds.initialize(getActivity() , getString(R.string.app_id));

        adView = rootView.findViewById(R.id.adview);
        AdRequest adRequest  =new AdRequest.Builder().build();
        adView.loadAd(adRequest);

//        Log.d("smoke" , "smoke started");

        return rootView;
    }

}
