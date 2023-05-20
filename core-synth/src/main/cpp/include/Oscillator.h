#pragma once

#include <vector>
#include <atomic>
#include "AudioSource.h"

namespace core_synth {

    class WaveOscillator: public AudioSource{
    public:
        WaveOscillator() = default;
        WaveOscillator(std::vector<float> waveTable, float sampleRate);

        float getSample() override;

        void onPlaybackStopped() override;

        virtual void setFrequency(float frequency);
        virtual void setAmplitude(float newAmplitude);
        virtual void setWaveTable(const std::vector<float>& waveTable);

    private:
        float interpolateLineary() const;
        void swapWavetableIfNecessary();

        float index = 0.f;
        std::atomic<float> indexIncrement{0.f};
        std::vector<float> waveTable;
        float sampleRate;
        std::atomic<float> amplitude{1.f};

        std::atomic<bool> swapWaveTable{false};
        std::vector<float> waveTableToSwap;
        std::atomic<bool> waveTableIsBeingSwapped{false};
    };

    class A4Oscillator : public AudioSource {

    public:
        explicit A4Oscillator(float sampleRate);

        float getSample() override;

        void onPlaybackStopped() override;

    private:
        float _phase{0.f};
        float _phaseIncrement{0.f};
    };
}
