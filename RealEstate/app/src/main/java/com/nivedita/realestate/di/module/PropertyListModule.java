package com.nivedita.realestate.di.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.nivedita.realestate.di.scope.ActivityContext;
import com.nivedita.realestate.di.scope.PerActivity;
import com.nivedita.realestate.model.property.Property;
import com.nivedita.realestate.propertylist.PropertyListActivity;
import com.nivedita.realestate.propertylist.PropertyListBasePresenter;
import com.nivedita.realestate.propertylist.PropertyListPresenter;
import com.nivedita.realestate.propertylist.PropertyListView;
import com.nivedita.realestate.util.DataLoadingState;
import com.nivedita.realestate.util.ViewState;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;

/**
 * The Module class for Property List which provides all the dependencies.
 */

@Module
public class PropertyListModule {

    boolean isPropertyDataAvailable;

    @Provides
    @ActivityContext
    Context provideContext(PropertyListActivity propertyListActivity) {
        return propertyListActivity;
    }

    //TODO: Add adapter as a dependency module.

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    LinearLayoutManager linearLayoutManager(PropertyListActivity propertyListActivity) {
        return new LinearLayoutManager(propertyListActivity,
                LinearLayoutManager.VERTICAL, false);
    }


    @Provides
    ViewState provideViewState() {
        return new ViewState();
    }

    @Provides
    boolean provideIsPropertyDataAvailable() {
        return isPropertyDataAvailable;
    }

    @Provides
    DataLoadingState provideDataLoadingState() {
        return new DataLoadingState();
    }

    @Provides
    PublishProcessor<Property> providePublishProcessor() {
        return PublishProcessor.create();
    }

    @Provides
    PropertyListBasePresenter<PropertyListView> providePresenter(PropertyListPresenter<PropertyListView> propertyListPresenter) {
        return propertyListPresenter;
    }


}
