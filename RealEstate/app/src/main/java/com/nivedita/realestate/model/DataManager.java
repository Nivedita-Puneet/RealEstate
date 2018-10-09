package com.nivedita.realestate.model;

import com.nivedita.realestate.model.network.APIHelper;

import javax.inject.Singleton;

/**
 * Interface which defines Single
 * responsibility Principle to declare methods wo consume them from
 * either network, Local or Shared prefs
 */

@Singleton
public interface DataManager extends APIHelper{

    public void onPageLoad();
}
