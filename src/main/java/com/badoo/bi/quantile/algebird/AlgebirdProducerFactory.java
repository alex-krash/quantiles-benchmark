package com.badoo.bi.quantile.algebird;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileProducer;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import com.twitter.algebird.QTreeSemigroup;
import com.twitter.algebird.javaapi.Monoids;

/**
 * Created by krash on 15.02.17.
 */
public class AlgebirdProducerFactory implements ProducerFactory {

    // Decision for
    private static final RangeMap<Double, Integer> RANGE_TO_LEVELS = TreeRangeMap.create();

    static {
        RANGE_TO_LEVELS.put(Range.closed(0.0, 0.005), 11);
        RANGE_TO_LEVELS.put(Range.openClosed(0.005, 0.01), 10);
        RANGE_TO_LEVELS.put(Range.openClosed(0.01, 0.015), 9);
        RANGE_TO_LEVELS.put(Range.openClosed(0.015, 0.02), 9);
    }

    @Override
    public QuantileProducer create(double maxError) {

        QTreeSemigroup<Double> group = createSummator(maxError);
        return new AlgebirdProducer(group);
    }

    private QTreeSemigroup<Double> createSummator(double maxError) {

        Integer level = RANGE_TO_LEVELS.get(maxError);
        if (null == level) {
            throw new IllegalArgumentException("Can not create computation algo for error " + maxError);
        }
        return new QTreeSemigroup<>(level, Monoids.doubleMonoid());
    }
}
