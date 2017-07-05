package com.badoo.bi.quantile.combined;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileMerger;
import org.HdrHistogram.DoubleHistogram;

/**
 * Created by krash on 05.07.17.
 */
public class CombinedMerger implements QuantileMerger {

    public static final int MAX_SIZE_TO_USE_RAW = 1000;

    private static void populate(DoubleHistogram target, CombinedAdapter source) {
        if (null == source.rawData) {
            target.add(source.histogram);
        } else {
            for (double value : source.rawData) {
                target.recordValue(value);
            }
        }
    }

    @Override
    public QuantileAdapter apply(QuantileAdapter one, QuantileAdapter two) {

        CombinedAdapter castOne = (CombinedAdapter) one;
        CombinedAdapter castTwo = (CombinedAdapter) two;

        if ((null == castOne.rawData || null == castTwo.rawData) || castOne.rawData.length + castTwo.rawData.length > MAX_SIZE_TO_USE_RAW) {

            DoubleHistogram aggregate = new DoubleHistogram(2);
            populate(aggregate, castOne);
            populate(aggregate, castTwo);
            return new CombinedAdapter(aggregate);
        } else {
            double[] target = new double[castOne.rawData.length + castTwo.rawData.length];
            System.arraycopy(castOne.rawData, 0, target, 0, castOne.rawData.length);
            System.arraycopy(castTwo.rawData, 0, target, castOne.rawData.length, castTwo.rawData.length);
            return new CombinedAdapter(target);
        }
    }
}
