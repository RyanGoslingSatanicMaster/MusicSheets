#include "Oscillator.h"
#include <cmath>
#include "MathConstants.h"

namespace core_synth{

    A4Oscillator::A4Oscillator(float sampleRate)
        : _phaseIncrement{440.f / sampleRate * 2.f * PI} {}

    float A4Oscillator::getSample() {
        const auto sample = 0.5f * std::sin(_phase);
        _phase = std::fmod(_phase + _phaseIncrement, 2.f * PI);
        return sample;
    }

    void A4Oscillator::onPlaybackStopped() {
        _phase = 0.f;
    }
}
