package com.example.core_synth

enum class WaveTable {
    SINE,
    TRIANGLE,
    SQUARE,
    SAW,
    WHITE_NOISE,
    SAMPLE,
    NONE;

    companion object {
        fun getByOrdinal(ordinal: Int): WaveTable{
            return when(ordinal){
                SINE.ordinal -> SINE
                TRIANGLE.ordinal -> TRIANGLE
                SQUARE.ordinal -> SQUARE
                SAW.ordinal -> SAW
                WHITE_NOISE.ordinal -> WHITE_NOISE
                SAMPLE.ordinal -> SAMPLE
                else -> NONE
            }
        }
    }
}
