package com.marvel_explorer.data.model.marvelentitytypes;

import com.google.gson.annotations.SerializedName;

public class Comic extends MarvelResource {

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

//    private Creators creators;

//    private Characters characters;


//    public Creators getCreators ()
//    {
//        return creators;
//    }

//    public void setCreators (Creators creators)
//    {
//        this.creators = creators;
//    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

}

