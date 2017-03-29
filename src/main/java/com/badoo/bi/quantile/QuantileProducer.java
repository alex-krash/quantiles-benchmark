package com.badoo.bi.quantile;

import java.util.Iterator;

/**
 * A wrapper for underlying algorithm for quantile calculation
 * Created by krash on 15.02.17.
 */
public interface QuantileProducer {

    public QuantileAdapter compute(Iterator<Double> input);

}
