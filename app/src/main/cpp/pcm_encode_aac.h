#ifndef FFMPEG_PCM_ENCODE_AAC_H
#define FFMPEG_PCM_ENCODE_AAC_H


#include "base_include.h"
#include "user_arguments.h"
using namespace std;

/**
 * pcm编码为aac
 */
class JXPCMEncodeAAC {
public:
    JXPCMEncodeAAC(UserArguments* arg);
public:
    int initAudioEncoder();

    static void* startEncode(void* obj);

    void user_end();

    void release();

    int sendOneFrame(uint8_t* buf);

    int encodeEnd();
    ~JXPCMEncodeAAC() {
    }
private:
    int flush_encoder(AVFormatContext *fmt_ctx, unsigned int stream_index);

private:
    threadsafe_queue<uint8_t *> frame_queue;
    AVFormatContext *pFormatCtx;
    AVOutputFormat *fmt;
    AVStream *audio_st;
    AVCodecContext *pCodecCtx;
    AVCodec *pCodec;

    AVFrame *pFrame;
    AVPacket pkt;

    int got_frame = 0;
    int ret = 0;
    int size = 0;

    int i;
    int is_end=FALSE;
    int is_release=FALSE;
    UserArguments *arguments;

};

#endif
