package com.nivedita.realestate.propertylist;

import com.nivedita.realestate.base.presenter.Presenter;
import com.nivedita.realestate.di.scope.PerActivity;

/**
 * The presenter interface which will be implemented by PropertyListPresenter class
 */

@PerActivity
public interface PropertyListBasePresenter<V extends PropertyListView> extends Presenter<V> {

    void onPropertyListActivityInitialized();
}
