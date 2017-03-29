package com.badoo.bi.quantile.airlift;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileMerger;
import com.facebook.presto.jdbc.internal.airlift.stats.QuantileDigest;

/**
 * Created by krash on 16.02.17.
 */
public class AirliftMerger implements QuantileMerger {

    private AirliftAdapter apply(AirliftAdapter one, AirliftAdapter two) {
        QuantileDigest merged = new QuantileDigest(one.digest);
        merged.merge(two.digest);
        return new AirliftAdapter(merged);
    }

    @Override
    public QuantileAdapter apply(QuantileAdapter one, QuantileAdapter two) {
        return apply((AirliftAdapter) one, (AirliftAdapter) two);
    }
}
