package com.marvel_explorer.data.model;

import com.google.gson.annotations.SerializedName;
import com.marvel_explorer.data.model.marvelentitytypes.MarvelResource;

import java.util.List;

public class MarvelResourceData<T>
{
    @SerializedName("total")
    private int total;

    @SerializedName("offset")
    private int offset;

    @SerializedName("limit")
    private int limit;

    @SerializedName("count")
    private int count;

    @SerializedName("results")
    private List<T> mMarvelResources;

    public int getTotal ()
    {
        return total;
    }

    public int getOffset ()
    {
        return offset;
    }

    public int getLimit ()
    {
        return limit;
    }

    public int getCount ()
    {
        return count;
    }

    public List<T> getMarvelResources() {
        return mMarvelResources;
    }

}