package com.nivedita.realestate.base.presenter;

import com.nivedita.realestate.base.view.MVPView;

/**
 * A plain interface Presenter which declares methods
 * attach and detach view and view has to be attached while Using MVP Pattern as a Thumb rule.
 */

public interface Presenter<V extends MVPView> {

    void attachView(V mvpView);

    void detachView();

}
