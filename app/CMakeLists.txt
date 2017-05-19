# CMake使用的最小版本
cmake_minimum_required(VERSION 3.4.1)

# 创建so库无关而是用NDK的Apis或者库
# log-lib是log库的位置
find_library(
              log-lib
              log )

add_library( avutil-55
             SHARED
             IMPORTED )
set_target_properties( avutil-55
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavutil-55.so )
add_library( swresample-2
             SHARED
             IMPORTED )
set_target_properties( swresample-2
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libswresample-2.so )
add_library( avcodec-57
             SHARED
             IMPORTED )
set_target_properties( avcodec-57
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavcodec-57.so )
add_library( avfilter-6
             SHARED
             IMPORTED)
set_target_properties( avfilter-6
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavfilter-6.so )
add_library( swscale-4
             SHARED
             IMPORTED)
set_target_properties( swscale-4
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libswscale-4.so )
add_library( avdevice-57
             SHARED
             IMPORTED)
set_target_properties( avdevice-57
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavdevice-57.so )
add_library( avformat-57
             SHARED
             IMPORTED)
set_target_properties( avformat-57
                       PROPERTIES IMPORTED_LOCATION
                       ../../../../libs/armeabi-v7a/libavformat-57.so )

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11")

# 配置so库信息
# native-lib 声明引用so库的名称
# SHARED表示共享so库文件
# 最后是构建so库的源文件
add_library(
             native-lib
             SHARED
             src/main/cpp/native-lib.cpp )
# STATIC 表示静态库，在链接其他目标的时候使用
# SHARED 表示动态库，会被动态链接，在运行时加载
# MODULE 模块库不会被链接到其他目标中，可能会在运行时使用dlopen-系列函数动态链接

# 配置头文件
# include_directories([AFTER|BEFORE][SYSTEM]dir[dir...])
include_directories(libs/include)

# 本地库调用了log库的方法，关联到本地库
target_link_libraries(
                       native-lib
                       swresample-2 avcodec-57 avfilter-6 swscale-4 avdevice-57 avformat-57
                       ${log-lib} )

# 使用第三方so库，只需要引入不需要构建
# add_library( imported-lib
#             SHARED
#             IMPORTED
# )
# 同时需要指定so库的路径
# set_target_properties(target1 target2 ...
#                    PROPERTIES prop1 value1
#                    prop2 value2 ...)
# 使用已经存在的so库，不应该配置target_link_libraries()方法，应该其不需要build