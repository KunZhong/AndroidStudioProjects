package com.example.staticjni;

/**
 * Created by user on 17-4-19.
 */

public class StaticJni {
    public native String getStringFromC();
    static {
        System.loadLibrary("hello-jni");
    }
}
