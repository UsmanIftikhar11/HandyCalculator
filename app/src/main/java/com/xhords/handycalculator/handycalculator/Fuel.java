package com.xhords.handycalculator.handycalculator;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

public class Fuel extends Fragment {

    Button fuel_moreINfo ;

    EditText et_trip_dist , et_fuel_effi , et_gas_price ;
    TextView txt_dist_imperical , txt_fuel_effi_imperical , txt_gas_price_imperical , txt_journey_cost_imperical ;
    TextView txt_dist_metric , txt_fuel_effi_metric , txt_gas_price_metric , txt_journey_cost_metric ;
    MaterialSpinner spinner , spinner1 , spinner2;
    ImageButton fuel_img_btn;
    public double Et_Trip_dist , Et_Fuel_Effi , Et_Gas_Price ;
    public double dist_km , dist_miles , gas_price_litre , gas_price_gallon , fuel_effi_litre , fuel_effi_gallon ;
    public double journey_cost_value ;
    public double mile_conversion = 0.621371 , gas_conversion = 0.264172 , fuel_conversion = 0.264172 ;

    AdView fuel_adView ;

    NestedScrollView fuel_scroll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fuel, container, false);

        fuel_img_btn = rootView.findViewById(R.id.fuel_img_btn);
        et_trip_dist = rootView.findViewById(R.id.trip_dist);
        et_fuel_effi = rootView.findViewById(R.id.fuel_effi);
        et_gas_price = rootView.findViewById(R.id.gas_price);
        txt_dist_imperical = rootView.findViewById(R.id.txt_dist_imperical);
        txt_fuel_effi_imperical = rootView.findViewById(R.id.txt_fuel_effi_imperical);
        txt_gas_price_imperical = rootView.findViewById(R.id.txt_gas_price_imperical);
        txt_journey_cost_imperical = rootView.findViewById(R.id.txt_journey_cost_imperical);
        txt_dist_metric = rootView.findViewById(R.id.txt_dist_metric);
        txt_fuel_effi_metric = rootView.findViewById(R.id.txt_fuel_effi_metric);
        txt_gas_price_metric = rootView.findViewById(R.id.txt_gas_price_metric);

        fuel_scroll = rootView.findViewById(R.id.fuel_scroll);

        fuel_moreINfo = rootView.findViewById(R.id.fuel_moreInfo);
        fuel_moreINfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , FuelMoreInfo.class);
                startActivity(intent);
            }
        });


        spinner = rootView.findViewById(R.id.trip_dist_spinner);
        spinner.setItems("Select unit","Kilometre", "Mile");
       /* spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });*/

        spinner1 = rootView.findViewById(R.id.fuel_effi_spinner);
        spinner1.setItems("Select unit","Litre", "Gallon");

        spinner2 = rootView.findViewById(R.id.gas_price_spinner);
        spinner2.setItems("Select unit","Litre", "Gallon");

        final RippleBackground rippleBackground = rootView.findViewById(R.id.content);
        rippleBackground.bringToFront();
        rippleBackground.startRippleAnimation();

        fuel_img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(et_trip_dist.getText().length()<1 || et_fuel_effi.getText().length()<1 || et_gas_price.getText().length()<1)
                {
                    Toast toast = Toast.makeText(getActivity() , "  Fill all fields  " , Toast.LENGTH_LONG);

                    View toastview = toast.getView();
                    toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                    // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                    //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                    //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    toast.show();
                   /* Snackbar snackbar = Snackbar.make(view  , "Fill all fields" , Snackbar.LENGTH_LONG);
                    View snackBarview = snackbar.getView();
                    snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.fuel_primary_dark));
                    TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    snackbar.show();*/
                    //Toast.makeText(getActivity() , "Fill in all fields" , Toast.LENGTH_LONG).show();
                }
                else if(et_trip_dist.getText().toString().equals(".") || et_fuel_effi.getText().toString().equals(".") || et_gas_price.getText().toString().equals("."))
                {
                    Toast toast = Toast.makeText(getActivity() , "  Invalid value (.)  " , Toast.LENGTH_LONG);

                    View toastview = toast.getView();
                    toastview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.loan_primary_dark));
                    // TextView textView = (TextView) toast.getView().findViewById(R.id.instant_message);
                    //textView.setTextColor(ContextCompat.getColor(getActivity(),R.color.loan_primary_dark));
                    //textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    toast.show();
                   /* Snackbar snackbar = Snackbar.make(view  , "Invalid fields" , Snackbar.LENGTH_LONG);
                    View snackBarview = snackbar.getView();
                    snackBarview.setBackgroundColor(ContextCompat.getColor(getActivity() , R.color.fuel_primary_dark));
                    TextView textView = (TextView) snackBarview.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP , getResources().getDimension(R.dimen.snackBar_large)/getResources().getDisplayMetrics().density);
                    snackbar.show();*/
                    //Toast.makeText(getActivity() , "Invalid fields" , Toast.LENGTH_LONG).show();
                }
                else
                    {

                       if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK )== Configuration.SCREENLAYOUT_SIZE_NORMAL)
                        {
                            fuel_scroll.scrollTo(0 , fuel_scroll.getMaxScrollAmount());
                        }

                        Et_Trip_dist = Double.valueOf(et_trip_dist.getText().toString());
                        Et_Fuel_Effi = Double.valueOf(et_fuel_effi.getText().toString());
                        Et_Gas_Price = Double.valueOf(et_gas_price.getText().toString());

                        dist_km = Et_Trip_dist ;
                        dist_miles = Et_Trip_dist * mile_conversion ;

                        gas_price_litre = Et_Gas_Price ;
                        gas_price_gallon =Et_Gas_Price * gas_conversion ;

                        fuel_effi_litre = Et_Fuel_Effi ;
                        fuel_effi_gallon = Et_Fuel_Effi * fuel_conversion ;

                        journey_cost_value = (dist_km / fuel_effi_litre) * gas_price_litre ;

                        String distKm_string = String.format("%.2f" , dist_km);
                        String fuel_effiImp_string = String.format("%.2f" , Et_Fuel_Effi);
                        String gasPriceImp_string = String.format("%.2f" , Et_Gas_Price);
                        String distMi_string = String.format("%.2f" , dist_miles);
                        String fuelEffiMetric_string = String.format("%.2f" , fuel_effi_gallon);
                        String gasPriceMetric_string = String.format("%.2f" , gas_price_gallon);
                        String journeyCost_string = String.format("%.2f" , journey_cost_value);

                        txt_dist_imperical.setText(distKm_string + "Km");
                        txt_fuel_effi_imperical.setText(fuel_effiImp_string + "Li");
                        txt_gas_price_imperical.setText(gasPriceImp_string + "Li");
                        txt_dist_metric.setText(distMi_string + "Mi");
                        txt_fuel_effi_metric.setText(fuelEffiMetric_string + "Ga");
                        txt_gas_price_metric.setText(gasPriceMetric_string + "Ga");
                        txt_journey_cost_imperical.setText(journeyCost_string + "$");
                    }


            }
        });

       MobileAds.initialize(getActivity() , getString(R.string.app_id));

        fuel_adView = rootView.findViewById(R.id.fuel_adview);
        AdRequest fuel_adRequest  =new AdRequest.Builder().build();
        fuel_adView.loadAd(fuel_adRequest);

        return rootView;
    }
}
