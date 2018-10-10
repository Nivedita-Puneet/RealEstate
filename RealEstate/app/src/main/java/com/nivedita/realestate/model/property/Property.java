
package com.nivedita.realestate.model.property;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Property {

    @SerializedName("ad_id")
    @Expose
    private Integer adId;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("title")
    @Expose
    private String title;

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Property withAdId(Integer adId) {
        this.adId = adId;
        return this;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Property withData(Data data) {
        this.data = data;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Property withTitle(String title) {
        this.title = title;
        return this;
    }

}
