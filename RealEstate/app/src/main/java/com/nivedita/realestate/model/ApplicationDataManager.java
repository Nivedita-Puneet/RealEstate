package com.nivedita.realestate.model;

import android.content.Context;

import com.nivedita.realestate.di.scope.ApplicationContext;
import com.nivedita.realestate.model.network.APIHelper;
import com.nivedita.realestate.model.property.Property;
import com.nivedita.realestate.util.DataLoadingState;
import com.nivedita.realestate.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Class used by presenter to handle all data driven operations
 */

public class ApplicationDataManager implements DataManager {

    private static final String TAG = ApplicationDataManager.class.getSimpleName();
    private final APIHelper apiHelper;
    private final Context context;

    private DataLoadingState dataLoadingState;

    private PublishProcessor<Property> dataSubject;
    private Disposable dataSubscription;
    private SchedulerProvider schedulerProvider;

    @Inject
    public ApplicationDataManager(@ApplicationContext Context context,
                                  APIHelper apiHelper) {
        this.apiHelper = apiHelper;
        this.context = context;
    }

    @Override
    public Flowable<Property> getRealEstateProperties() {
        return apiHelper.getRealEstateProperties();
    }

    @Override
    public void loadPropertyListForActivity() {

    }
}
