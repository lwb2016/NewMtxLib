package top.newmtx.app;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.oacg.chromeweb.chrome.ChromeWebFragment;
import com.oacg.chromeweb.x5web.X5WebFragment;

/**
 * X5fragment
 * Created by leo on 2017/6/23.
 */

public class ChromeFragment extends ChromeWebFragment {
    private FrameLayout fl_web;

    @Override
    protected void initView(View root) {
        fl_web= (FrameLayout) root.findViewById(R.id.fl_web);
    }

    @Override
    protected ViewGroup getWebParent() {
        return fl_web;
    }

    @Override
    public void onLoadingStart() {

    }

    @Override
    public void onLoadingSuccess() {

    }

    @Override
    public void onLoadingError() {

    }

    @Override
    public void onReceiveTitle(String title) {

    }

    @Override
    public void onProgressChange(int newProgress) {

    }

    @Override
    public void startDownload(String url, long contentLength) {

    }

    @Override
    protected void handleUiMsg(Message msg) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web_ui;
    }

    @Override
    public boolean doBackPress() {
        return false;
    }
}
