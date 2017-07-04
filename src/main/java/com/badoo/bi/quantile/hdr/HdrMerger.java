package com.badoo.bi.quantile.hdr;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileMerger;
import org.HdrHistogram.DoubleHistogram;

/**
 * Created by krash on 04.07.17.
 */
public class HdrMerger implements QuantileMerger {
    @Override
    public QuantileAdapter apply(QuantileAdapter one, QuantileAdapter two) {

        HdrAdapter castedOne = (HdrAdapter) one;
        HdrAdapter castedTwo = (HdrAdapter) two;
        DoubleHistogram histogram = new DoubleHistogram(2);
        histogram.add(castedOne.histogram);
        histogram.add(castedTwo.histogram);

        return new HdrAdapter(histogram);
    }
}
