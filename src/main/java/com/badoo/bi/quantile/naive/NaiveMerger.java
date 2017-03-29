package com.badoo.bi.quantile.naive;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileMerger;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * Created by krash on 16.02.17.
 */
public class NaiveMerger implements QuantileMerger {

    private NaiveAdapter apply(NaiveAdapter one, NaiveAdapter two) {

        double[] merged = ArrayUtils.addAll(one.orderedSequence, two.orderedSequence);
        Arrays.sort(merged);

        return new NaiveAdapter(merged);
    }

    @Override
    public QuantileAdapter apply(QuantileAdapter one, QuantileAdapter two) {

        return apply((NaiveAdapter) one, (NaiveAdapter) two);
    }
}
