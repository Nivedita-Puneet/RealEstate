
package com.nivedita.realestate.model.property;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("big")
    @Expose
    private Big big;
    @SerializedName("small")
    @Expose
    private Small small;
    @SerializedName("medium")
    @Expose
    private Medium medium;

    public Big getBig() {
        return big;
    }

    public void setBig(Big big) {
        this.big = big;
    }

    public Image withBig(Big big) {
        this.big = big;
        return this;
    }

    public Small getSmall() {
        return small;
    }

    public void setSmall(Small small) {
        this.small = small;
    }

    public Image withSmall(Small small) {
        this.small = small;
        return this;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Image withMedium(Medium medium) {
        this.medium = medium;
        return this;
    }

}
