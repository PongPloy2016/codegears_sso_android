package com.tow.sso.codegears_sso_android;

/**
 * Created by pongwii2016 on 7/4/2559.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tow.sso.codegears_sso_android.fragment.TabFragment3;
import com.tow.sso.codegears_sso_android.fragment.TabFragment4;
import com.tow.sso.codegears_sso_android.fragment.TabFragment5;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment3 tab3 = new TabFragment3();
                return tab3;
            case 1:
                TabFragment4 tab4 = new TabFragment4();
                return tab4;

            case 2:
                TabFragment5 tab5 = new TabFragment5();
                return tab5;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}
