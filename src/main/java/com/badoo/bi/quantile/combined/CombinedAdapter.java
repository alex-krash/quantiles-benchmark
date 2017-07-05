package com.badoo.bi.quantile.combined;

import com.badoo.bi.quantile.QuantileAdapter;
import org.HdrHistogram.DoubleHistogram;

import java.util.Arrays;

/**
 * Created by krash on 05.07.17.
 */
public class CombinedAdapter implements QuantileAdapter {

    double[] rawData;
    DoubleHistogram histogram;

    public CombinedAdapter(double[] rawData) {
        this.rawData = rawData;
    }

    public CombinedAdapter(DoubleHistogram histogram) {
        this.histogram = histogram;
    }

    @Override
    public double getQuantile(double quantile) {
        if (null != histogram) {
            return histogram.getValueAtPercentile(quantile * 100);
        } else {
            Arrays.sort(rawData);
            return rawData[Math.toIntExact(Math.round(Math.floor(rawData.length * quantile)))];
        }
    }
}
