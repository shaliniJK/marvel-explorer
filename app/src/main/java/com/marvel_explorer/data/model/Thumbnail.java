package com.marvel_explorer.data.model;

public class Thumbnail
{
    public static final String SIZE_PORTRAIT_XLARGE = "portrait_xlarge";
    public static final String SIZE_STANDARD_SMALL = "standard_small";
    public static final String SIZE_STANDARD_FANTASTIC = "standard_fantastic";
    public static final String SIZE_LANDSCAPE_XLARGE = "landscape_xlarge";

    private String path;

    private String extension;

    public String getPath ()
    {
        return path;
    }

    public void setPath (String path)
    {
        this.path = path;
    }

    public String getExtension ()
    {
        return extension;
    }

    public void setExtension (String extension)
    {
        this.extension = extension;
    }

    public static String getImageUrl(String path, String size, String extension) {
        return path + "/" + size + "." + extension;
    }

    public String getThumbnailUrl() {
        return path + "/" + SIZE_STANDARD_SMALL + "." + extension;
    }

}