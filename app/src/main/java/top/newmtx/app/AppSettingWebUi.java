package top.newmtx.app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import top.libbase.tool.PermissionUtil;

public class AppSettingWebUi extends AppCompatActivity{
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_ui);
        mTextView= (TextView) findViewById(R.id.tv_add);
        findViewById(R.id.tv_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //requestWindowFloat();
                startActivity(getAppDetailSettingIntent());
            }
        });
        findViewById(R.id.tv_reqting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestWindowFloat();
            }
        });

        mTextView.setText(isFloatWindowOpAllowed(this)?"true":"false");

    }



    public void requestWindowFloat(){
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent,10);
        //startActivity(getAppDetailSettingIntent());
        //PermissionUtil.requestPermissions(this, 120, Manifest.permission.SYSTEM_ALERT_WINDOW);
    }

    /**
     * 获取应用详情页面intent(如果无法直接定位到权限界面，直接定位到应用详情页即可手动打开界面)
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


    /**
     * 判断悬浮窗权限
     * @param context
     * @return
     */
    public boolean isFloatWindowOpAllowed(Context context) {
        if (Build.VERSION.SDK_INT <23)
            return true;
        return Settings.canDrawOverlays(context);
    }

    public boolean checkOp(Context context, String op) {
        final int version = Build.VERSION.SDK_INT;

        if(PermissionUtil.checkPermissions(context, Manifest.permission.SYSTEM_ALERT_WINDOW)){
            return true;
        }else{
            return false;
        }

//        if (version >= 19) {
//            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
//            try {
//                Object checkOp = invokePerMethod(manager, "checkOp", op, Binder.getCallingUid(), context.getPackageName());
//                if(checkOp!=null&&(int)checkOp==AppOpsManager.MODE_ALLOWED){
//                    return true;
//                }
//            } catch (Exception e) {
//                //Flog.e(e.getMessage());
//            }
//        } else {
//            //Flog.e("Below API 19 cannot invoke!");
//        }
//        return false;
    }

    private Object invokePerMethod(Object object,String methodName,String op, Integer uid, String packageName)  {  //String op, int uid, String packageName
        try {
            Method method = object.getClass().getMethod(methodName, String.class, Integer.class, String.class);
            if(method!=null){
                return method.invoke(object, op, uid, packageName);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
