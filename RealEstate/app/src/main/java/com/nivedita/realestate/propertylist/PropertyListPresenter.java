package com.nivedita.realestate.propertylist;

import android.util.Log;

import com.nivedita.realestate.base.presenter.BasePresenter;
import com.nivedita.realestate.model.DataManager;
import com.nivedita.realestate.model.property.AgencyList;
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


    private Disposable getPropertyListings() {

        getMvpView().showWait();
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            disposable = publishProcessor.onBackpressureDrop()
                    .doOnNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Exception {

                        }
                    }).concatMap(new Function<Integer, Publisher<Property>>() {
                        @Override
                        public Publisher<Property> apply(Integer integer) throws Exception {
                            return dataManager.getRealEstateProperties()
                                    .subscribeOn(schedulerProvider.io())
                                    .observeOn(schedulerProvider.ui());
                        }
                    }).subscribe(new Consumer<Property>() {
                        @Override
                        public void accept(Property property) throws Exception {

                            List<AgencyList> propertyList = property.getAgency().getAgencyLists();
                            getMvpView().showListOfProperties(propertyList);
                            for (int i = 0; i < propertyList.size(); i++) {
                                Log.i(TAG, propertyList.get(i).getDescription());
                            }
                        }
                    });
        }
        return disposable;
    }

    @Override
    public void onBindDataSubscription() {

        compositeDisposable.add(getPropertyListings());
    }

    @Override
    public void onUnBindDataSubscription() {

        dataManager.unSubscribeRealEstateProperties(disposable, compositeDisposable);
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }
}
