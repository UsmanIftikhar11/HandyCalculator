package com.xhords.handycalculator.handycalculator;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

   private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mtoogle;
    private Toolbar mtoolbar , toolbar;
    private AppBarLayout appBarLayout;
    private TabLayout tab;
    private ViewPager.OnPageChangeListener indicator;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tab = (TabLayout) findViewById(R.id.tabs);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Smoke Calculator");

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mdrawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mtoogle = new ActionBarDrawerToggle(this , mdrawerlayout , R.string.open , R.string.close);

        mdrawerlayout.addDrawerListener(mtoogle);
        mtoogle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tab.getTabAt(0).setIcon(R.drawable.smoking);
        tab.getTabAt(1).setIcon(R.drawable.tip);
        tab.getTabAt(2).setIcon(R.drawable.fuel);
        tab.getTabAt(3).setIcon(R.drawable.loan);
        tab.getTabAt(4).setIcon(R.drawable.discount);


        appBarLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.smoke_primary_dark));
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.smoke_primary_light));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Toast.makeText(getApplicationContext() , tab.getPosition() , Toast.LENGTH_SHORT).show();

              switch (tab.getPosition()) {
                  case 0:
                  toolbar.setTitle("Smoke Calculator");
                  appBarLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.smoke_primary_dark));
                  toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.smoke_primary_light));


                      break;
                  case 1:
                      toolbar.setTitle("Tip Calculator");
                      appBarLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.tip_primary_dark));
                      toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.tip_primary_light));
                      break;
                  case 2:
                      toolbar.setTitle("Fuel Calculator");
                      appBarLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.fuel_primary_dark));
                      toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.fuel_primary_light));
                      break;
                  case 3:
                      toolbar.setTitle("Loan Calculator");
                      appBarLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.loan_primary_dark));
                      toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.loan_primary_light));
                      break;

                  case 4:
                      toolbar.setTitle("Discount Calculator");
                      appBarLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.discount_primary_dark));
                      toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext() , R.color.discount_primary_light));
                      break;

              }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

               // Toast.makeText(getApplicationContext() , tab.getPosition() , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Toast.makeText(getApplicationContext() , tab.getPosition() , Toast.LENGTH_SHORT).show();

            }
        });


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/roboto.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        boolean firstrun1 = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun1", true);
        if (firstrun1)
        {
            new MaterialStyledDialog.Builder(this)
                    .setTitle("Welcome!")
                    .setIcon(R.drawable.logofinal)
                    .setHeaderDrawable(R.drawable.wallpaper)
                    .withIconAnimation(true)
                    .setPositiveText("Ok")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        }
                    })
                    .setDescription("Thank you for installing handy calculator. Your companion in the day to day basic calculation. What can we improve? Your feedback is always welcome.")
                    .show();

            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("firstrun1", false)
                    .commit();
        }


         /*   AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Get the layout inflater
            LayoutInflater inflater = this.getLayoutInflater();

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.custom_dialog, null))
                    // Add action buttons
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // sign in the user ...
                        }
                    });

             builder.create();*/



    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(mtoogle.onOptionsItemSelected(item))
        {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    private void displayselectedscreen (int id)
    {
        switch (id)
        {
            case R.id.nav_ourapps:
                Intent intent = new Intent(MainActivity.this, OurApps.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.nav_feature:
                Intent intent1 = new Intent(MainActivity.this, SuggestFeature.class);
                startActivity(intent1);
                break;
            case R.id.nav_about:
                Intent intent2 = new Intent(MainActivity.this, About.class);
                startActivity(intent2);
                break;
            case R.id.nav_credits:
                Intent intent3 = new Intent(MainActivity.this, Credits.class);
                startActivity(intent3);
                break;
            case R.id.nav_rate:
                //Toast.makeText(getApplicationContext() , getApplicationContext().getPackageName() , Toast.LENGTH_LONG).show();
                startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                break;

            case R.id.nav_exit:
                super.onBackPressed();
                break;
        }
      /* if(id==R.id.nav_ourapps)
        {
            Intent intent = new Intent(MainActivity.this, OurApps.class);
            startActivity(intent);
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displayselectedscreen(id);
        return false;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            // return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    Smoke smoke = new Smoke();
                    return smoke;

                case 1:
                    Tip tip = new Tip();
                    return tip;
                case 2:
                    Fuel fuel = new Fuel();
                    return fuel;
                case 3:
                    Loan loan = new Loan();
                    return  loan;
                case 4:
                    Discount discount = new Discount();
                    return  discount;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }




    }


}
