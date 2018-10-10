package com.nivedita.realestate.propertylist;

import com.nivedita.realestate.base.presenter.BasePresenter;
import com.nivedita.realestate.model.DataManager;
import com.nivedita.realestate.util.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;

/**
 * The Main PropertyList Presenter class which handles all the interactions with view and also manages life cycle of view.
 */

public class PropertyListPresenter<V extends PropertyListView> extends BasePresenter<V>
        implements PropertyListBasePresenter<V> {

    private static final String TAG = PropertyListPresenter.class.getSimpleName();

    /*Define Parameters PopertyListPresenter*/
    DataManager dataManager;
    SchedulerProvider schedulerProvider;
    CompositeDisposable compositeDisposable;
    PublishProcessor<Integer> publishProcessor;

    @Inject
    public PropertyListPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable,
                                 PublishProcessor<Integer> publishProcessor){

        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.publishProcessor = publishProcessor;
    }


    @Override
    public void onPropertyListActivityInitialized() {

    }
}
