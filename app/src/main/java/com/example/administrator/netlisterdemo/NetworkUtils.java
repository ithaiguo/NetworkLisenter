package com.example.administrator.netlisterdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

/**
 * Created by Administrator on 2019/8/15.
 */

public class NetworkUtils {

    @TargetApi(Build.VERSION_CODES.M)
    public  static  boolean hasNetworkConnection(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

//        Network network = connectivityManager.getActiveNetwork();
//        Log.e("ppp", "hasNetworkConnection: "+ network);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();


//        Log.e("ppp", "hasNetworkConnection: "+ activeNetworkInfo);

        return (activeNetworkInfo!=null && activeNetworkInfo.isAvailable());
    }


    public static NetType getNetworkType(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null)
            return NetType.NONE;
        int type = activeNetworkInfo.getType();
        if (type == ConnectivityManager.TYPE_WIFI){
            return NetType.WIFI;
        }else if (type == ConnectivityManager.TYPE_MOBILE){
            return NetType.CMNET;
        }

        return NetType.NONE;
    }


}
