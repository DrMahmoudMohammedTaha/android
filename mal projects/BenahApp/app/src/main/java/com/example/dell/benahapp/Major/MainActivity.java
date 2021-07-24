package com.example.dell.benahapp.Major;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.font_factory;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
   // private TabLayout tabLayout;
    private ViewPager viewPager;
    public static Typeface face ;
    public static AppCompatActivity app;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

          //  face = Typeface.createFromAsset(getAssets(),"fonts/LGR.otf");
            // Setup Toolbar
            //android:background="?attr/colorPrimary"
            // app:titleTextColor="@color/colorAccent"
            app = this;
 //        toolbar = (Toolbar) findViewById(R.id.toolbar);
//            setSupportActionBar(toolbar);

            visionFactory.fill_names(
                    getResources().getString(R.string.midicine),
                    getResources().getString(R.string.veterinary),
                    getResources().getString(R.string.nursing),
                    getResources().getString(R.string.science),
                    getResources().getString(R.string.education),
                    getResources().getString(R.string.arts),
                    getResources().getString(R.string.agriculture),
                    getResources().getString(R.string.physical),
                    getResources().getString(R.string.law),
                    getResources().getString(R.string.shoubra),
                    getResources().getString(R.string.tech) ,
                    getResources().getString(R.string.engineer) ,
                    getResources().getString(R.string.applied),
                    getResources().getString(R.string.specific));

            ImageView universityimg= (ImageView)findViewById(R.id.universityimg);

            universityimg.setImageResource(R.drawable.benha);

            viewPager = (ViewPager) findViewById(R.id.pager);
            // Assign created adapter to viewPager
            setupViewPager(viewPager);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            //      This method setup all required method for TabLayout with Viewpager
            tabLayout.setupWithViewPager(viewPager);


        }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new studentservicefragment(), "Main page");
        adapter.addFragment(new MainPagefrag(), "Vision");
        viewPager.setAdapter(adapter);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

                private final List<Fragment> mFragmentList = new ArrayList<>();
                private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
            }


    public static TextView tv;

    public void showMission(View view){

        font_factory.stylish_it1((TextView)findViewById(R.id.missonData),this);

        Button b = (Button)view;
        String buttonText = b.getText().toString();
        tv = (TextView) findViewById(R.id.missonData);
        tv.setText(buttonText+"\nMission:- \n"+visionFactory.getMission(buttonText)+"\n\nVision:- \n"+visionFactory.getVision(buttonText));
    }


}

