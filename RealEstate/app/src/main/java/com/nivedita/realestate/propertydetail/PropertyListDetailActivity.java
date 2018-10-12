package com.nivedita.realestate.propertydetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.TextView;

import com.nivedita.realestate.R;
import com.nivedita.realestate.propertylist.PropertyListActivity;

/*Activity class which will be
launched when tapped on recycler view list
item in the absence of tablet configuration.*/
public class PropertyListDetailActivity extends AppCompatActivity {

    TextView propertyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propertylist_detail);
        initializeControls(savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            navigateUpTo(new Intent(this, PropertyListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeControls(Bundle savedInstanceState) {

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        propertyId = (TextView) findViewById(R.id.property_id);
        propertyId.setText(getIntent().getStringExtra(PropertyListDetailFragment.ARG_ITEM_ID));

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(PropertyListDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PropertyListDetailFragment.ARG_ITEM_ID));
            PropertyListDetailFragment fragment = new PropertyListDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.propertylist_detail_container, fragment)
                    .commit();
        }
    }
}
