package com.tow.sso.codegears_sso_android.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tow.sso.codegears_sso_android.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment3 extends Fragment {


    public TabFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("News1");
        View rootView = inflater.inflate(R.layout.fragment_tab_3, container, false);

        // Inflate the layout for this fragment
        return rootView ;
    }




}
