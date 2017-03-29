package com.badoo.quantile.benchmark.helper;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileMerger;

/**
 * Created by krash on 16.02.17.
 */
public interface TestHelper {

    public ProducerFactory createProducerFactory();

    public QuantileMerger createMerger();

}
