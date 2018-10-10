package com.nivedita.realestate.base.view;

import com.nivedita.realestate.model.network.LogNetworkError;

/**
 * A base MVP View which must be extended by other view classes
 */

public interface MVPView {

    void showError(LogNetworkError logNetworkError);

    void showWait();

    void removeWait();


}
