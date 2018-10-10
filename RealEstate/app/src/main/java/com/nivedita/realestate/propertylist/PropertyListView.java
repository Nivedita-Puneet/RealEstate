package com.nivedita.realestate.propertylist;

import com.nivedita.realestate.base.view.MVPView;
import com.nivedita.realestate.model.property.AgencyList;

import java.util.List;

/**
 * interface for PropertyList view.
 */

public interface PropertyListView extends MVPView {

    void showListOfProperties(List<AgencyList> properties);

    void noPropertiesAvailable();

}
