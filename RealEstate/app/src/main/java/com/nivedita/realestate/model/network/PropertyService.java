package com.nivedita.realestate.model.network;

import com.nivedita.realestate.model.property.Property;
import com.nivedita.realestate.util.ConstantsUtil;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * A RETROFIT service class which defines an interface to consume API From server
 * The class uses RXJAVA observable pattern to accomplish this task
 */

public interface PropertyService {

    @GET(ConstantsUtil.GET_PROPERTIES)
    Flowable<Property> getPropertiesFromAgencies();
}
