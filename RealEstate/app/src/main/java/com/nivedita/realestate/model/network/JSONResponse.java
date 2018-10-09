package com.nivedita.realestate.model.network;

import com.google.gson.annotations.SerializedName;

/**
 * A class used to de serialize the JSON Response
 */

public class JSONResponse {

    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public JSONResponse() {
    }

    public JSONResponse(String status) {
        this.status = status;
    }

}
