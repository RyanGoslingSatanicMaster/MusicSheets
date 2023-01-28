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
        return {};
    }

    std::vector<float> WaveTableFactory::squareWaveTable(){
        return {};
    }

    std::vector<float> WaveTableFactory::sawWaveTable(){
        return {};
    }

    std::vector<float> WaveTableFactory::sampleWaveTable(){
        return {};
    }

    std::vector<float> WaveTableFactory::whiteNoiseWaveTable(){
        return {};
    }
}
