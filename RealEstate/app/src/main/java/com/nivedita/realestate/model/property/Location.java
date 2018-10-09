
package com.nivedita.realestate.model.property;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Address2")
    @Expose
    private String address2;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("Suburb")
    @Expose
    private String suburb;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public Location withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Location withState(String state) {
        this.state = state;
        return this;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public Location withSuburb(String suburb) {
        this.suburb = suburb;
        return this;
    }

}
