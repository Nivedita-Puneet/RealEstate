package com.nivedita.realestate.propertylist;

import android.util.Log;

import com.nivedita.realestate.base.presenter.BasePresenter;
import com.nivedita.realestate.model.DataManager;
import com.nivedita.realestate.model.network.LogNetworkError;
import com.nivedita.realestate.model.property.Listing;
import com.nivedita.realestate.model.property.Property;
import com.nivedita.realestate.util.rx.SchedulerProvider;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

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
    PublishProcessor<Integer> publishProcessor;
    Disposable disposable;

    @Inject
    public PropertyListPresenter(DataManager dataManager,
                                 SchedulerProvider schedulerProvider,
                                 CompositeDisposable compositeDisposable,
                                 PublishProcessor<Integer> publishProcessor) {

        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.publishProcessor = publishProcessor;
    }

    private Disposable getAgencyListing() {
        return dataManager.getRealEstateProperties().
                subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(new Consumer<Property>() {
                    @Override
                    public void accept(Property property) throws Exception {
                        Log.i(TAG, property.getTitle());
                        List<Listing> propertyList = property.getData().getListings();
                        getMvpView().showListOfProperties(propertyList);
                        for (int i = 0; i < propertyList.size(); i++) {
                            Log.i(TAG, propertyList.get(i).getDescription());
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        getMvpView().showError(new LogNetworkError(throwable));
                    }
                });

    }

    @Override
    public void onBindViewSubscription() {

        compositeDisposable.add(getAgencyListing());
    }

    @Override
    public void onUnBindViewSubscription() {

        dataManager.unSubscribeRealEstateProperties(compositeDisposable);
    }

    @Override
    public void detachView() {
        super.detachView();
        onUnBindViewSubscription();
        //compositeDisposable.clear();
    }
}
