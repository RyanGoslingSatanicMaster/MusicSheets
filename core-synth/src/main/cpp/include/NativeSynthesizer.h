#pragma once
// Means that include one time
#include "WaveTable.h"
#include <memory>
#include <mutex>
#include "WaveTableFactory.h"

namespace core_synth {

    class WaveOscillator;
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
        WaveTable getCurrentWaveTable() const;

    private:
        std::atomic<bool> _isPlaying = false;
        std::mutex _mutex;
        WaveTableFactory _waveTableFactory;
        WaveTable _currentWaveTable{WaveTable::SINE};
        std::shared_ptr<WaveOscillator> _oscillator;
        std::unique_ptr<AudioPlayer> _audioPlayer;
    };

}
