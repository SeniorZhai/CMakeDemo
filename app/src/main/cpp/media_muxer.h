#ifndef FFMPEG_MEDIA_MUXER_H
#define FFMPEG_MEDIA_MUXER_H


#include "base_include.h"
#define USE_H264BSF 1
#define USE_AACBSF 1


class JXMediaMuxer{
public:
    int startMuxer(const char * video, const char *audio , const char *out_file);

private:

};

#endif
