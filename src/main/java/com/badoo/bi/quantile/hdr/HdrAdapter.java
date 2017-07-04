package com.badoo.bi.quantile.hdr;

import com.badoo.bi.quantile.QuantileAdapter;
import com.google.common.annotations.VisibleForTesting;
import org.HdrHistogram.DoubleHistogram;

/**
 * Created by krash on 04.07.17.
 */
public class HdrAdapter implements QuantileAdapter {

    @VisibleForTesting
    public final DoubleHistogram histogram;

    public HdrAdapter(DoubleHistogram histogram) {
        this.histogram = histogram;
    }

    @Override
    public double getQuantile(double quantile) {
        return histogram.getValueAtPercentile(quantile * 100);
    }
}
