package com.marvel_explorer.data.model.marvelentitytypes;

import com.google.gson.annotations.SerializedName;
import com.marvel_explorer.data.model.Thumbnail;
import com.marvel_explorer.data.model.Urls;

import java.util.List;

public class MarvelResource<T> {

    @SerializedName("urls")
    private List<Urls> urls;

    @SerializedName("thumbnail")
    private Thumbnail thumbnail;

    @SerializedName("modified")
    private String modified;

    @SerializedName("id")
    private String id;

    @SerializedName("resourceURI")
    private String resourceURI;

    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<Urls> getUrls() {
        return urls;
    }

    public void setUrls(List<Urls> urls) {
        this.urls = urls;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }
}
