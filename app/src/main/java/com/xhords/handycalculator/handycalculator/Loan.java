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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.skyfishjy.library.RippleBackground;

/**
 * Created by mAni on 27/07/2017.
 */

public class Loan extends Fragment {

    Button loan_moreInfo ;

    EditText loan_amount_edit , loan_term_edit , intrest_rate_edit ;
    RadioButton radio_month , radio_year ;
    TextView total_payback , total_intrest , monthly_payback , principle ;
    ImageButton loan_img_btn ;
    MaterialSpinner spinner ;
    public float Loan_Amount1 , Loan_Term , Intrest_Rate ;
    public float intres_value , total_payback_calulated , total_intrest_calculated , monthly_payback_calculated , monthly_loan_term;
    public float time ;
    public boolean year = false , month = false , nothing =true ;

    AdView loan_adView ;

    NestedScrollView loan_scroll ;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.loan, container, false);

        final RippleBackground rippleBackground = rootView.findViewById(R.id.content);
        rippleBackground.bringToFront();
        rippleBackground.startRippleAnimation();

        loan_amount_edit = rootView.findViewById(R.id.txt_loan_amount);
        loan_term_edit = rootView.findViewById(R.id.loan_term);
        intrest_rate_edit = rootView.findViewById(R.id.intrest_rate);
        total_payback = rootView.findViewById(R.id.total_payback);
        total_intrest = rootView.findViewById(R.id.total_intrest);
        monthly_payback = rootView.findViewById(R.id.monthly_payback);
        loan_img_btn = rootView.findViewById(R.id.loan_img_btn);
        principle = rootView.findViewById(R.id.principle);

        loan_scroll = rootView.findViewById(R.id.loan_scroll);

        loan_moreInfo = rootView.findViewById(R.id.loan_moreInfo);
        loan_moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , LoanMoreInfo.class);
                startActivity(intent);
            }
        });

         spinner = rootView.findViewById(R.id.spinner);
        spinner.setItems("Select Term" , "Year", "Month");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if(item.toString()=="Year")
                {
                    year = true;
                    month = false ;
                    nothing = false;
                }
                else if (item.toString()=="Month")
                {
                    month = true ;
                    year = false ;
                    nothing = false;
                }
                else if(item.toString()=="Select Term")
                {
                    nothing = true ;
                    month = false;
                    year = false;
                }
            }
        });

        loan_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(loan_amount_edit.getText().length()<1 || loan_term_edit.getText().length()<1 || intrest_rate_edit.getText().length()<1)
                {
                    //Snackbar snackbar = Snackbar.make(view  , "Fill all fields" , Snackbar.LENGTH_LONG);
                    Toast toast = Toast.makeText(getActivity() , "  Fill all fields  " , Toast.LENGTH_LONG);

                    View toastview = toast.getView();
                    toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                   // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                    //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                    //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    toast.show();
                    // Toast.makeText(getActivity() , "Fill in all fields" , Toast.LENGTH_LONG).show();
                }
                else if(loan_amount_edit.getText().toString().equals(".") || loan_term_edit.getText().toString().equals(".") || intrest_rate_edit.getText().toString().equals("."))
                {

                    Toast toast = Toast.makeText(getActivity() , "  Invalid field (.)  " , Toast.LENGTH_LONG);

                    View toastview = toast.getView();
                    toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                    // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                    //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                    //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    toast.show();
                   /* Snackbar snackbar = Snackbar.make(view  , "  Invalid value (.)  " , Snackbar.LENGTH_LONG);
                    View snackBarview = snackbar.getView();
                    snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                    TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    snackbar.show();*/

                    //Toast.makeText(getActivity() , "Invalid fields" , Toast.LENGTH_LONG).show();
                }
                else
                    {
                        Loan_Amount1 = Float.valueOf(loan_amount_edit.getText().toString());
                        Loan_Term = Float.valueOf(loan_term_edit.getText().toString());
                        Intrest_Rate = Float.valueOf(intrest_rate_edit.getText().toString());

                        intres_value = Intrest_Rate/100 ;

                        if (year == true) {

                            if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK )== Configuration.SCREENLAYOUT_SIZE_NORMAL)
                            {
                                loan_scroll.scrollTo(0 , loan_scroll.getMaxScrollAmount());
                            }


                            total_payback_calulated = Loan_Amount1 * (1 + (intres_value * Loan_Term));
                            total_intrest_calculated = total_payback_calulated - Loan_Amount1;
                            monthly_payback_calculated = total_payback_calulated / Loan_Term;

                            String Principle_string = String.format("%.2f" , Loan_Amount1);
                            String totalPayback_string = String.format("%.2f" , total_payback_calulated);
                            String totalIntrest_string = String.format("%.2f" , total_intrest_calculated);
                            String monthlyPayback_string = String.format("%.2f" , monthly_payback_calculated);

                            principle.setText("$" + Principle_string);
                            total_payback.setText("$" + totalPayback_string);
                            total_intrest.setText("$" + totalIntrest_string);
                            monthly_payback.setText("$" + monthlyPayback_string);

                        } else if (month == true) {

                            if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK )== Configuration.SCREENLAYOUT_SIZE_NORMAL)
                            {
                                loan_scroll.scrollTo(0 , loan_scroll.getMaxScrollAmount());
                            }


                            monthly_loan_term = Loan_Term / 12;
                            total_payback_calulated = Loan_Amount1 * (1 + (intres_value * monthly_loan_term));
                            total_intrest_calculated = total_payback_calulated - Loan_Amount1;
                            monthly_payback_calculated = total_payback_calulated / Loan_Term;

                            String Principle_string = String.format("%.2f" , Loan_Amount1);
                            String totalPayback_string = String.format("%.2f" , total_payback_calulated);
                            String totalIntrest_string = String.format("%.2f" , total_intrest_calculated);
                            String monthlyPayback_string = String.format("%.2f" , monthly_payback_calculated);

                            principle.setText("$" + Principle_string);
                            total_payback.setText("$" + totalPayback_string);
                            total_intrest.setText("$" + totalIntrest_string);
                            monthly_payback.setText("$" + monthlyPayback_string);
                        }
                        else
                        {
                           /* Snackbar snackbar = Snackbar.make(view  , "Must select Loan term" , Snackbar.LENGTH_LONG);
                            View snackBarview = snackbar.getView();
                            snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                            TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                            snackbar.show();*/
                            Toast toast = Toast.makeText(getActivity() , "  Must select loan term  " , Toast.LENGTH_LONG);

                            View toastview = toast.getView();
                            toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                            // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                            //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                            //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                            toast.show();
                           // Toast.makeText(getActivity() , "Must select Loan term" , Toast.LENGTH_LONG).show();
                        }

                       /* String Principle_string = String.format("%.2f" , Loan_Amount1);
                        String totalPayback_string = String.format("%.2f" , total_payback_calulated);
                        String totalIntrest_string = String.format("%.2f" , total_intrest_calculated);
                        String monthlyPayback_string = String.format("%.2f" , monthly_payback_calculated);

                        principle.setText("$" + Principle_string);
                        total_payback.setText("$" + totalPayback_string);
                        total_intrest.setText("$" + totalIntrest_string);
                        monthly_payback.setText("$" + monthlyPayback_string);*/
                    }





            }
        });




       MobileAds.initialize(getActivity() , getString(R.string.app_id));

        loan_adView = rootView.findViewById(R.id.loan_adview);
        AdRequest loan_adRequest  =new AdRequest.Builder().build();
        loan_adView.loadAd(loan_adRequest);

       // Log.d("loan" , "loan started");

        return rootView;
    }
}
