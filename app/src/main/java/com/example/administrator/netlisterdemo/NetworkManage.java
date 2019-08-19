package com.example.administrator.netlisterdemo;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import com.example.administrator.netlisterdemo.annotation.NetStateReflectReciver;

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
//        initreciver();
        initreciverannotation();
    }

    public void initreciver(){
        netStateReciver = new NetStateReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contanst.NET_ACTION);
        application.registerReceiver(netStateReciver,intentFilter);
    }


    /***
     * 反射注解方法
     * 分割线 =================================================================================
     */
    private NetStateReflectReciver netStateReflectReciver;
    public void initreciverannotation() {
        netStateReflectReciver = new NetStateReflectReciver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contanst.NET_ACTION);
        application.registerReceiver(netStateReflectReciver,intentFilter);
    }

    public void register(Object object) {
        netStateReflectReciver.register(object);
    }

    public void unregister(Object object) {
        if (netStateReflectReciver!=null){
            application.unregisterReceiver(netStateReflectReciver);
            netStateReflectReciver.unregister(object);
        }
    }
}
