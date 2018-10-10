package com.nivedita.realestate.model;

import android.content.Context;

import com.nivedita.realestate.di.scope.ApplicationContext;
import com.nivedita.realestate.model.network.APIHelper;
import com.nivedita.realestate.model.property.Property;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Class used by presenter to handle all data driven operations
 */

public class ApplicationDataManager implements DataManager {

    private static final String TAG = ApplicationDataManager.class.getSimpleName();
    private final APIHelper apiHelper;
    private final Context context;

    @Inject
    public ApplicationDataManager(@ApplicationContext Context context, APIHelper apiHelper) {
        this.apiHelper = apiHelper;
        this.context = context;
    }

    @Override
    public Flowable<Property> getRealEstateProperties() {
        return apiHelper.getRealEstateProperties();
    }

    @Override
    public void unSubscribeRealEstateProperties(Disposable disposable, CompositeDisposable compositeDisposable) {

        if(disposable != null && !disposable.isDisposed()){

            compositeDisposable.dispose();
        }

    }
}
