#include <fcntl.h>
#include <sys/ioctl.h>
#include <sys/socket.h>
#include <unistd.h>
#include <jni.h>

JNIEXPORT jint JNICALL Java_eu_bebendorf_linuxio_LinuxIO_ioctl(JNIEnv* env, jobject thisObject, jint fd, jlong request, jbyteArray data) {
    jboolean isCopy;
    jbyte* b = (*env)->GetByteArrayElements(env, data, &isCopy);
    jsize len = (*env)->GetArrayLength(env, data);
    int err = ioctl(fd, request, b);
    (*env)->SetByteArrayRegion(env, data, 0, len, b);
    (*env)->ReleaseByteArrayElements(env, data, b, 0);
    return err;
}

JNIEXPORT jint JNICALL Java_eu_bebendorf_linuxio_LinuxIO_open(JNIEnv* env, jobject thisObject, jstring path, jint mode) {
    const char* pathStr = (*env)->GetStringUTFChars(env, path, 0);
    int fd = open(pathStr, mode);
    (*env)->ReleaseStringUTFChars(env, path, pathStr);
    return fd;
}

JNIEXPORT jint JNICALL Java_eu_bebendorf_linuxio_LinuxIO_socket(JNIEnv* env, jobject thisObject, jint family, jint type, jint protocol) {
    return socket(family, type, protocol);
}

JNIEXPORT jint JNICALL Java_eu_bebendorf_linuxio_LinuxIO_close(JNIEnv* env, jobject thisObject, jint fd) {
    return close(fd);
}

JNIEXPORT jint JNICALL Java_eu_bebendorf_linuxio_LinuxIO_read(JNIEnv* env, jobject thisObject, jint fd, jbyteArray buffer, jint length) {
    jboolean isCopy;
    jbyte* b = (*env)->GetByteArrayElements(env, buffer, &isCopy);
    int r = read(fd, b, length);
    (*env)->SetByteArrayRegion(env, buffer, 0, r, b);
    (*env)->ReleaseByteArrayElements(env, buffer, b, 0);
    return r;
}

JNIEXPORT jint JNICALL Java_eu_bebendorf_linuxio_LinuxIO_write(JNIEnv* env, jobject thisObject, jint fd, jbyteArray buffer, jint length) {
    jboolean isCopy;
    jbyte* b = (*env)->GetByteArrayElements(env, buffer, &isCopy);
    int r = write(fd, b, length);
    (*env)->ReleaseByteArrayElements(env, buffer, b, 0);
    return r;
}