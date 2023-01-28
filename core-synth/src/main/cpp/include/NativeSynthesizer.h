#pragma once
// Means that include one time
#include "WaveTable.h"
#include <memory>

namespace core_synth {

    class AudioSource;
    class AudioPlayer;

    constexpr auto samplingRate = 48000;

    class NativeSynthesizer {
    public:
        NativeSynthesizer();
        ~NativeSynthesizer();
        void play();
        void stop();
        bool isPlaying() const;
        void setFrequency(float frequencyInHz);
        void setVolume(float volumeInDb);
        void setWavetable(WaveTable waveTable);

    private:
        bool _isPlaying = false;
        std::shared_ptr<AudioSource> _oscillator;
        std::unique_ptr<AudioPlayer> _audioPlayer;
    };

}
