package com.example.administrator.netlisterdemo.annotation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.util.Log;

import com.example.administrator.netlisterdemo.Contanst;
import com.example.administrator.netlisterdemo.NetType;
import com.example.administrator.netlisterdemo.NetworkUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2019/8/16.
 */

public class NetStateReflectReciver extends BroadcastReceiver{
    private NetType netType;

    private Map<Object,List<MethodManage>> networkMap;

    public NetStateReflectReciver(){
        this.netType = NetType.NONE;
        this.networkMap = new HashMap<>();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction()==null){
            Log.e("ppp", "onReceive: 广播接收异常");
            return;
        }

        if (networkMap.isEmpty()){
            return;
        }

        if (Contanst.NET_ACTION.equalsIgnoreCase(intent.getAction())){
            netType = NetworkUtils.getNetworkType(context);



            Set<Map.Entry<Object, List<MethodManage>>> entrySet = networkMap.entrySet();
            for (Map.Entry entry:entrySet){
                Object key = entry.getKey();
                List<MethodManage> value = (List<MethodManage>) entry.getValue();

                Log.e("ppp", "onReceive: "+key.getClass().getSimpleName());

                for (MethodManage methodManage:value){

                    if (methodManage.getNetType().equals(netType)){
                        Log.e("ppp", "onReceive: "+methodManage.getNetType());
                        try {
                            methodManage.getMethod().invoke(key);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void register(Object object){

        if (networkMap.get(object) == null){



            Class<?> clazz = object.getClass();
            Method[] methods = clazz.getDeclaredMethods();

            List<MethodManage> methodList = new ArrayList<>();
            networkMap.put(object,methodList);

    //        Log.e("ppp", "register: "+methods.length );
            for (Method method: methods){
    //            Log.e("ppp", "register: "+method.getName());
                Annotation[] annotations = method.getAnnotations();

                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 0){
    //                Class<?> clzz = parameterTypes[0];
                    for (Annotation annotation:annotations){
                        if (annotation.annotationType() == NetworkSubscribe.class){
                            NetworkSubscribe networkSubscribe = (NetworkSubscribe) annotation;
                            NetType annotationNettype = networkSubscribe.nettype();
                            MethodManage methodManage = new MethodManage(method,annotationNettype);

                            methodList.add(methodManage);
                        }
                    }
                }

            }
        }


    }

    public void unregister(Object object) {
        if (networkMap.get(object) != null){
            networkMap.remove(object);
        }
    }
}
