package com.nivedita.realestate.propertylist;

import android.util.Log;

import com.nivedita.realestate.base.presenter.BasePresenter;
import com.nivedita.realestate.base.view.MVPView;
import com.nivedita.realestate.model.DataManager;
import com.nivedita.realestate.model.network.LogNetworkError;
import com.nivedita.realestate.model.property.Listing;
import com.nivedita.realestate.model.property.Property;
import com.nivedita.realestate.util.DataLoadingState;
import com.nivedita.realestate.util.ViewState;
import com.nivedita.realestate.util.rx.SchedulerProvider;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
    Disposable viewSubscription;
    Disposable dataSubscription;

    boolean isPropertyDataAvailable;

    @Inject
    public PropertyListPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable,
                                 PublishProcessor<Property> publishProcessor,
                                 boolean isPropertyDataAvailable
    ) {

        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.publishProcessor = publishProcessor;
        this.isPropertyDataAvailable = false;
    }

    @Override
    public void attachView(V propertyListActivity) {
        super.attachView(propertyListActivity);

        Log.i(TAG, "Data is loading" + isPropertyDataAvailable);
        if (!isPropertyDataAvailable) {
            loadDataForPropertyList();
        }

        loadPropertiesOnConfigChange();

    }

    private Flowable<Property> getDataSubscription() {
        return this.publishProcessor.serialize();
    }

    private void getPropertyListForActivity() {

        if (isPropertyDataAvailable) {

            return;
        }
        // dataLoadingState.setLoadingData(true);
        if (dataSubscription != null && !dataSubscription.isDisposed()) {
            dataSubscription.dispose();
        }
        getMvpView().showWait();
        dataSubscription = dataManager.getRealEstateProperties().
                subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Consumer<Property>() {
                    @Override
                    public void accept(Property property) throws Exception {
                        isPropertyDataAvailable = true;
                        Log.i(TAG, property.getTitle());
                        List<Listing> propertyList = property.getData().getListings();
                        getMvpView().showListOfProperties(propertyList);
                        getMvpView().removeWait();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        isPropertyDataAvailable = false;
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
        if (viewSubscription != null && !viewSubscription.isDisposed()) {
            viewSubscription.dispose();
        }
        compositeDisposable.clear();
    }

    public void unSubscribeDataSubscription() {
        if (dataSubscription != null && !dataSubscription.isDisposed()) {
            dataSubscription.dispose();
        }

    }
    /*Handle two way subscription,  If data is available already Show the data from existing subscription.*/
    private void loadPropertiesOnConfigChange() {

        if (viewSubscription == null || viewSubscription.isDisposed()) {
            viewSubscription = this.getDataSubscription()
                    .subscribeOn(schedulerProvider.computation())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(new Consumer<Property>() {
                        @Override
                        public void accept(Property property) throws Exception {
                            if (property.getData().getListings().size() == 0) {
                                if (!isPropertyDataAvailable) {
                                    getMvpView().noPropertiesAvailable();
                                } else {
                                    getMvpView().showListOfProperties(property.getData().getListings());
                                    isPropertyDataAvailable = true;
                                }
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            getMvpView().showError(new LogNetworkError(throwable));
                        }
                    });
        }
    }

    public boolean isPropertyDataAvailable() {
        return isPropertyDataAvailable;
    }

    public void setPropertyDataAvailable(boolean propertyDataAvailable) {
        isPropertyDataAvailable = propertyDataAvailable;
    }

}
