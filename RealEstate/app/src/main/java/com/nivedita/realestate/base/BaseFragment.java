package com.nivedita.realestate.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

/**
 * The Base Fragment class which will be extended by PropertyList Detail Fragment class.
 */

public class BaseFragment extends Fragment {

    private BaseActivity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

}
