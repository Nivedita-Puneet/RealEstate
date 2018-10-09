package com.nivedita.realestate.di.module;

import android.app.Application;
import android.content.Context;

import com.nivedita.realestate.di.scope.ApplicationContext;
import com.nivedita.realestate.model.ApplicationDataManager;
import com.nivedita.realestate.model.DataManager;
import com.nivedita.realestate.model.network.APIHelper;
import com.nivedita.realestate.model.network.PropertyAPIHelper;
import com.nivedita.realestate.util.rx.AppSchedulerProvider;
import com.nivedita.realestate.util.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Define Application Module which defines dependencies.
 */

@Module
public class ApplicationModule {
    @Provides
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager getDataManager(ApplicationDataManager dataManager) {
        return dataManager;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    @Singleton
    APIHelper getPropertyAPIHelper(PropertyAPIHelper propertyAPIHelper) {
        return propertyAPIHelper;
    }
}
