package com.nivedita.realestate.model.network;

import com.nivedita.realestate.model.property.Property;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * The API Helper class which starts emitting observable in presenter
 */

@Singleton
public class PropertyAPIHelper implements APIHelper {

    private final PropertyService propertyService;

    @Inject
    public PropertyAPIHelper(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public Flowable<Property> getRealEstateProperties() {
        return propertyService.getPropertiesFromAgencies();
    }
}
