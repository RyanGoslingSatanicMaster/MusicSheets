package com.example.core_synth

@JvmInline
value class Hz(
    val value: Float
): Comparable<Hz>{

    inline operator fun plus(other: Hz) =
        Hz(value = this.value + other.value)

    /**
     * Subtract a Hz from another one.
     */
    inline operator fun minus(other: Hz) =
        Hz(value = this.value - other.value)

    /**
     * This is the same as multiplying the Hz by -1.0.
     */
    inline operator fun unaryMinus() = Hz(-value)


    inline operator fun div(other: Float): Hz =
        Hz(value = value / other)

    inline operator fun div(other: Int): Hz =
        Hz(value = value / other)

    /**
     * Divide by another Hz to get a scalar.
     */
    inline operator fun div(other: Hz): Hz = Hz(value / other.value)

    /**
     * Multiply a Hz by a scalar.
     */
    inline operator fun times(other: Float): Hz =
        Hz(value = value * other)

    inline operator fun times(other: Int): Hz =
        Hz(value = value * other)

    override fun compareTo(other: Hz): Int = value.compareTo(other.value)

    override fun toString() = if (isUnspecified) "Hz.Unspecified" else "$value.Hz"

    companion object {

        val Hairline = Hz(value = 0f)

        val Infinity = Hz(value = Float.POSITIVE_INFINITY)

        val Unspecified = Hz(value = Float.NaN)
    }
}

inline val Hz.isSpecified: Boolean
    get() = !value.isNaN()

inline val Hz.isUnspecified: Boolean
    get() = value.isNaN()

inline val Int.Hz: Hz get() = Hz(this.toFloat())

inline val Float.Hz: Hz get() = Hz(this)

inline val Double.Hz: Hz get() = Hz(this.toFloat())

inline val Long.Hz: Hz get() = Hz(this.toFloat())
