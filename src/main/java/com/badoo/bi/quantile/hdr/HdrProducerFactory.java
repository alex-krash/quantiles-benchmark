package com.badoo.bi.quantile.hdr;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileProducer;

/**
 * Created by krash on 04.07.17.
 */
public class HdrProducerFactory implements ProducerFactory {
    @Override
    public QuantileProducer create(double maxError) {
        return new HdrProducer();
    }
}
