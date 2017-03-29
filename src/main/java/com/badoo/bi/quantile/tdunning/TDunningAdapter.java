package com.badoo.bi.quantile.tdunning;

import com.badoo.bi.quantile.QuantileAdapter;
import com.tdunning.math.stats.TDigest;

/**
 * Created by krash on 15.02.17.
 */
public class TDunningAdapter implements QuantileAdapter {

    final TDigest digest;

    public TDunningAdapter(TDigest digest) {
        this.digest = digest;
    }

    @Override
    public double getQuantile(double quantile) {
        return digest.quantile(quantile);
    }
}
