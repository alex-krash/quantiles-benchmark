package com.badoo.bi.quantile.algebird;

import com.badoo.bi.quantile.QuantileAdapter;
import com.twitter.algebird.QTree;

/**
 * Created by krash on 15.02.17.
 */
public class AlgebirdAdapter implements QuantileAdapter {

    final QTree<Double> source;

    public AlgebirdAdapter(QTree<Double> source) {
        this.source = source;
    }

    @Override
    public double getQuantile(double quantile) {
        return AlgebirdHelper.getQuantile(source, quantile);
    }
}
