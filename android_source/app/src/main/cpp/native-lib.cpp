#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_comquas_tgif3_1demo_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_comquas_tgif3_1demo_HashNative_getNativeKey(
        JNIEnv* env,
jobject /* this */) {
std::string nativeKey = "234213";
return env->NewStringUTF(nativeKey.c_str());
}
