package com.example.administrator.netlisterdemo;

import android.app.Application;

/**
 * Created by Administrator on 2019/8/15.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NetworkManage.getDefault().init(this);
    }
}
