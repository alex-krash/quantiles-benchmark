package com.badoo.bi.quantile.naive;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileProducer;
import com.facebook.presto.jdbc.internal.guava.collect.Iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by krash on 15.02.17.
 */
public class NaiveProducer implements QuantileProducer {
    @Override
    public QuantileAdapter compute(Iterator<Double> input) {

        List<Double> lst = new ArrayList<>();
        Iterators.addAll(lst, input);

        return NaiveAdapter.create(lst);
    }
}
