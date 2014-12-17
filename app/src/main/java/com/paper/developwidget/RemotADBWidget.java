package com.paper.developwidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RemoteViews;

import com.paper.developwidget.db.AppPackeget;
import com.paper.developwidget.db.AppPacketManager;

import java.util.List;


/**
 * Implementation of App Widget functionality.
 */
public class RemotADBWidget extends AppWidgetProvider {

    public static final String BT_REFRESH_ACTION = "com.paper.dev.BT_REFRESH_ACTION";
    public static final String BT_SEARCH_ACTION = "com.paper.dev.BT_SEARCH_ACTION";
    public static final String BT_TEST_ACTION = "com.paper.dev.BT_TEST_ACTION";

    private boolean isOpened ;
    public static int widgetIds[];
    AlarmManager mAlarmManager;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                SystemClock.elapsedRealtime() + period, period, p.broadcast);
        Log.e("hshs","onUpdate");
        widgetIds = appWidgetIds;
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for w
        Log.e("hshs","onEnabled");
        mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//        new SaveApplicationInfo().execute(context);

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.remot_adbwidget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        Intent it = new Intent().setAction(BT_REFRESH_ACTION);
        PendingIntent pit = PendingIntent.getBroadcast(context, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.start,pit);
        Intent it3 = new Intent().setAction(BT_TEST_ACTION);
        PendingIntent pit3 = PendingIntent.getBroadcast(context, 0, it3, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.appwidget_testbtn,pit3);
        Intent it2 = new Intent(context,MainActivity.class);
        PendingIntent pit2 = PendingIntent.getActivity(context,0,it2,PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.search_btn,pit2);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override   
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("hshs","onReceive");
        Intent it = new Intent(context,MyService.class);
        it.setAction(action);
        context.startService(it);
//        AppWidgetManager awm=AppWidgetManager.getInstance(context);
//
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.remot_adbwidget);
//
//        if (action.equals(BT_REFRESH_ACTION)){
//            Toast.makeText(context, "点了一下！" + isOpened, Toast.LENGTH_SHORT).show();
//            if (!PreferenceUtils.getBoolean(context,"isopen",false)){
//                ShellUtils.CommandResult r = ShellUtils.execCommand("setprop service.adb.tcp.port 5555", true);
//                if (r.result < 0){
//                    views.setTextViewText(R.id.appwidget_text,r.errorMsg);
//                }else {
//                views.setTextViewText(R.id.appwidget_text,NetUtils.getWifiIp(context));
//                views.setTextViewText(R.id.start,"开启状态");
//                PreferenceUtils.putBoolean(context, "isopen", true);
//                }
//            }else {
//
//                ShellUtils.CommandResult r1 = ShellUtils.execCommand("stop adbd", true);
//                ShellUtils.CommandResult r2 = ShellUtils.execCommand("start adbd", true);
//                if (r1.result < 0 || r2.result < 0){
//                    views.setTextViewText(R.id.appwidget_text,r1.errorMsg);
//                }else {
//                    views.setTextViewText(R.id.start,"关闭状态");
//                    PreferenceUtils.putBoolean(context,"isopen",false);
//                }
//            }
//
//
//
//        }else if (action.equals(BT_SEARCH_ACTION)){
//            context.startActivity(new Intent(context,MainActivity.class));
//        }else{
//            Log.e("hshs","刷新界面！");
//        }
//        awm.updateAppWidget(widgetIds,views);
        super.onReceive(context, intent);
    }

    class SaveApplicationInfo extends AsyncTask<Context,Integer,Boolean>{
        @Override
        protected Boolean doInBackground(Context... params) {
            List<PackageInfo> list = PackageUtils.getInstalledApplication(params[0]);
            AppPacketManager manager = new AppPacketManager(params[0]);
            for (PackageInfo info : list){

                AppPackeget a = new AppPackeget(info.applicationInfo.name,info.packageName);
                manager.save(a);
            }
            return true;
        }
    }
}


