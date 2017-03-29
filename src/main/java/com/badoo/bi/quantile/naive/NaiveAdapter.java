package com.badoo.bi.quantile.naive;

import com.badoo.bi.quantile.QuantileAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Naive implementation, holding all the values in array
 * Created by krash on 15.02.17.
 */
class NaiveAdapter implements QuantileAdapter {

    final double[] orderedSequence;

    public NaiveAdapter(double[] orderedSequence) {
        this.orderedSequence = orderedSequence;
    }

    public static NaiveAdapter create(List<Double> input) {
        double[] doubles = input.stream().mapToDouble(d -> d).toArray();
        Arrays.sort(doubles);
        return new NaiveAdapter(doubles);
    }

    @Override
    public double getQuantile(double quantile) {
        return orderedSequence[Math.toIntExact(Math.round(Math.floor(orderedSequence.length * quantile)))];
    }
}
