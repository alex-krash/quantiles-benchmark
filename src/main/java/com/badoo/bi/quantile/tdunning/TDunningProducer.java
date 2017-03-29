package com.badoo.bi.quantile.tdunning;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileProducer;
import com.tdunning.math.stats.TDigest;

import java.util.Iterator;

/**
 * Created by krash on 15.02.17.
 */
public class TDunningProducer implements QuantileProducer {

    private final double compression;

    public TDunningProducer(double compression) {
        this.compression = compression;
    }

    static TDigest createDigest(double compression)
    {
        return TDigest.createDigest(compression);
    }

    @Override
    public QuantileAdapter compute(Iterator<Double> input) {

        TDigest digest = createDigest(compression);

        while (input.hasNext()) {
            digest.add(input.next());
        }

        return new TDunningAdapter(digest);
    }
}
