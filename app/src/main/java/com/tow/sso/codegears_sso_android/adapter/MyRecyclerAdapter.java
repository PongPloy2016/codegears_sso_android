package com.tow.sso.codegears_sso_android.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.tow.sso.codegears_sso_android.R;
import com.tow.sso.codegears_sso_android.TabFragmentDetail;
import com.tow.sso.codegears_sso_android.hoder.FeedListRowHolde;
import com.tow.sso.codegears_sso_android.logger.Logger;
import com.tow.sso.codegears_sso_android.model.FeedItem;

import java.util.List;

/**
 * Created by pongwii2016 on 8/4/2559.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<FeedListRowHolde>  {

    private List<FeedItem> feedItemList;
    private Context mContext;
    private Activity mActivity ;
    private Fragment fragment;

    private ImageView imageView;
    private TextView textView ;
    private   FeedListRowHolde feedListRowHolde ;

    private  FeedItem feedItem ;
    private static final String DESCRIBABLE_KEY = "describable_key";




    public MyRecyclerAdapter(Fragment fragment)
    {
        this.fragment = fragment;
    }

    public MyRecyclerAdapter(Context context , List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }
    @Override
    public FeedListRowHolde onCreateViewHolder(ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
         feedListRowHolde = new FeedListRowHolde(v);
        return feedListRowHolde;
    }

    @Override
    public void onBindViewHolder(FeedListRowHolde  feedListRowHolder, final int position) {

        getSize();

        final FeedItem feedItem = feedItemList.get(position); //feedItem get
        Picasso.with(this.mContext).load(feedItem.getThumbnail()).into(feedListRowHolder.getThumbnail()); //load image
        feedListRowHolder.getTitle().setText(Html.fromHtml(feedItem.getTitle()));
        feedListRowHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Recycle Click" + position, Toast.LENGTH_SHORT).show();



//                Fragment fragment = new Fragment(); // replace your custom fragment class


               // getIntent().putExtra("complexObject", feedItem);


                TabFragmentDetail fragment = new TabFragmentDetail();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DESCRIBABLE_KEY, feedItem);
                fragment.setArguments(bundle);

             //   bundle.putInt("key",feedItem.getId());
//                DataCache.getInstance().push(feedItem);
//                FragmentManager manager = ((FragmentActivity) mContext).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = manager.beginTransaction();
//                TabFragmentDetail tabFragmentDetail = new TabFragmentDetail();
//                tabFragmentDetail.setArguments(bundle);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.replace(R.id.fragment_container, tabFragmentDetail);
//                fragmentTransaction.commit();


//                Bundle bundle = new Bundle();
//                bundle.putInt("key",feedItem.getId());
//                DataCache.getInstance().push(feedItem);
//                FragmentManager manager = ((FragmentActivity) mContext).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = manager.beginTransaction();
//                TabFragmentDetail tabFragmentDetail = new TabFragmentDetail();
//                tabFragmentDetail.setArguments(bundle);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.replace(R.id.fragment_container, tabFragmentDetail);
//                fragmentTransaction.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
         return (null != feedItemList ? feedItemList.size() : 0);
    }

    public  void  getSize(){
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            display.getSize(size);
        }
        int width = size.x;
        int height = size.y;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 320);
        feedListRowHolde.getThumbnail().setLayoutParams(layoutParams);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            feedListRowHolde.getThumbnail().getMaxHeight();
            Logger.Log("height X", String.valueOf(   feedListRowHolde.getThumbnail().getMaxHeight()));
        }

        Logger.Log("height", String.valueOf(height));
        Logger.Log("height", String.valueOf(height/4));

    }







}
