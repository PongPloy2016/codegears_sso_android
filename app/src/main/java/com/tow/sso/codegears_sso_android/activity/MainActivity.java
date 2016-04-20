package com.tow.sso.codegears_sso_android.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.tow.sso.codegears_sso_android.R;
import com.tow.sso.codegears_sso_android.TabFragmentDetail;
import com.tow.sso.codegears_sso_android.fragment.MainFragmentTab;
import com.tow.sso.codegears_sso_android.fragment.TabFragment1;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    TextView slideshow,gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the fragment initially
        TabFragment1 fragment = new TabFragment1();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        int[][] state = new int[][] {
//                new int[] {-android.R.attr.state_enabled}, // disabled
//                new int[] {android.R.attr.state_enabled}, // enabled
//                new int[] {-android.R.attr.state_checked}, // unchecked
//                new int[] { android.R.attr.state_pressed}  // pressed
//
//        };
//
//
//        int[] color = new int[] {
//                Color.WHITE,
//                Color.WHITE,
//                Color.WHITE,
//                Color.WHITE
//        };
//
//        ColorStateList colorStateList1 = new ColorStateList(state, color);
//
//        int[][] states = new int[][] {
//                new int[] {-android.R.attr.state_enabled}, // disabled
//                new int[] {android.R.attr.state_enabled}, // enabled
//                new int[] {-android.R.attr.state_checked}, // unchecked
//                new int[] { android.R.attr.state_pressed}  // pressed
//
//        };
//
//        int[] colors = new int[] {
//                Color.WHITE,
//                Color.WHITE,
//                Color.WHITE,
//                Color.WHITE
//        };
//        ColorStateList colorStateList2 = new ColorStateList(states, colors);
//        navigationView.setItemTextColor(colorStateList1);
//        navigationView.setItemIconTintList(colorStateList2);

        gallery=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_gallery));
        slideshow=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_slideshow));
        //  initializeCountDrawer();
        //How to change elements in the header programatically
        View headerView = navigationView.getHeaderView(0);
        TextView emailText = (TextView) headerView.findViewById(R.id.email);
        emailText.setText("newemail@email.com");



    }


    private void initializeCountDrawer(){
        //  gallery.setGravity(Gravity.CENTER_VERTICAL);
        gallery.setTypeface(null, Typeface.BOLD);
        gallery.setTextColor(getResources().getColor(R.color.colorAccent));
        gallery.setText("99+");
        //  slideshow.setGravity(Gravity.CENTER_VERTICAL);
        slideshow.setTypeface(null,Typeface.BOLD);
        slideshow.setTextColor(getResources().getColor(R.color.colorAccent));
        slideshow.setText("7");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }


        final FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
	    	/*FragmentManager.BackStackEntry backStackEntry  = this.getSupportFragmentManager().getBackStackEntryAt(this.getSupportFragmentManager().getBackStackEntryCount()-1);
	    	Fragment currentFragment = this.getSupportFragmentManager().findFragmentByTag( backStackEntry.getName() );*/
            Fragment currentFragment = getVisibleFragment();
            //Toast.makeText(this, "t1 "+backStackEntry.getName(), Toast.LENGTH_LONG).show();
            if (currentFragment instanceof TabFragmentDetail

                    )

            {

                //setBackButtonVisible(false);
            }

        }

    }
    public Fragment getVisibleFragment() {
        /*FragmentManager fragmentManager = this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for(Fragment fragment : fragments){
            if(fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;*/
        if (this.getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backStackEntry = this.getSupportFragmentManager().getBackStackEntryAt(this.getSupportFragmentManager().getBackStackEntryCount() - 1);
            Fragment currentFragment = this.getSupportFragmentManager().findFragmentByTag(backStackEntry.getName());
            return currentFragment;
        }
        return null;
    }





//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {


            //Set the fragment initially
            TabFragment1 fragment = new TabFragment1();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            //Set the fragment initially
            MainFragmentTab fragment = new MainFragmentTab();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}