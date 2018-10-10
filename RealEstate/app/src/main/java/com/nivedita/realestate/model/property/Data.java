
package com.nivedita.realestate.model.property;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("listings")
    @Expose
    private List<Listing> listings = null;

    public List<Listing> getListings() {
        return listings;
    }

    public void setListings(List<Listing> listings) {
        this.listings = listings;
    }

    public Data withListings(List<Listing> listings) {
        this.listings = listings;
        return this;
    }

}
