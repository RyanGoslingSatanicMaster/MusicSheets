#pragma once
#include <vector>

namespace core_synth {
    enum class WaveTable;
    class WaveTableFactory {
    public:
        std::vector<float> getWaveTable(WaveTable waveTable);

    private:
        std::vector<float> sineWaveTable();
        std::vector<float> triangleWaveTable();
        std::vector<float> squareWaveTable();
        std::vector<float> sawWaveTable();
        std::vector<float> sampleWaveTable();
        std::vector<float> whiteNoiseWaveTable();

        std::vector<float> _sineWaveTable;
        std::vector<float> _triangleWaveTable;
        std::vector<float> _squareWaveTable;
        std::vector<float> _sawWaveTable;
        std::vector<float> _sampleWaveTable;
        std::vector<float> _whiteNoiseWaveTable;
    };
}
