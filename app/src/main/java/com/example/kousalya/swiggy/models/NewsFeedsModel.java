package com.example.kousalya.swiggy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kousalya on 14/10/16.
 */

public class NewsFeedsModel implements Serializable {

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("post")
    @Expose
    private Post post;

    @SerializedName("photo")
    @Expose
    private String photo;

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public Post getPost ()
    {
        return post;
    }

    public void setPost (Post post)
    {
        this.post = post;
    }

    public String getPhoto ()
    {
        return photo;
    }

    public void setPhoto (String photo)
    {
        this.photo = photo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [author = "+author+", post = "+post+", photo = "+photo+"]";
    }
}
