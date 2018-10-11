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
    private DataLoadingState dataLoadingState;
    private ViewState viewState;

    @Inject
    public PropertyListPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable,
                                 PublishProcessor<Property> publishProcessor,
                                 DataLoadingState dataLoadingState,
                                 ViewState viewState) {

        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.publishProcessor = publishProcessor;
        this.dataLoadingState = dataLoadingState;
        this.viewState = viewState;
    }

    @Override
    public void attachView(V propertyListActivity) {
        super.attachView(propertyListActivity);

        Log.i(TAG, "Data is loading"+ dataLoadingState.isLoadingData());
        if (!viewState.isViewLoadedAtLeastOnceWithValidData()
                && !dataLoadingState.isLoadingData()) {
            loadDataForPropertyList();
        }

        loadPropertiesOnConfigChange();

    }

    private Flowable<Property> getDataSubscription() {
        return this.publishProcessor.serialize();
    }

    private void getPropertyListForActivity() {

        if (dataLoadingState.isLoadingData()) {

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

                        dataLoadingState.setLoadingData(true);
                        Log.i(TAG, property.getTitle());
                        List<Listing> propertyList = property.getData().getListings();
                        getMvpView().showListOfProperties(propertyList);
                        getMvpView().removeWait();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        dataLoadingState.setLoadingData(false);
                        getMvpView().showError(new LogNetworkError(throwable));
                    }
                });

    }

    @Override
    public void loadDataForPropertyList() {

        getPropertyListForActivity();
        if(dataSubscription != null)
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

    private void loadPropertiesOnConfigChange() {

        if (viewSubscription == null || viewSubscription.isDisposed()) {
            viewSubscription = this.getDataSubscription()
                    .subscribeOn(schedulerProvider.computation())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(new Consumer<Property>() {
                        @Override
                        public void accept(Property property) throws Exception {
                            if (property.getData().getListings().size() == 0) {
                                if (!viewState.isViewLoadedAtLeastOnceWithValidData()) {
                                    getMvpView().noPropertiesAvailable();
                                } else {
                                    getMvpView().showListOfProperties(property.getData().getListings());
                                    viewState.setViewLoadedAtLeastOnceWithValidData(true);
                                }
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            if (!dataLoadingState.isLoadingData()) {
                                getMvpView().showError(new LogNetworkError(throwable));
                            }
                        }
                    });
        }
    }
}
