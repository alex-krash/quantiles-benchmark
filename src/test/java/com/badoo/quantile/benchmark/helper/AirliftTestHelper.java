package com.badoo.quantile.benchmark.helper;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileMerger;
import com.badoo.bi.quantile.airlift.AirliftMerger;
import com.badoo.bi.quantile.airlift.AirliftProducerFactory;

/**
 * Created by krash on 16.02.17.
 */
public class AirliftTestHelper implements TestHelper {
    @Override
    public ProducerFactory createProducerFactory() {
        return new AirliftProducerFactory();
    }

    @Override
    public QuantileMerger createMerger() {
        return new AirliftMerger();
    }
}
