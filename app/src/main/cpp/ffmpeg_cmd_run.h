#ifndef FFMPEG_FFMPEG_RUN_H
#define FFMPEG_FFMPEG_RUN_H

#include <jni.h>

JNIEXPORT jint JNICALL
Java_io_seniorzhai_cmake_MainActivity_CMDRun(JNIEnv *env, jclass type,
                                                                       jobjectArray commands);

void log_callback(void* ptr, int level, const char* fmt,
                            va_list vl);

JNIEXPORT void JNICALL
Java_io_seniorzhai_cmake_MainActivity_initFFmpeg(JNIEnv *env, jclass type,
        jboolean debug,
jstring logUrl_);

int ffmpeg_cmd_run(int argc, char **argv);
#endif
