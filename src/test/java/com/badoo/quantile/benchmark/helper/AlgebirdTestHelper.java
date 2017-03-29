package com.badoo.quantile.benchmark.helper;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileMerger;
import com.badoo.bi.quantile.algebird.AlgebirdMerger;
import com.badoo.bi.quantile.algebird.AlgebirdProducerFactory;
import com.twitter.algebird.QTreeSemigroup;
import com.twitter.algebird.javaapi.Monoids;

/**
 * Created by krash on 16.02.17.
 */
public class AlgebirdTestHelper implements TestHelper {
    @Override
    public ProducerFactory createProducerFactory() {
        return new AlgebirdProducerFactory();
    }

    @Override
    public QuantileMerger createMerger() {
        return new AlgebirdMerger(new QTreeSemigroup<>(10, Monoids.doubleMonoid()));
    }
}
