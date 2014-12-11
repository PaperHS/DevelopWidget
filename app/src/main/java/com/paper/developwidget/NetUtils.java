package com.paper.developwidget;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Paper on 14-12-11.
 */
public class NetUtils {
    public static String getWifiIp(Context context){

        WifiManager wifimanger = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiinfo = wifimanger.getConnectionInfo();
        String ip = intToIp(wifiinfo.getIpAddress());  //注：getIpAddress获取的为int型需要用intToIp方法
        return ip;
    }
    private static String intToIp(int i)
    {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }
}
