package com.nivedita.realestate.model.network;

import com.nivedita.realestate.model.property.Property;

import io.reactivex.Flowable;

/**
 * interface defines the Helper methods to consume API.
 */

public interface APIHelper {

    Flowable<Property> getRealEstateProperties();
}
