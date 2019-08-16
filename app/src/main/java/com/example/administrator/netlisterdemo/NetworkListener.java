package com.example.administrator.netlisterdemo;

/**
 * Created by Administrator on 2019/8/15.
 */

public interface NetworkListener {

    void onConnect(NetType netType);

    void onDisConnect();

}

