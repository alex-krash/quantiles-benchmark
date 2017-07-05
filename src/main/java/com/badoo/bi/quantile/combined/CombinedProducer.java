package com.badoo.bi.quantile.combined;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileProducer;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import org.HdrHistogram.DoubleHistogram;

import java.util.Iterator;

/**
 * Created by krash on 05.07.17.
 */
public class CombinedProducer implements QuantileProducer {
    @Override
    public QuantileAdapter compute(Iterator<Double> input) {

        DoubleHistogram histogram = null;
        double[] temp = new double[CombinedMerger.MAX_SIZE_TO_USE_RAW];

        DoubleList list = new DoubleArrayList();

        while (input.hasNext()) {
            double inputValue = input.next();

            if (list.size() + 1 >= temp.length) {
                if (null == histogram) {
                    histogram = new DoubleHistogram(2);
                    for (double value : list) {
                        histogram.recordValue(value);
                    }
                }
                histogram.recordValue(inputValue);
            } else {
                list.add(inputValue);
            }
        }

        if (null == histogram) {
            return new CombinedAdapter(list.toDoubleArray());
        } else {
            return new CombinedAdapter(histogram);
        }
    }
}
