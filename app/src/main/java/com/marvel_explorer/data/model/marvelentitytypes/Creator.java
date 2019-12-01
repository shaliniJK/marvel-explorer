package com.marvel_explorer.data.model.marvelentitytypes;

import com.google.gson.annotations.SerializedName;

public class Creator extends MarvelResource {

    @SerializedName("fullName")
    private String fullName;

//    private Stories stories;

//    private Comics comics;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
