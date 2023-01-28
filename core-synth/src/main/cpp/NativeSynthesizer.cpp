#include "Log.h"
#include "include/NativeSynthesizer.h"
#include "OboeAudioPlayer.h"
#include "Oscillator.h"


namespace core_synth{

    NativeSynthesizer::NativeSynthesizer()
    : _oscillator{std::make_shared<A4Oscillator>(samplingRate)},
        _audioPlayer(std::make_unique<OboeAudioPlayer>(_oscillator, samplingRate)) {}

    NativeSynthesizer::~NativeSynthesizer() = default;

    void NativeSynthesizer::play(){
        LOGD("Play() called. C++");
        const auto  result = _audioPlayer->play();
        // 0 = SUCCESS code
        if (result == 0) {
            _isPlaying = true;
        } else {
            LOGD("Could not start playback.");
        }
    }

    void NativeSynthesizer::stop(){
        LOGD("Stop() called. C++");
        _audioPlayer->stop();
        _isPlaying = false;
    }

    bool NativeSynthesizer::isPlaying() const{
        LOGD("isPlaying() called. C++");
        return _isPlaying;
    }

    void NativeSynthesizer::setFrequency(float frequencyInHz){
        LOGD("setFrequency() called with %.2f Hz. C++", frequencyInHz);
    }

    void NativeSynthesizer::setVolume(float volumeInDb){
        LOGD("setVolume() called with %.2f dB. C++", volumeInDb);
    }

    void NativeSynthesizer::setWavetable(WaveTable waveTable){
        LOGD("setWavetable() called with %.d. C++" , static_cast<int>(waveTable));
    }
}


