package com.badoo.bi.quantile.tdunning;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileProducer;

/**
 * Created by krash on 15.02.17.
 */
public class TDunningProducerFactory implements ProducerFactory {

    @Override
    public QuantileProducer create(double maxError) {

        return new TDunningProducer(110);
    }
}
