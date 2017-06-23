package top.newmtx.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.oacg.chromeweb.BaseWebClient;
import com.oacg.chromeweb.x5web.X5WebViewClient;
import com.tencent.smtt.sdk.WebView;

public class X5WebUi extends Activity implements BaseWebClient.WebClientListener{
    FrameLayout fl_web;
    private BaseWebClient<WebView> mWebViewBaseWebClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_ui);
        fl_web= (FrameLayout) findViewById(R.id.fl_web);

        mWebViewBaseWebClient=new X5WebViewClient(this,fl_web);
        mWebViewBaseWebClient.setClientListener(this);
        mWebViewBaseWebClient.onCreate();

        mWebViewBaseWebClient.getWebView().loadUrl("http://192.168.1.123:5963/index.html");
        //mWebViewBaseWebClient.getWebView().loadUrl("https://www.baidu.com");

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
