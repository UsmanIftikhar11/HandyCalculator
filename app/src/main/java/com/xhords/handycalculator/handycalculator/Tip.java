package com.xhords.handycalculator.handycalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.shawnlin.numberpicker.NumberPicker;
import com.skyfishjy.library.RippleBackground;

/**
 * Created by mAni on 27/07/2017.
 */

public class Tip extends Fragment {

    public float bill_amount , tip_percent , no_of_people ;
    EditText bill_amount_value ;
    NumberPicker Tip_Percent , No_Of_People ;
    ImageButton tip_img_btn ;
    TextView total_per_person , bill_per_person , tip_per_person ;
    public float tip_value , indivdual_bill_with_tip , individual_bill , individual_tip;
    Snackbar snackbar ;
    String  every_one_pays , per_person_bill , per_person_tip ;
    Button tip_moreInfo ;
    NestedScrollView tip_scroll ;

    AdView tip_adView ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tip, container, false);

        final RippleBackground rippleBackground = rootView.findViewById(R.id.content);
        rippleBackground.bringToFront();
        rippleBackground.startRippleAnimation();

        bill_amount_value = rootView.findViewById(R.id.bill_amount);
        Tip_Percent = rootView.findViewById(R.id.tip_percent);
        No_Of_People = rootView.findViewById(R.id.no_of_people);
        tip_img_btn = rootView.findViewById(R.id.tip_img_btn);
        total_per_person = rootView.findViewById(R.id.total_per_person);
        bill_per_person = rootView.findViewById(R.id.bill_per_person);
        tip_per_person =rootView.findViewById(R.id.tip_per_person);
        tip_moreInfo = rootView.findViewById(R.id.tip_moreInfo);

        tip_scroll = rootView.findViewById(R.id.tip_scroll);

        tip_moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , TipMoreInfo.class);
                startActivity(intent);
            }
        });

        tip_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // bill_amount = Float.parseFloat(bill_amount_value);



                if(bill_amount_value.getText().length()<1)
                {
                   // Toast.makeText(getActivity() , "Fill in all fields" , Toast.LENGTH_LONG).show();

                    Toast toast = Toast.makeText(getActivity() , "  Enter total bill  " , Toast.LENGTH_LONG);

                    View toastview = toast.getView();
                    toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                    // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                    //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                    //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    toast.show();
                    /*Snackbar snackbar = Snackbar.make(view  , "Fill all fields" , Snackbar.LENGTH_LONG);
                    View snackBarview = snackbar.getView();
                    snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.tip_primary_dark));
                    TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    snackbar.show();*/


                }
                else  if(bill_amount_value.getText().toString().equals("."))
                {
                    Toast toast = Toast.makeText(getActivity() , "  Invalid value (.)  " , Toast.LENGTH_LONG);

                    View toastview = toast.getView();
                    toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                    // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                    //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                    //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    toast.show();
                   //Toast.makeText(getActivity() , String.valueOf(getResources().getDimension(R.dimen.snackBar_large)) , Toast.LENGTH_LONG).show();
                    /*Snackbar snackbar = Snackbar.make(view  , "Invalid field" , Snackbar.LENGTH_LONG);
                    View snackBarview = snackbar.getView();
                    snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.tip_primary_dark));
                    TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    snackbar.show();*/
                }
                else
                    {
                        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK )== Configuration.SCREENLAYOUT_SIZE_NORMAL)
                        {
                            tip_scroll.scrollTo(0 , tip_scroll.getMaxScrollAmount());
                        }

                        bill_amount = Float.valueOf(bill_amount_value.getText().toString());
                        tip_percent = Tip_Percent.getValue();
                        no_of_people = No_Of_People.getValue();

                        tip_value = (tip_percent/100)*bill_amount;
                        indivdual_bill_with_tip = (tip_value + bill_amount) / no_of_people ;
                        individual_bill = bill_amount / no_of_people ;
                        individual_tip = tip_value / no_of_people;

                       /* String.format(every_one_pays, "%.2f" , indivdual_bill_with_tip );
                        String.format(per_person_bill, "%.2f" , individual_bill );
                        String.format(per_person_tip, "%.2f" , individual_tip );*/

                        String s = String.format("%.2f" , indivdual_bill_with_tip);
                        String t = String.format("%.2f" , individual_bill);
                        String u = String.format("%.2f" , individual_tip);


                        total_per_person.setText("$" + s);
                        bill_per_person.setText("$" + t);
                        tip_per_person.setText("$" + u);
                    }



            }
        });

        MobileAds.initialize(getActivity() , getString(R.string.app_id));

        tip_adView = rootView.findViewById(R.id.tip_adview);
        AdRequest tip_adRequest  =new AdRequest.Builder().build();
        tip_adView.loadAd(tip_adRequest);

      //  Log.d("tip" , "tip started");


        return rootView;
    }
}
