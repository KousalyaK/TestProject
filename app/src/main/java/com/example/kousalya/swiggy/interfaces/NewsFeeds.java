package com.example.kousalya.swiggy.interfaces;

import com.example.kousalya.swiggy.models.FilterModel;
import com.example.kousalya.swiggy.models.NewsFeedsModel;

import java.util.ArrayList;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kousalya on 15/10/16.
 */

public interface NewsFeeds {

    //
    @GET("/news-feed")
    void getfeeds(@Query("project") String sw, Callback<ArrayList<NewsFeedsModel>> responseCallback);

    //http://compassites-dev-api.herokuapp.com/apis/filtered-feed?project=SW&category=Latest
    @GET("/filtered-feed")
    void filtered_feed(@Query("project") String project, @Query("category") String category, Callback<ArrayList<NewsFeedsModel>> responseCallback);

    //http://compassites-dev-api.herokuapp.com/apis/filters?project=SW
    @GET("/filters")
    void filters(@Query("project") String project, Callback<ArrayList<FilterModel>> reArrayListCallback);

    //http://compassites-dev-api.herokuapp.com/apis/filtered-feed?project=SW&filter=team:H R---Topics:Fun
    @GET("/filtered-feed")
    void filtered_feed_category(@Query("project") String sw, @Query("filter") String filter, Callback<ArrayList<NewsFeedsModel>> responseCallback);
}
