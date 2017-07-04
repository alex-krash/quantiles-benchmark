package com.badoo.quantile.benchmark.helper;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileMerger;
import com.badoo.bi.quantile.hdr.HdrMerger;
import com.badoo.bi.quantile.hdr.HdrProducerFactory;

/**
 * Created by krash on 04.07.17.
 */
public class HdrTestHelper implements TestHelper {
    @Override
    public ProducerFactory createProducerFactory() {
        return new HdrProducerFactory();
    }

    @Override
    public QuantileMerger createMerger() {
        return new HdrMerger();
    }
}
