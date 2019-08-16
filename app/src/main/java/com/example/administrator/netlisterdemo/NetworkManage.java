package com.example.administrator.netlisterdemo;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

/**
 * Created by Administrator on 2019/8/15.
 */

public class NetworkManage {

    private static NetworkManage networkManage;
    private Application application;
    private NetStateReciver netStateReciver;


    private NetworkManage(){
        netStateReciver = new NetStateReciver();
    }

    public  static  NetworkManage getDefault(){
        if (networkManage == null){
            synchronized (NetworkManage.class){
                if (networkManage == null){
                    networkManage = new NetworkManage();
                }
            }
        }

        return networkManage;
    }

    public Application getApplication(){
        if (application == null)
            throw  new RuntimeException("app 未初始化");

        return application;
    }

    public void setListener(NetworkListener listener){
        netStateReciver.setNetworkLisenter(listener);
    }

    public void init(Application application){
        this.application = application;
        register();
    }

    public void register(){
        netStateReciver = new NetStateReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contanst.NET_ACTION);
        application.registerReceiver(netStateReciver,intentFilter);
    }

}
