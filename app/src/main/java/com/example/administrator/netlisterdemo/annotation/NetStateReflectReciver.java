package com.example.administrator.netlisterdemo.annotation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.netlisterdemo.NetType;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/8/16.
 */

public class NetStateReflectReciver extends BroadcastReceiver{
    private NetType netType;

    private Map<Object,List<MethodManage>> networklist;

    private NetStateReflectReciver(){
        this.netType = NetType.NONE;
        this.networklist = new HashMap<>();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    public void register(Object object){
        Class<?> clazz = object.getClass();
        Annotation[] annotations = clazz.getAnnotations();
        Log.e("ppp", "register: "+annotations );
    }
}
