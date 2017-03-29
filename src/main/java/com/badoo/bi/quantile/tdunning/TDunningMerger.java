package com.badoo.bi.quantile.tdunning;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileMerger;
import com.tdunning.math.stats.TDigest;

/**
 * Created by krash on 16.02.17.
 */
public class TDunningMerger implements QuantileMerger {

    private TDunningAdapter apply(TDunningAdapter one, TDunningAdapter two) {

        TDigest merged = TDunningProducer.createDigest(one.digest.compression());
        merged.add(one.digest);
        merged.add(two.digest);
        return new TDunningAdapter(merged);
    }

    @Override
    public QuantileAdapter apply(QuantileAdapter one, QuantileAdapter two) {
        return apply((TDunningAdapter) one, (TDunningAdapter) two);
    }
}
