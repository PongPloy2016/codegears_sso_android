package com.tow.sso.codegears_sso_android.model;

import java.io.Serializable;

/**
 * Created by pongwii2016 on 8/4/2559.
 */
public  class FeedItem implements Serializable {

   private String title ;
   private String thumbnail ;
   private int id ;
   private String  content ;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



//    public FeedItem(View itemView) {
//        super(itemView);
//    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public  void setId(int id) {
        this.id = id;
    }
}
