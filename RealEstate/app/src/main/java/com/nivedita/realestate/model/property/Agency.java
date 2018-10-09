
package com.nivedita.realestate.model.property;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agency {

    @SerializedName("agencyLists")
    @Expose
    private List<AgencyList> agencyLists = null;

    public List<AgencyList> getAgencyLists() {
        return agencyLists;
    }

    public void setAgencyLists(List<AgencyList> agencyLists) {
        this.agencyLists = agencyLists;
    }

    public Agency withListings(List<AgencyList> agencyLists) {
        this.agencyLists = agencyLists;
        return this;
    }

}
