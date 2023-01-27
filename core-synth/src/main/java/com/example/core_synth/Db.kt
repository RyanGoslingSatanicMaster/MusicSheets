package com.example.core_synth

/** Decibel class
 *
 */
@JvmInline
value class Db(
    val value: Float
): Comparable<Db>{

    inline operator fun plus(other: Db) =
        Db(value = this.value + other.value)

    /**
     * Subtract a dB from another one.
     */
    inline operator fun minus(other: Db) =
        Db(value = this.value - other.value)

    /**
     * This is the same as multiplying the dB by -1.0.
     */
    inline operator fun unaryMinus() = Db(-value)


    inline operator fun div(other: Float): Db =
        Db(value = value / other)

    inline operator fun div(other: Int): Db =
        Db(value = value / other)

    /**
     * Divide by another dB to get a scalar.
     */
    inline operator fun div(other: Db): Db = Db(value / other.value)

    /**
     * Multiply a dB by a scalar.
     */
    inline operator fun times(other: Float): Db =
        Db(value = value * other)

    inline operator fun times(other: Int): Db =
        Db(value = value * other)

    override fun compareTo(other: Db): Int = value.compareTo(other.value)

    override fun toString() = if (isUnspecified) "dB.Unspecified" else "$value.dB"

    companion object {

        val Hairline = Db(value = 0f)

        val Infinity = Db(value = Float.POSITIVE_INFINITY)

        val Unspecified = Db(value = Float.NaN)
    }
}

inline val Db.isSpecified: Boolean
    get() = !value.isNaN()

inline val Db.isUnspecified: Boolean
    get() = value.isNaN()

inline val Int.dB: Db get() = Db(this.toFloat())

inline val Float.dB: Db get() = Db(this)

inline val Double.dB: Db get() = Db(this.toFloat())

inline val Long.dB: Db get() = Db(this.toFloat())
