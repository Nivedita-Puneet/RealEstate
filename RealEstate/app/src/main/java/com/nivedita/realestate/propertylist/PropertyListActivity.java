package com.nivedita.realestate.propertylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.nivedita.realestate.base.BaseActivity;
import com.nivedita.realestate.model.network.LogNetworkError;
import com.nivedita.realestate.model.property.Listing;
import com.nivedita.realestate.propertydetail.PropertyListDetailActivity;
import com.nivedita.realestate.propertydetail.PropertyListDetailFragment;
import com.nivedita.realestate.R;
import com.nivedita.realestate.model.DummyContent;

import java.util.List;

import javax.inject.Inject;

/**
 * An activity representing a list of PropertyListItems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link PropertyListDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class PropertyListActivity extends BaseActivity implements PropertyListView {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecyclerView propertyListView;
    PropertyListAdapter propertyListAdapter;

    @Inject
    PropertyListBasePresenter<PropertyListView> propertyListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        initializeControls();
        propertyListPresenter.attachView(this);
    }

    private void initializeControls(){

        propertyListAdapter = new PropertyListAdapter(PropertyListActivity.this, null);
        propertyListView = (RecyclerView)findViewById(R.id.propertylist_list);
        propertyListView.setAdapter(propertyListAdapter);
        /*propertyListView.setAdapter(new PropertyListAdapter(PropertyListActivity.this, new PropertyListAdapter.PropertyListClickListener() {
            @Override
            public void onClickListener(String itemPosition) {

                if(checkForTabletConfiguration()){

                    Bundle arguments = new Bundle();
                    arguments.putString(PropertyListDetailFragment.ARG_ITEM_ID, itemPosition);
                    PropertyListDetailFragment fragment = new PropertyListDetailFragment();
                    fragment.setArguments(arguments);
                    PropertyListActivity.this.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.propertylist_detail_container, fragment)
                            .commit();
                }else {

                    Intent intent = new Intent(PropertyListActivity.this, PropertyListDetailActivity.class);
                    intent.putExtra(PropertyListDetailFragment.ARG_ITEM_ID, itemPosition);
                    PropertyListActivity.this.startActivity(intent);

                }
            }
        }));*/

    }

    private boolean checkForTabletConfiguration(){

        if(findViewById(R.id.propertylist_detail_container)!= null){
            mTwoPane = true;
        }
        return mTwoPane;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        propertyListPresenter.detachView();
        propertyListPresenter.onUnBindDataSubscription();
    }

    @Override
    public void showError(LogNetworkError logNetworkError) {

    }

    @Override
    public void showWait() {

    }

    @Override
    public void removeWait() {

    }

    @Override
    public void showListOfProperties(List<Listing> properties) {
        Log.i(PropertyListActivity.class.getSimpleName(), properties.get(0).getAgencyLogoUrl());
        propertyListAdapter.addAll(properties);
    }

    @Override
    public void noPropertiesAvailable() {

    }
}
