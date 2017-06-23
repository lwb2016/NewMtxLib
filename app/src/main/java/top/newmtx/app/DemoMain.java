package top.newmtx.app;

import android.app.LauncherActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoMain extends LauncherActivity {
    private List<String> mList;
    private List<Intent> mClasses ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = fillArray();
        mClasses = fillClassArray();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, mList);
        this.setListAdapter(adapter);
    }

    private List<Intent> fillClassArray() {
        List<Intent> list=new ArrayList<>();
        list.add(new Intent(this,X5WebUi.class));
        list.add(new Intent(this,WebUi.class));

//        list.add(new Intent(this,ClipListViewDemo.class));
//        list.add(new Intent(this,ClipGridViewDemo.class));
//        list.add(new Intent(this,ClipRecycleViewDemo.class));
//        list.add(new Intent(this,WebUi.class));
//        list.add(getAppDetailSettingIntent());
//        list.add(new Intent(this,AppSettingWebUi.class));
        return list;
    }

    private List<String> fillArray() {
        List<String> list=new ArrayList<>();
        list.add("X5web 超展开");
        list.add("原生web 超展开");
//        list.add("ListView侧滑测试");
//        list.add("GridView侧滑测试");
//        list.add("RecycleView侧滑测试");
//        list.add("WebView测试");
//        list.add("app 安装界面测试");
//        list.add("miui权限测试 安装界面测试");
        return list;
    }

    @Override
    protected Intent intentForPosition(int position) {
        return mClasses.get(position);
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
