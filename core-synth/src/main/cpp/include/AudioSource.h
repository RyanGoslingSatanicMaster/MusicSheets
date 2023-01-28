#pragma once

namespace core_synth {

    class AudioSource {
    public:
        virtual ~AudioSource() = default;
        virtual float getSample() = 0;
        virtual void onPlaybackStopped() = 0;
    };
}
