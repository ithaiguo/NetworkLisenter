package com.example.administrator.netlisterdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2019/8/15.
 */

public class NetStateReciver extends BroadcastReceiver {
    private NetType netType;
    private NetworkListener listener;
    public NetStateReciver(){
        this.netType = NetType.NONE;
    }

    public void setNetworkLisenter(NetworkListener listener){
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        Log.e("ppp", "onReceive: ");
        if (intent == null || intent.getAction()==null){
            Log.e("ppp", "onReceive: 广播接收异常");
            return;
        }

        if (Contanst.NET_ACTION.equalsIgnoreCase(intent.getAction())){
            if (NetworkUtils.hasNetworkConnection(context)){
                NetType netType = NetworkUtils.getNetworkType(context);
                if (listener != null){
                    listener.onConnect(netType);
                }
            }else {
                if (listener !=null)
                    listener.onDisConnect();
            }
        }


    }
}
