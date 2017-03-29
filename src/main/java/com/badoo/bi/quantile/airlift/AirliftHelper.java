package com.badoo.bi.quantile.airlift;

/**
 * Created by krash on 15.02.17.
 */
public abstract class AirliftHelper {
    public static long doubleToSortableLong(double value) {
        long bits = Double.doubleToLongBits(value);
        return bits ^ (bits >> 63) & Long.MAX_VALUE;
    }

    public static double sortableLongToDouble(long value) {
        value = value ^ (value >> 63) & Long.MAX_VALUE;
        return Double.longBitsToDouble(value);
    }
}
