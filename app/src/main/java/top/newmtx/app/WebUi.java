package top.newmtx.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.FrameLayout;

import com.oacg.chromeweb.BaseWebClient;
import com.oacg.chromeweb.chrome.ChromeWebViewClient;

public class WebUi extends AppCompatActivity implements BaseWebClient.WebClientListener{
    FrameLayout fl_web;
    private BaseWebClient<WebView> mWebViewBaseWebClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_ui);
        fl_web= (FrameLayout) findViewById(R.id.fl_web);

        mWebViewBaseWebClient=new ChromeWebViewClient(this,fl_web);
        mWebViewBaseWebClient.setClientListener(this);
        mWebViewBaseWebClient.onCreate();

        mWebViewBaseWebClient.getWebView().loadUrl("https://www.baidu.com/");

    }

    @Override
    public void onBackPressed() {
        if(mWebViewBaseWebClient.getWebView().canGoBack()){
            mWebViewBaseWebClient.getWebView().goBack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        mWebViewBaseWebClient.onDestroy();
        super.onDestroy();
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
    public void onReceiveTitle(String s) {

    }

    @Override
    public void onProgressChange(int i) {

    }

    @Override
    public void startDownload(String s, long l) {

    }

    public void openAppDetail(){
        startActivity(getAppDetailSettingIntent());
    }

    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    private Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        return localIntent;
    }
}
