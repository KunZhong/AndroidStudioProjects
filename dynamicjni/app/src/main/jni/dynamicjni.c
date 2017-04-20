//
// Created by user on 17-4-19.
//
#include <jni.h>

JNIEXPORT jstring JNICALL jninative_hello(JNIEnv *env, jclass clazz) {
    return (*env)->NewStringUTF(env,"Hello from C");
}

static JNINativeMethod nativeMethod[] = {
        {"native_hello", "()Ljava/lang/String;", (void *) jninative_hello}
};

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
    JNIEnv *env;
    if ((*jvm) -> GetEnv(jvm, (void**) &env, JNI_VERSION_1_4) != JNI_OK)
    {
        return -1;
    }

    jclass clz = (*env) -> FindClass(env, "com/example/dynamicjni/DynamicJni");

    (*env) -> RegisterNatives(env, clz, nativeMethod, sizeof(nativeMethod) / sizeof(nativeMethod[0]));

    return JNI_VERSION_1_4;
}
