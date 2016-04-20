package com.tow.sso.codegears_sso_android.hoder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tow.sso.codegears_sso_android.R;


/**
 * Created by pongwii2016 on 8/4/2559.
 */
public class FeedListRowHolde extends RecyclerView.ViewHolder {

    protected ImageView thumbnail;
    protected TextView title;
    private   Context context ;

    public FeedListRowHolde(View itemView) {
        super(itemView);

      //  getSize();
        this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        this.title = (TextView) itemView.findViewById(R.id.title);



    }

    public ImageView getThumbnail() {
        return thumbnail;
    }


    public TextView getTitle() {
        return title;
    }







}
