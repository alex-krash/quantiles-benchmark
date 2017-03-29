package com.badoo.bi.quantile;

/**
 * Interface for candidates to provide quantile algorythm with given accuracy
 * Created by krash on 15.02.17.
 */
public interface ProducerFactory {

    public QuantileProducer create(double maxError);

}
