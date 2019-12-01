package com.marvel_explorer.data.model;

import com.google.gson.annotations.SerializedName;

public class MarvelResourceResponse
{
    @SerializedName("copyright")
    private String copyright;

    @SerializedName("code")
    private String code;

    @SerializedName("data")
    private MarvelResourceData data;

    @SerializedName("attributionHTML")
    private String attributionHTML;

    @SerializedName("attributionText")
    private String attributionText;

    @SerializedName("etag")
    private String etag;

    @SerializedName("status")
    private String status;

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public MarvelResourceData getData ()
    {
        return data;
    }

    public void setData (MarvelResourceData data)
    {
        this.data = data;
    }

    public String getAttributionHTML ()
    {
        return attributionHTML;
    }

    public void setAttributionHTML (String attributionHTML)
    {
        this.attributionHTML = attributionHTML;
    }

    public String getAttributionText ()
    {
        return attributionText;
    }

    public void setAttributionText (String attributionText)
    {
        this.attributionText = attributionText;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

}
