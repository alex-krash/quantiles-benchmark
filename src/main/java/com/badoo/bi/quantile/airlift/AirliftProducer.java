package com.badoo.bi.quantile.airlift;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileProducer;
import com.facebook.presto.jdbc.internal.airlift.stats.QuantileDigest;

import java.util.Iterator;

/**
 * Created by krash on 15.02.17.
 */
public class AirliftProducer implements QuantileProducer {

    private final double maxError;

    public AirliftProducer(double maxError) {
        this.maxError = maxError;
    }

    @Override
    public QuantileAdapter compute(Iterator<Double> input) {

        QuantileDigest digest = new QuantileDigest(maxError);
        while (input.hasNext()) {
            digest.add(AirliftHelper.doubleToSortableLong(input.next()));
        }

        return new AirliftAdapter(digest);
    }
}
