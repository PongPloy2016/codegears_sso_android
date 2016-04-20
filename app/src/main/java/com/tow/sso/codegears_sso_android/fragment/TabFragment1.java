package com.tow.sso.codegears_sso_android.fragment;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import com.tow.sso.codegears_sso_android.R;
import com.tow.sso.codegears_sso_android.adapter.MyRecyclerAdapter;
import com.tow.sso.codegears_sso_android.logger.Logger;
import com.tow.sso.codegears_sso_android.model.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment1 extends Fragment {

     RelativeLayout rlMumber ;
     RelativeLayout rlCard ;
     RelativeLayout rlMap ;
     RelativeLayout rlSetting ;


    private static final String TAG = "RecyclerViewExample";
    // List<FeedItem> feedsList;
    private List<FeedItem> feedsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter adapter;
    private int id =0;

    public TabFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_tab_1, container, false);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        //toolbar.setTitle("My title");
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        final String url = "http://javatechig.com/?json=get_recent_posts&count=45";
        new AsyncHttpTask().execute(url);


        rlMumber = (RelativeLayout) rootView.findViewById(R.id.rl_menber);
        rlCard = (RelativeLayout) rootView.findViewById(R.id.rl_card);
        rlMap = (RelativeLayout) rootView.findViewById(R.id.rl_map);
        rlSetting = (RelativeLayout) rootView.findViewById(R.id.rl_setting);





        rlMumber.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
               Logger.Log("Onclick","Onclck");
                MainFragmentTab fragment = new MainFragmentTab();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });


        rlCard.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Logger.Log("Onclick","Onclck");
                MainFragmentTab fragment = new MainFragmentTab();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });


        rlMap.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Logger.Log("Onclick","Onclck");
                MainFragmentTab fragment = new MainFragmentTab();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });


        rlSetting.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Logger.Log("Onclick","Onclck");
                MainFragmentTab fragment = new MainFragmentTab();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        });
        // Inflate the layout for this fragment
        return rootView ;
    }



    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
          //  setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected Integer doInBackground(String... params) {

            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();


                // 200 represents HTTP OK
                if (statusCode == 200) {

                    Logger.Log("data","check");
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                  Logger.Log("data",response.toString());

                    try {
                        JSONObject responseData = new JSONObject(response.toString());
                        JSONArray posts = responseData.optJSONArray("posts");
                        Logger.Log("posts",posts.toString());
                        for (int i = 0; i < posts.length(); i++) {
                            JSONObject post = posts.optJSONObject(i);

                            FeedItem item = new FeedItem();
                            item.setTitle(post.optString("title"));
                            item.setThumbnail(post.optString("thumbnail"));
                            item.setId(post.optInt("id"));
                            item.setContent(post.optString("content"));

                            Logger.Log("title", post.optString("title"));
                            Logger.Log("thumbnail", post.optString("thumbnail"));
                            Logger.Log("id", String.valueOf(post.optInt("id")));

                           feedsList.add(item);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
               Logger.Log(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            if (result == 1) {
                adapter = new MyRecyclerAdapter(getContext(),feedsList  );
                mRecyclerView.setAdapter(adapter);
            } else {
                Logger.Log(TAG, "Failed to fetch data!");
            }
        }
    }



    }







