#include <jni.h>
#include <memory>
#include "Log.h"
#include "NativeSynthesizer.h"

extern "C" {

    JNIEXPORT jlong JNICALL
    Java_com_example_core_1synth_NativeSynthesizer_create(JNIEnv *env, jobject thiz) {

        auto synthesizer = std::make_unique<core_synth::NativeSynthesizer>();

        if (not synthesizer) {
            LOGD("Failed to create the synthesizer.");
            synthesizer.reset(nullptr);
        }

        return reinterpret_cast<jlong>(synthesizer.release());
    }

    JNIEXPORT void JNICALL
    Java_com_example_core_1synth_NativeSynthesizer_delete(JNIEnv *env, jobject thiz,
                                                          jlong synthesizer_handle) {

        auto *synthesizer = reinterpret_cast<core_synth::NativeSynthesizer*>(synthesizer_handle);

        if (not synthesizer) {
            LOGD("Attempt to destroy an uninitialized synthesizer.");
            return;
        }

        delete synthesizer;
    }

    JNIEXPORT void JNICALL
    Java_com_example_core_1synth_NativeSynthesizer_play(JNIEnv *env, jobject thiz,
                                                    jlong synthesizer_handle) {

        auto *synthesizer = reinterpret_cast<core_synth::NativeSynthesizer*>(synthesizer_handle);

        if (synthesizer) {
            synthesizer->play();
        } else {
            LOGD("Synthesizer not created.");
        }
    }

    JNIEXPORT void JNICALL
    Java_com_example_core_1synth_NativeSynthesizer_stop(JNIEnv *env, jobject thiz,
                                                    jlong synthesizer_handle) {

        auto *synthesizer = reinterpret_cast<core_synth::NativeSynthesizer*>(synthesizer_handle);

        if (synthesizer) {
            synthesizer->stop();
        } else {
            LOGD("Synthesizer not created.");
        }
    }

    JNIEXPORT jboolean JNICALL
    Java_com_example_core_1synth_NativeSynthesizer_isPlaying(JNIEnv *env, jobject thiz,
                                                         jlong synthesizer_handle) {

        auto *synthesizer = reinterpret_cast<core_synth::NativeSynthesizer*>(synthesizer_handle);

        if (synthesizer) {
            return synthesizer->isPlaying();
        } else {
            LOGD("Synthesizer not created.");
        }

        return false;
    }

    JNIEXPORT void JNICALL
    Java_com_example_core_1synth_NativeSynthesizer_setFrequency(JNIEnv *env, jobject thiz,
                                                            jlong synthesizer_handle,
                                                            jfloat frequency_in_hz) {

        auto *synthesizer = reinterpret_cast<core_synth::NativeSynthesizer*>(synthesizer_handle);

        if (synthesizer) {
            synthesizer->setFrequency(static_cast<float>(frequency_in_hz));
        } else {
            LOGD("Synthesizer not created.");
        }
    }

    JNIEXPORT void JNICALL
    Java_com_example_core_1synth_NativeSynthesizer_setVolume(JNIEnv *env, jobject thiz,
                                                         jlong synthesizer_handle,
                                                         jfloat volume_in_db) {

        auto *synthesizer = reinterpret_cast<core_synth::NativeSynthesizer*>(synthesizer_handle);

        if (synthesizer) {
            synthesizer->setVolume(static_cast<float>(volume_in_db));
        } else {
            LOGD("Synthesizer not created.");
        }
    }

    JNIEXPORT void JNICALL
    Java_com_example_core_1synth_NativeSynthesizer_setWaveTable(JNIEnv *env, jobject thiz,
                                                            jlong synthesizer_handle,
                                                            jint wavetable) {

        auto *synthesizer = reinterpret_cast<core_synth::NativeSynthesizer*>(synthesizer_handle);
        const auto nativeWavetable = static_cast<core_synth::WaveTable>(wavetable);
        if (synthesizer) {
            synthesizer->setWavetable(nativeWavetable);
        } else {
            LOGD("Synthesizer not created.");
        }
    }
}
