package com.tow.sso.codegears_sso_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tow.sso.codegears_sso_android.fragment.TabFragment1;
import com.tow.sso.codegears_sso_android.logger.Logger;
import com.tow.sso.codegears_sso_android.model.FeedItem;


public class TabFragmentDetail extends Fragment {

            private FeedItem feedItem ;
    private static final String DESCRIBABLE_KEY = "describable_key";
    public TabFragmentDetail() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_tab_fragment_detail, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        feedItem = (FeedItem) getArguments().getSerializable(
                DESCRIBABLE_KEY);

    //    feedItem = DataCache.getInstance().pop(FeedItem.class);

        feedItem.getThumbnail();
        feedItem.getTitle();
        feedItem.getContent();

        Logger.Log("feedItem.getThumbnail", feedItem.getThumbnail());
        Logger.Log("feedItem.getTitle", feedItem.getTitle());
        Logger.Log("feedItem.getContent()", feedItem.getContent());

//        int data = 0;
//        Bundle bundle = this.getArguments();
//        int myInt = bundle.getInt("key", data);
//        Logger.Log("myInt", String.valueOf(myInt));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.Log("onDetach","onDetach");

        TabFragment1 fragment = new TabFragment1();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
     //   getActivity().getSupportFragmentManager().popBackStack();
    }





//    @Override
//    public void onResume() {
//
//        Logger.Log("onResume","onResume");
//        super.onResume();
//
//        getActivity().getSupportFragmentManager().popBackStack();
//
//    }



}
