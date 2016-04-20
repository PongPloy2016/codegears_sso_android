package com.tow.sso.codegears_sso_android.fragment;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tow.sso.codegears_sso_android.PagerAdapter;
import com.tow.sso.codegears_sso_android.R;

public class MainFragmentTab extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private TextView text;
    NavigationView navigationView = null;
    TextView slideshow,gallery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("News");

        View rootView = inflater.inflate(R.layout.fragment_main_tab, container, false);


       Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
       //toolbar.setTitle("My title");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        activity.getSupportActionBar().setTitle("News");
//        text = (TextView) activity.findViewById(R.id.text);
//        text.setText("News");
      //  text = (TextView) toolbar.findViewById(R.id.text);


        navigationView = (NavigationView)rootView. findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gallery=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_gallery));
        slideshow=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_slideshow));
        //  initializeCountDrawer();
        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);
        TextView emailText = (TextView) headerView.findViewById(R.id.email);
        emailText.setText("newemail@email.com");

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) rootView. findViewById(R.id.viewpager);
        final PagerAdapter adapter = new PagerAdapter
                (this.getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        DrawerLayout drawer = (DrawerLayout)rootView. findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return rootView ;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            //Set the fragment initially
            TabFragment1 fragment = new TabFragment1();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            //Set the fragment initially
            MainFragmentTab fragment = new MainFragmentTab();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }


        return false;
    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser){
//            ((MainActivity)getActivity()).setTitleBar("Chart");
//        }
//    }



}
