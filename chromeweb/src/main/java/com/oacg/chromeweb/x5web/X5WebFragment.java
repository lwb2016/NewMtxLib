package com.oacg.chromeweb.x5web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.oacg.chromeweb.BaseWebClient;
import com.tencent.smtt.sdk.WebView;

import top.libbase.ui.fragment.BaseFragment;

/**
 * x5web的frament
 * Created by leo on 2017/6/23.
 */

public abstract class X5WebFragment extends BaseFragment implements BaseWebClient.WebClientListener{
    BaseWebClient<WebView> mTBaseWebClient;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mTBaseWebClient=new X5WebViewClient(getActivity(),getWebParent());
        mTBaseWebClient.onCreate();
    }

    @Override
    public void onDestroyView() {
        mTBaseWebClient.onDestroy();
        super.onDestroyView();
    }

    protected abstract void initView(View root);

    protected abstract ViewGroup getWebParent();
}
