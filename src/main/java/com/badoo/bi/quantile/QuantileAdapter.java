package com.badoo.bi.quantile;

/**
 * An adopting interface to get quantile value from underlying implementation
 * Created by krash on 14.02.17.
 */
public interface QuantileAdapter {

    public double getQuantile(double quantile);
}
