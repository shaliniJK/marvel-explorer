package com.marvel_explorer.data.model;

import com.google.gson.annotations.SerializedName;
import com.marvel_explorer.data.model.marvelentitytypes.MarvelResource;

import java.util.List;

public class MarvelResourceData<T>
{
    @SerializedName("total")
    private String total;

    @SerializedName("offset")
    private String offset;

    @SerializedName("limit")
    private String limit;

    @SerializedName("count")
    private String count;

    @SerializedName("results")
    private List<T> mMarvelResources;

    public String getTotal ()
    {
        return total;
    }

    public String getOffset ()
    {
        return offset;
    }

    public String getLimit ()
    {
        return limit;
    }

    public String getCount ()
    {
        return count;
    }

    public List<T> getMarvelResources() {
        return mMarvelResources;
    }

}