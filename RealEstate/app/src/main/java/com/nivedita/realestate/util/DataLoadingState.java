package com.nivedita.realestate.util;

/**
 * Created by PUNEETU on 11-10-2018.
 */

public class DataLoadingState {

    private boolean loadingData;

    public DataLoadingState() {
        this.loadingData = false;
    }

    public boolean isLoadingData() {
        return loadingData;
    }

    public void setLoadingData(boolean loadingData) {
        this.loadingData = loadingData;
    }
}
