package com.nivedita.realestate.base;

import android.app.Activity;
import android.app.Application;

import com.nivedita.realestate.di.component.ApplicationComponent;
import com.nivedita.realestate.di.component.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * The application class used to configure application
 */

public class RealEstateApplication extends Application implements HasActivityInjector {

    ApplicationComponent applicationComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate(){
        super.onCreate();
        DaggerApplicationComponent.builder().application(this).build().inject(RealEstateApplication.this);

    }
    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
