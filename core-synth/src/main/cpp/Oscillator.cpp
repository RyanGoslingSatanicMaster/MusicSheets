#include "Oscillator.h"
#include <cmath>
#include "MathConstants.h"

namespace core_synth{

    WaveOscillator::WaveOscillator(std::vector<float> waveTable, float sampleRate)
    : waveTable{std::move(waveTable)}, sampleRate(sampleRate) {}

    float WaveOscillator::getSample() {
        swapWavetableIfNecessary();

        index = std::fmod(index, static_cast<float>(waveTable.size()));
        const auto sample = interpolateLineary();
        index += indexIncrement;
        return amplitude * sample;
    }

    float WaveOscillator::interpolateLineary() const {
        const auto truncatedIndex = static_cast<typename decltype(waveTable)::size_type>(index);
        const auto nextIndex = (truncatedIndex + 1u) % waveTable.size();
        const auto nextIndexWeight = index - static_cast<float>(truncatedIndex);
        return waveTable[nextIndex] * nextIndexWeight + (1.f - nextIndexWeight) * waveTable[truncatedIndex];
    }

    void WaveOscillator::swapWavetableIfNecessary() {
        waveTableIsBeingSwapped.store(true, std::memory_order_release);
        if (swapWaveTable.load(std::memory_order_acquire)) {
            std::swap(waveTable, waveTableToSwap);
            swapWaveTable.store(false, std::memory_order_relaxed);
        }
        waveTableIsBeingSwapped.store(false, std::memory_order_release);
    }

    void WaveOscillator::setWaveTable(const std::vector<float> &waveTable) {
        swapWaveTable.store(false, std::memory_order_release);
        while(waveTableIsBeingSwapped.load(std::memory_order_acquire)) { }
        waveTableToSwap = waveTable;
        swapWaveTable.store(true, std::memory_order_release);
    }

    void WaveOscillator::setFrequency(float frequency) {
        indexIncrement = frequency * static_cast<float>(waveTable.size()) /
                static_cast<float>(sampleRate);
    }

    void WaveOscillator::setAmplitude(float newAmplitude) {
        amplitude.store(newAmplitude);
    }

    void WaveOscillator::onPlaybackStopped() {
        index = 0.f;
    }

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
