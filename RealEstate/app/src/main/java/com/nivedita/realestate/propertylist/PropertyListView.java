package com.nivedita.realestate.propertylist;

import com.nivedita.realestate.base.view.MVPView;
import com.nivedita.realestate.model.property.Listing;

import java.util.List;

/**
 * interface for PropertyList view.
 */

public interface PropertyListView extends MVPView {

    void showListOfProperties(List<Listing> properties);

    void noPropertiesAvailable();

}
