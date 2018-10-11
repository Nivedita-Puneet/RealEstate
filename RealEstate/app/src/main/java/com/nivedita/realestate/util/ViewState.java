package com.nivedita.realestate.util;

/**
 * Created by PUNEETU on 11-10-2018.
 */

public class ViewState {


    private boolean isViewLoadedAtLeastOnceWithValidData;

    public ViewState() {
        this.isViewLoadedAtLeastOnceWithValidData = false;
    }

    public boolean isViewLoadedAtLeastOnceWithValidData() {
        return isViewLoadedAtLeastOnceWithValidData;
    }

    public void setViewLoadedAtLeastOnceWithValidData(boolean viewLoadedAtLeastOnceWithValidData) {
        isViewLoadedAtLeastOnceWithValidData = viewLoadedAtLeastOnceWithValidData;
    }
}
