package com.oacg.chromeweb.chrome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.oacg.chromeweb.BaseWebClient;

import top.libbase.ui.fragment.BaseFragment;

/**
 * 原生web的fragment
 * Created by leo on 2017/6/23.
 */

public abstract class ChromeWebFragment extends BaseFragment implements BaseWebClient.WebClientListener{
    BaseWebClient<WebView> mTBaseWebClient;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        mTBaseWebClient=new ChromeWebViewClient(getActivity(),getWebParent());
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
