package com.oacg.chromeweb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.libbase.ui.fragment.BaseFragment;

/**
 * Created by leo on 2017/6/23.
 */

public abstract class BaseWebFragment<T extends View> extends BaseFragment {
    BaseWebClient<T> mTBaseWebClient;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mTBaseWebClient!=null){
            mTBaseWebClient.onCreate();
        }
    }

    @Override
    public void onDestroyView() {
        if(mTBaseWebClient!=null){
            mTBaseWebClient.onDestroy();
        }
        super.onDestroyView();
    }

    protected abstract ViewGroup getWebParent();
}
