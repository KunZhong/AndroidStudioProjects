//
// Created by user on 17-4-19.
//
#include "com_example_staticjni_StaticJni.h"

JNIEXPORT jstring JNICALL Java_com_example_staticjni_StaticJni_getStringFromC
  (JNIEnv *env, jobject thiz){
    return (*env)->NewStringUTF(env,"hello jni");
}
