package com.badoo.bi.quantile.airlift;

import com.badoo.bi.quantile.QuantileAdapter;
import com.facebook.presto.jdbc.internal.airlift.stats.QuantileDigest;
import com.google.common.annotations.VisibleForTesting;

/**
 * Created by krash on 15.02.17.
 */
public class AirliftAdapter implements QuantileAdapter {

    @VisibleForTesting
    public final QuantileDigest digest;

    public AirliftAdapter(QuantileDigest digest) {
        this.digest = digest;
    }

    @Override
    public double getQuantile(double quantile) {
        return AirliftHelper.sortableLongToDouble(digest.getQuantile(quantile));
    }
}
