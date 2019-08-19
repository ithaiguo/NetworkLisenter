package com.example.administrator.netlisterdemo.annotation;

import com.example.administrator.netlisterdemo.NetType;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2019/8/16.
 */

public class MethodManage {
    private Method method;

//    private Class<?> clazz;
    private NetType netType;

    public MethodManage(Method method, NetType netType) {
        this.method = method;
//        this.clazz = clazz;
        this.netType = netType;
    }

    public void invoekMethod(){
//        method.invoke()
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

//    public Class<?> getClazz() {
//        return clazz;
//    }
//
//    public void setClazz(Class<?> clazz) {
//        this.clazz = clazz;
//    }

    public NetType getNetType() {
        return netType;
    }

    public void setNetType(NetType netType) {
        this.netType = netType;
    }
}
