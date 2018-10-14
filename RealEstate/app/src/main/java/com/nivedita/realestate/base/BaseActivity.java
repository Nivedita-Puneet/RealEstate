package com.nivedita.realestate.base;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import com.nivedita.realestate.BuildConfig;

import dagger.android.AndroidInjection;

/**
 * A base activity which is extended by Property List Activity, It handles all the basic operations.
 */

public class BaseActivity extends AppCompatActivity implements BaseFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDependencyInjection();
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
        }

    }

    public void performDependencyInjection() {
        AndroidInjection.inject(BaseActivity.this);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
