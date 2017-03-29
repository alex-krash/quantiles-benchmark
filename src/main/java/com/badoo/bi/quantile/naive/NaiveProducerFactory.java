package com.badoo.bi.quantile.naive;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileProducer;

/**
 * Created by krash on 15.02.17.
 */
public class NaiveProducerFactory implements ProducerFactory {
    @Override
    public QuantileProducer create(double maxError) {
        return new NaiveProducer();
    }
}
