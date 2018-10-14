package com.nivedita.realestate.propertylist;


import com.nivedita.realestate.base.presenter.BasePresenter;
import com.nivedita.realestate.model.DataManager;
import com.nivedita.realestate.model.network.LogNetworkError;
import com.nivedita.realestate.model.property.Listing;
import com.nivedita.realestate.model.property.Property;
import com.nivedita.realestate.util.rx.SchedulerProvider;


import java.util.List;

import javax.inject.Inject;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;


/**
 * The Main PropertyList Presenter class which handles all the interactions with view and also manages life cycle of view.
 */
public class PropertyListPresenter<V extends PropertyListView> extends BasePresenter<V>
        implements PropertyListBasePresenter<V> {

    private static final String TAG = PropertyListPresenter.class.getSimpleName();

    /*Define Parameters PropertyList Presenter*/
    DataManager dataManager;
    SchedulerProvider schedulerProvider;
    CompositeDisposable compositeDisposable;
    PublishProcessor<Property> publishProcessor;
    Disposable dataSubscription;

    @Inject
    public PropertyListPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable) {

        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void attachView(V propertyListActivity) {
        super.attachView(propertyListActivity);
        loadDataForPropertyList();
    }

    /* The method which loads all the properties from API as a RX Flowable<Property> and survives config changes.*/
    private void getPropertyListForActivity() {

        if (dataSubscription != null && !dataSubscription.isDisposed()) {
            dataSubscription.dispose();
        }
        getMvpView().showWait();
        if(null == dataSubscription)
        dataSubscription = dataManager.getRealEstateProperties()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Consumer<Property>() {
                    @Override
                    public void accept(Property property) throws Exception {

                        List<Listing> propertyList = property.getData().getListings();
                        getMvpView().showListOfProperties(propertyList);
                        getMvpView().removeWait();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().showError(new LogNetworkError(throwable));
                    }
                });

    }

    @Override
    public void loadDataForPropertyList() {

        getPropertyListForActivity();
        if (dataSubscription != null)
            compositeDisposable.add(dataSubscription);

    }

    @Override
    public void onUnBindDataSubscription() {

        unSubscribeDataSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }

    public void unSubscribeDataSubscription() {
        if (dataSubscription != null && !dataSubscription.isDisposed()) {
            dataSubscription.dispose();
        }

    }


}
