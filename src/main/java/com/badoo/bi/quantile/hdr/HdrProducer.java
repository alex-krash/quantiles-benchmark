package com.badoo.bi.quantile.hdr;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileProducer;
import org.HdrHistogram.DoubleHistogram;

import java.util.Iterator;

/**
 * Created by krash on 04.07.17.
 */
public class HdrProducer implements QuantileProducer {

    @Override
    public QuantileAdapter compute(Iterator<Double> input) {

        DoubleHistogram histogram = new DoubleHistogram(2);
        while (input.hasNext()) {
            histogram.recordValue(input.next());
        }

        return new HdrAdapter(histogram);
    }
}
