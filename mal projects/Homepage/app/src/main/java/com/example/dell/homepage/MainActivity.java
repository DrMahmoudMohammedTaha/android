package com.example.dell.homepage;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

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
          toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            ImageView universityimg= (ImageView)findViewById(R.id.universityimg);

            universityimg.setImageResource(R.mipmap.benha);
            Log.v("xtest" , getApplicationContext() + " MainActivity");

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

    }

