package com.paper.developwidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        AppWidgetManager awm=AppWidgetManager.getInstance(this);
        Log.e("hshs", action);
        RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.remot_adbwidget);

        if (action.equals(RemotADBWidget.BT_REFRESH_ACTION)){
//            Toast.makeText(context,"点了一下！"+isOpened,Toast.LENGTH_SHORT).show();
            if (!PreferenceUtils.getBoolean(this,"isopen",false)){
                ShellUtils.CommandResult r = ShellUtils.execCommand("setprop service.adb.tcp.port 5555", true);
                if (r.result < 0){
                    views.setTextViewText(R.id.appwidget_text,r.errorMsg);
                }else {
                    views.setTextViewText(R.id.appwidget_text,NetUtils.getWifiIp(this));
                    views.setTextViewText(R.id.start,"开启状态");
                    PreferenceUtils.putBoolean(this, "isopen", true);
                }
            }else {

                ShellUtils.CommandResult r1 = ShellUtils.execCommand("stop adbd", true);
                ShellUtils.CommandResult r2 = ShellUtils.execCommand("start adbd", true);
                if (r1.result < 0 || r2.result < 0){
                    views.setTextViewText(R.id.appwidget_text,r1.errorMsg);
                }else {
                    views.setTextViewText(R.id.start,"关闭状态");
                    PreferenceUtils.putBoolean(this,"isopen",false);
                }
            }



        }else if (action.equals(RemotADBWidget.BT_SEARCH_ACTION)){
            this.startActivity(new Intent(this, MainActivity.class));
        }else if(action.equals(RemotADBWidget.BT_TEST_ACTION)){
//            Log.e("hshs", "刷新界面！");
            if (!PreferenceUtils.getBoolean(this,"istest",false)){
                views.setTextViewText(R.id.appwidget_testbtn,"xixixi");
                PreferenceUtils.putBoolean(this,"istest",true);
            }else{
                views.setTextViewText(R.id.appwidget_testbtn,"hahaha");
                PreferenceUtils.putBoolean(this,"istest",false);
            }

        }
        awm.updateAppWidget(RemotADBWidget.widgetIds,views);
        return super.onStartCommand(intent, flags, startId);
    }
}
