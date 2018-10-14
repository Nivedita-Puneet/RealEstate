package com.nivedita.realestate.propertylist;

import com.nivedita.realestate.base.presenter.Presenter;
import com.nivedita.realestate.di.scope.PerActivity;

import javax.inject.Singleton;

/**
 * The presenter interface which will be implemented by PropertyListPresenter.
 * It clearly defines a scope and is singleton class which is independent of Activity life cycle and survives Configuration changes.
 */

@Singleton
@PerActivity
public interface PropertyListBasePresenter<V extends PropertyListView> extends Presenter<V> {

    void loadDataForPropertyList();
    void onUnBindDataSubscription();
}
