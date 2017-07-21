package io.seniorzhai.cmake;

public class NativeBridge {
    static {
        System.loadLibrary("avutil");
        System.loadLibrary("fdk-aac");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("avfilter");
        System.loadLibrary("swresample");
        System.loadLibrary("swscale");
        System.loadLibrary("ffmpeg_jni");
    }

    public static native int initFFmpeg(boolean debug, String logPath);

    public static native int CMDRun(String cmd[]);

    public static native int nativeRelease();

}
