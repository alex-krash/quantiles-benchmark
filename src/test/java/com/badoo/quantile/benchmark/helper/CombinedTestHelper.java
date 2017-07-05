package com.badoo.quantile.benchmark.helper;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileMerger;
import com.badoo.bi.quantile.combined.CombinedMerger;
import com.badoo.bi.quantile.combined.CombinedProducerFactory;

/**
 * Created by krash on 05.07.17.
 */
public class CombinedTestHelper implements TestHelper {
    @Override
    public ProducerFactory createProducerFactory() {
        return new CombinedProducerFactory();
    }

    @Override
    public QuantileMerger createMerger() {
        return new CombinedMerger();
    }
}
