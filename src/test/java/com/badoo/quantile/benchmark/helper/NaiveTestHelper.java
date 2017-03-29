package com.badoo.quantile.benchmark.helper;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileMerger;
import com.badoo.bi.quantile.naive.NaiveMerger;
import com.badoo.bi.quantile.naive.NaiveProducerFactory;

/**
 * Created by krash on 16.02.17.
 */
public class NaiveTestHelper implements TestHelper {
    @Override
    public ProducerFactory createProducerFactory() {
        return new NaiveProducerFactory();
    }

    @Override
    public QuantileMerger createMerger() {
        return new NaiveMerger();
    }
}
