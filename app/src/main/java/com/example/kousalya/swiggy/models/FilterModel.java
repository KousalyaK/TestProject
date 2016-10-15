package com.example.kousalya.swiggy.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kousalya on 15/10/16.
 */

public class FilterModel implements Serializable {
    @SerializedName("values")
    @Expose
    private ArrayList<String> values;

    @SerializedName("name")
    @Expose
    private String name;

    public ArrayList<String> getValues ()
    {
        return values;
    }

    public void setValues (ArrayList<String> values)
    {
        this.values = values;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [values = "+values+", name = "+name+"]";
    }
}
