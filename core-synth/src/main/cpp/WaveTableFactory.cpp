#include "WaveTableFactory.h"
#include "Wavetable.h"
#include <cmath>
#include "MathConstants.h"

namespace {
    constexpr auto WAVETABLE_LENGTH = 256;

    std::vector<float> generateSineWaveTable() {
        auto sineWaveTable = std::vector<float>(WAVETABLE_LENGTH);

        for (auto  i = 0; i< WAVETABLE_LENGTH; ++i) {
            sineWaveTable[i] = std::sin(2 * core_synth::PI * static_cast<float>(i) / WAVETABLE_LENGTH);
        }

        return sineWaveTable;
    }

    std::vector<float> generateTriangleWaveTable() {
        auto triangleWaveTable = std::vector<float>(WAVETABLE_LENGTH, 0.f);

        constexpr auto HARMONICS_COUNT = 13;

        for (auto i = 1; i < HARMONICS_COUNT; ++i) {
            for (auto j = 0; j < WAVETABLE_LENGTH; ++j) {
                const auto phase = 2.f * core_synth::PI * j / WAVETABLE_LENGTH;
                triangleWaveTable[j] += 8.f / std::pow(core_synth::PI, 2.f)
                        * std::pow(-1.f, i) * std::pow(2.f * i - 1.f, -2.f)
                        * std::sin((2.f * i - 1.f) * phase);
            }
        }
        return triangleWaveTable;
    }

    std::vector<float> generateSqureWaveTable() {
        auto squareWaveTable = std::vector<float>(WAVETABLE_LENGTH, 0.f);

        constexpr auto HARMONICS_COUNT = 7;

        for (auto i = 1; i < HARMONICS_COUNT; ++i) {
            for (auto j = 0; j < WAVETABLE_LENGTH; ++j) {
                const auto phase = 2.f * core_synth::PI * j / WAVETABLE_LENGTH;
                squareWaveTable[j] += 4.f / core_synth::PI * std::pow(2.f * i - 1.f, -1.f)
                                        * std::sin((2.f * i - 1.f) * phase);
            }
        }
        return squareWaveTable;
    }

    std::vector<float> generateSawWaveTable() {
        auto sawWaveTable = std::vector<float>(WAVETABLE_LENGTH, 0.f);

        constexpr auto HARMONICS_COUNT = 26;

        for (auto i = 1; i < HARMONICS_COUNT; ++i) {
            for (auto j = 0; j < WAVETABLE_LENGTH; ++j) {
                const auto phase = 2.f * core_synth::PI * j / WAVETABLE_LENGTH;
                sawWaveTable[j] += 2.f / core_synth::PI
                                      * std::pow(-1.f, i) * std::pow(i, -1.f)
                                      * std::sin(i * phase);
            }
        }
        return sawWaveTable;
    }

    template <typename F>
    std::vector<float> generateWaveTableOnce(std::vector<float>& waveTable, F&& generator) {
        if (waveTable.empty()){
            waveTable = generator();
        }

        return waveTable;
    }
}

namespace core_synth{

    std::vector<float> WaveTableFactory::getWaveTable(WaveTable waveTable){
        switch (waveTable) {
            case WaveTable::SINE:
                return sineWaveTable();
            case WaveTable::TRIANGLE:
                return triangleWaveTable();
            case WaveTable::SAW:
                return sawWaveTable();
            case WaveTable::SQUARE:
                return squareWaveTable();
            case WaveTable::SAMPLE:
                return sampleWaveTable();
            case WaveTable::WHITE_NOISE:
                return whiteNoiseWaveTable();
            default:
                return std::vector<float>(WAVETABLE_LENGTH, 0.f);
        }
    }

    std::vector<float> WaveTableFactory::sineWaveTable(){
        return generateWaveTableOnce(_sineWaveTable, &generateSineWaveTable);
    }

    std::vector<float> WaveTableFactory::triangleWaveTable(){
        return generateWaveTableOnce(_triangleWaveTable, &generateTriangleWaveTable);
    }

    std::vector<float> WaveTableFactory::squareWaveTable(){
        return generateWaveTableOnce(_squareWaveTable, &generateSqureWaveTable);
    }

    std::vector<float> WaveTableFactory::sawWaveTable(){
        return generateWaveTableOnce(_sawWaveTable, &generateSawWaveTable);
    }

    std::vector<float> WaveTableFactory::sampleWaveTable(){
        return {};
    }

    std::vector<float> WaveTableFactory::whiteNoiseWaveTable(){
        return {};
    }
}
