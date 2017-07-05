package com.badoo.bi.quantile.combined;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileProducer;

/**
 * Created by krash on 05.07.17.
 */
public class CombinedProducerFactory implements ProducerFactory {
    @Override
    public QuantileProducer create(double maxError) {
        return new CombinedProducer();
    }
}
