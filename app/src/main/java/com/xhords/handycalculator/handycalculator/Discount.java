package com.xhords.handycalculator.handycalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.skyfishjy.library.RippleBackground;

/**
 * Created by mAni on 04/08/2017.
 */



public class Discount extends Fragment {

    Button discount_moreInfo;
    EditText et_total_amount , et_tax_percent ;
    RangeBar discount_percent ;
    CheckBox include_tax ;
    TextView txt_total_amount_value , txt_actual_amount , txt_discount_amount , txt_tax_amount ;
    ImageButton discount_img_btn ;
    public float Et_Total_Amount , Et_Tax_Percent , Discount_Percent ;
    public float after_discount_price , after_tax_price ;
    public float actual_discount_amount , actual_tax_amount;

    AdView discount_adView;

    NestedScrollView discount_scroll ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.discount, container, false);

        final RippleBackground rippleBackground = rootView.findViewById(R.id.content);
        rippleBackground.bringToFront();
        rippleBackground.startRippleAnimation();

        discount_moreInfo = rootView.findViewById(R.id.discount_moreInfo);
        et_total_amount = rootView.findViewById(R.id.total_amount);
        et_tax_percent = rootView.findViewById(R.id.tax_percent);
        discount_percent = rootView.findViewById(R.id.discount_percent);
        include_tax = rootView.findViewById(R.id.checkbox_tax);
        txt_total_amount_value = rootView.findViewById(R.id.total_amount_value);
        txt_actual_amount = rootView.findViewById(R.id.actual_amount);
        txt_discount_amount = rootView.findViewById(R.id.discount_amount);
        txt_tax_amount = rootView.findViewById(R.id.tax_amount);
        discount_img_btn = rootView.findViewById(R.id.discount_img_btn);

        discount_scroll = rootView.findViewById(R.id.discount_scroll);

        discount_moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , DiscountMoreInfo.class);
                startActivity(intent);
            }
        });



        include_tax.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(include_tax.isChecked())
                {
                    et_tax_percent.setVisibility(View.VISIBLE);

                }
                else
                    {
                        et_tax_percent.setVisibility(View.INVISIBLE);
                    }
            }
        });

        discount_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_total_amount.getText().length()<1)
                {

                    Toast toast = Toast.makeText(getActivity() , "  Enter total amount  " , Toast.LENGTH_LONG);

                    View toastview = toast.getView();
                    toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                    // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                    //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                    //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    toast.show();
                    /*Snackbar snackbar = Snackbar.make(view  , "Fill all fields" , Snackbar.LENGTH_LONG);
                    View snackBarview = snackbar.getView();
                    snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.discount_primary_dark));
                    TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    snackbar.show();*/
                    // Toast.makeText(getActivity() , "Fill in all fields" , Toast.LENGTH_LONG).show();


                }
                else  if(et_total_amount.getText().toString().equals("."))
                {

                    Toast toast = Toast.makeText(getActivity() , "  Invalid value (.)  " , Toast.LENGTH_LONG);

                    View toastview = toast.getView();
                    toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                    // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                    //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                    //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    toast.show();
                    /*Snackbar snackbar = Snackbar.make(view  , "Invalid field" , Snackbar.LENGTH_LONG);
                    View snackBarview = snackbar.getView();
                    snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.discount_primary_dark));
                    TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    snackbar.show();*/
                    // Toast.makeText(getActivity() , "Invalid field" , Toast.LENGTH_LONG).show();
                }
                else
                    {
                        Et_Total_Amount = Float.valueOf(et_total_amount.getText().toString());

                        Discount_Percent = Float.parseFloat(discount_percent.getRightPinValue());

                        if(include_tax.isChecked())
                        {
                            if(et_tax_percent.getText().length()<1)
                            {

                                Toast toast = Toast.makeText(getActivity() , "  Fill all fields  " , Toast.LENGTH_LONG);

                                View toastview = toast.getView();
                                toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                                // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                                //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                                //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                                toast.show();
                                /*Snackbar snackbar = Snackbar.make(view  , "Fill all fields" , Snackbar.LENGTH_LONG);
                                View snackBarview = snackbar.getView();
                                snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.discount_primary_dark));
                                TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                                snackbar.show();*/
                                //Toast.makeText(getActivity() , "Fill in all fields" , Toast.LENGTH_LONG).show();


                            }
                            else  if(et_tax_percent.getText().toString().equals("."))
                            {


                                Toast toast = Toast.makeText(getActivity() , "  Invalid value (.)  " , Toast.LENGTH_LONG);

                                View toastview = toast.getView();
                                toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                                // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                                //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                                //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                                toast.show();
                                /*Snackbar snackbar = Snackbar.make(view  , "Invalid fields" , Snackbar.LENGTH_LONG);
                                View snackBarview = snackbar.getView();
                                snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.discount_primary_dark));
                                TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                                snackbar.show();*/
                                // Toast.makeText(getActivity() , "Invalid field" , Toast.LENGTH_LONG).show();
                            }
                            else {

                                if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK )== Configuration.SCREENLAYOUT_SIZE_NORMAL)
                                {
                                    discount_scroll.scrollTo(0 , discount_scroll.getMaxScrollAmount());
                                }
                           Et_Tax_Percent = Float.valueOf(et_tax_percent.getText().toString());

                                after_discount_price = Et_Total_Amount - (Et_Total_Amount*(Discount_Percent/100));
                                actual_discount_amount = Et_Total_Amount - after_discount_price ;

                                after_tax_price = after_discount_price + (Et_Total_Amount*(Et_Tax_Percent/100));
                                //actual_tax_amount = Et_Total_Amount - after_tax_price ;
                                actual_tax_amount = (Et_Tax_Percent/100) *Et_Total_Amount ;

                                String totalAmount_string = String.format("%.2f" , after_tax_price);
                                String actualAmount_string = String.format("%.2f" , Et_Total_Amount);
                                String tax_string = String.format("%.2f" , actual_tax_amount);
                                String discountAmount_string = String.format("%.2f" , actual_discount_amount);

                                txt_total_amount_value.setText(totalAmount_string +"$" );
                                txt_actual_amount.setText(actualAmount_string + "$");
                                txt_tax_amount.setText(tax_string + "$");
                                txt_discount_amount.setText(discountAmount_string + "$");
                           }

                        }

                        else {

                            if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK )== Configuration.SCREENLAYOUT_SIZE_NORMAL)
                            {
                                discount_scroll.scrollTo(0 , discount_scroll.getMaxScrollAmount());
                            }


                            after_discount_price = Et_Total_Amount - (Et_Total_Amount * (Discount_Percent / 100));
                            actual_discount_amount = Et_Total_Amount - after_discount_price;

                            String totalAmount_string = String.format("%.2f", after_discount_price);
                            String actualAmount_string = String.format("%.2f", Et_Total_Amount);
                            String discountAmount_string = String.format("%.2f", actual_discount_amount);

                            txt_total_amount_value.setText(totalAmount_string + "$");
                            txt_actual_amount.setText(actualAmount_string + "$");
                            txt_tax_amount.setText("0.0$");
                            txt_discount_amount.setText(discountAmount_string + "$");
                        }
                    }



            }
        });



        MobileAds.initialize(getActivity() , getString(R.string.app_id));

        discount_adView = rootView.findViewById(R.id.discount_adview);
        AdRequest discount_adRequest  =new AdRequest.Builder().build();
        discount_adView.loadAd(discount_adRequest);


        //Log.d("discount" , "discount started");

        return rootView;

    }

}
