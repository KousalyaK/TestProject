package com.example.kousalya.swiggy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kousalya on 14/10/16.
 */

public class Post implements Serializable {

    @SerializedName("tags")
    @Expose
    private ArrayList<String> tags;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("type")
    @Expose
    private String type;

    public ArrayList<String> getTags ()
    {
        return tags;
    }

    public void setTags (ArrayList<String> tags)
    {
        this.tags = tags;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [tags = "+tags+", text = "+text+", image = "+image+", type = "+type+"]";
    }
}
