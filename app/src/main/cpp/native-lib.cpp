#include <jni.h>
#include <string>

/*extern "C" jstring
Java_com_es_news_utility_Constants_getBaseURL(JNIEnv *env, jobject thiz) {
    std::string baseUrl = "https://newsapi.org/v2/";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" jstring
Java_com_es_news_utility_Constants_getApiKey(JNIEnv *env, jobject thiz) {
    std::string apiKey = "9390c529f6aa4cb9a7be3d19832cd2d5";
    return env->NewStringUTF(apiKey.c_str());
}*/

extern "C"
JNIEXPORT jstring JNICALL
Java_com_es_news_utility_Constants_getBaseURL(JNIEnv *env, jobject thiz) {
    std::string baseUrl = "https://newsapi.org/v2/";
    return env->NewStringUTF(baseUrl.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_es_news_utility_Constants_getApiKey(JNIEnv *env, jobject thiz) {
    std::string apiKey = "9390c529f6aa4cb9a7be3d19832cd2d5";
    return env->NewStringUTF(apiKey.c_str());
}