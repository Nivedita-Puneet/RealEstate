package com.nivedita.realestate.mvp;

import android.test.mock.MockDialogInterface;

import com.nivedita.realestate.model.DataManager;
import com.nivedita.realestate.model.property.Listing;
import com.nivedita.realestate.model.property.Property;
import com.nivedita.realestate.propertylist.PropertyListBasePresenter;
import com.nivedita.realestate.propertylist.PropertyListPresenter;
import com.nivedita.realestate.propertylist.PropertyListView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Write Test Cases for Presenter.
 */
@RunWith(MockitoJUnitRunner.class)
public class PropertyListPresenterTest {

    @Mock
    PropertyListView propertyListView;

    @Mock
    DataManager dataManager;
    CompositeDisposable compositeDisposable;
    Property property;
    Disposable subscription;
    private PropertyListPresenter propertyListPresenter;
    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {

        compositeDisposable = new CompositeDisposable();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        propertyListPresenter = new PropertyListPresenter(dataManager, testSchedulerProvider, compositeDisposable);
        propertyListPresenter.attachView(propertyListView);

    }

    @Test
    public void fetchPropertyDetails() {

        Property property = new Property();
        List<Listing> agencies = property.getData().getListings();

        subscription =
                doReturn(Flowable.just(property)).when(dataManager).getRealEstateProperties().subscribe();

        propertyListPresenter.loadDataForPropertyList();
        mTestScheduler.triggerActions();

        verify(propertyListView).showWait();

    }

}
