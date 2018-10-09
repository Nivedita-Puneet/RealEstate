
package com.nivedita.realestate.model.property;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Small {

    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Small withUrl(String url) {
        this.url = url;
        return this;
    }

}
