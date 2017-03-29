package com.badoo.quantile.benchmark.helper;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileMerger;
import com.badoo.bi.quantile.tdunning.TDunningMerger;
import com.badoo.bi.quantile.tdunning.TDunningProducerFactory;

/**
 * Created by krash on 16.02.17.
 */
public class TDunningTestHelper implements TestHelper {
    @Override
    public ProducerFactory createProducerFactory() {
        return new TDunningProducerFactory();
    }

    @Override
    public QuantileMerger createMerger() {
        return new TDunningMerger();
    }
}
