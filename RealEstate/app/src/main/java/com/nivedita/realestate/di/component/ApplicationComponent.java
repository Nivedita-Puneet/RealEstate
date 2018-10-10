package com.nivedita.realestate.di.component;

import android.app.Application;

import com.nivedita.realestate.base.RealEstateApplication;
import com.nivedita.realestate.di.builder.BindingActivity;
import com.nivedita.realestate.di.module.ApplicationModule;
import com.nivedita.realestate.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Dagger component class defined to connect all the modules of property list
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ApplicationModule.class,
        BindingActivity.class,
        NetworkModule.class
})
public interface ApplicationComponent {

    void inject(RealEstateApplication realEstateApplication);

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}
