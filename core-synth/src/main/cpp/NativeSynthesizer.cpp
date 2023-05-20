#include <cmath>
#include "Log.h"
#include "include/NativeSynthesizer.h"
#include "OboeAudioPlayer.h"
#include "Oscillator.h"


namespace core_synth{

    NativeSynthesizer::NativeSynthesizer()
    : _oscillator{std::make_shared<WaveOscillator>(
            _waveTableFactory.getWaveTable(_currentWaveTable),
            samplingRate)},
        _audioPlayer(std::make_unique<OboeAudioPlayer>(_oscillator, samplingRate)) {}

    NativeSynthesizer::~NativeSynthesizer() = default;

    void NativeSynthesizer::play(){
        std::lock_guard<std::mutex> lock(_mutex);
        const auto  result = _audioPlayer->play();
        // 0 = SUCCESS code
        if (result == 0) {
            _isPlaying = true;
        } else {
            LOGD("Could not start playback.");
        }
    }

    void NativeSynthesizer::stop(){
        std::lock_guard<std::mutex> lock(_mutex);
        _audioPlayer->stop();
        _isPlaying = false;
    }

    bool NativeSynthesizer::isPlaying() const{
        return _isPlaying;
    }

    void NativeSynthesizer::setFrequency(float frequencyInHz){
        _oscillator->setFrequency(frequencyInHz);
    }

    float dBToAmplitude(float dB) {
        return std::pow(10.f, dB/20.f);
    }

    void NativeSynthesizer::setVolume(float volumeInDb){
        const auto amplitude = dBToAmplitude(volumeInDb);
        _oscillator->setAmplitude(amplitude);
    }

    void NativeSynthesizer::setWavetable(WaveTable waveTable){
        if (_currentWaveTable != waveTable) {
            _currentWaveTable = waveTable;
            _oscillator->setWaveTable(_waveTableFactory.getWaveTable(waveTable));
        }
    }

    WaveTable NativeSynthesizer::getCurrentWaveTable() const{
        return _currentWaveTable;
    }
}


