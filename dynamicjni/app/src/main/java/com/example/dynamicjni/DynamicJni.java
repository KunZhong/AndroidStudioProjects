package com.example.dynamicjni;

/**
 * Created by user on 17-4-19.
 */

public class DynamicJni {
    public native String native_hello();
    static {
        System.loadLibrary("hello-jni");
    }
}
