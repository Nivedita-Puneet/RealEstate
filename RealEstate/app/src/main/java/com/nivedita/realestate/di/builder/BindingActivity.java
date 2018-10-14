package com.nivedita.realestate.di.builder;

import com.nivedita.realestate.di.module.PropertyListModule;
import com.nivedita.realestate.di.scope.PerActivity;
import com.nivedita.realestate.propertylist.PropertyListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * An activity module which binds all the components together
 */

@Module
public abstract class BindingActivity {

    @ContributesAndroidInjector(modules = {
            PropertyListModule.class
    })
    abstract PropertyListActivity bindPropertyListActivity();
}
