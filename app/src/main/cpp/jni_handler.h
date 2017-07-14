#ifndef FFMPEG_JNI_HANDLER_H
#define FFMPEG_JNI_HANDLER_H


#include "user_arguments.h"
class JXJNIHandler{
    ~JXJNIHandler(){
//        delete(arguments);
    }
public:
    void setup_video_state(int video_state);
    void setup_audio_state(int audio_state);
    int try_encode_over(UserArguments* arguments);
    void end_notify(UserArguments* arguments);

private:
    int start_muxer(UserArguments* arguments);
private:
    int video_state;
    int audio_state;

};

#endif
