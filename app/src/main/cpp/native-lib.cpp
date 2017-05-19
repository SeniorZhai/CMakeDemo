#include <jni.h>
#include <string>

// 按照C语言进行编译 保证C++能够调用
extern "C" {
#include <libavcodec/avcodec.h>

JNIEXPORT jstring JNICALL
Java_io_seniorzhai_cmake_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(avcodec_configuration());
}
}