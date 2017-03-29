package com.badoo.bi.quantile.algebird;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileProducer;
import com.twitter.algebird.QTree;
import com.twitter.algebird.QTreeSemigroup;
import scala.collection.JavaConversions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by krash on 15.02.17.
 */
public class AlgebirdProducer implements QuantileProducer {

    private final QTreeSemigroup<Double> summator;

    public AlgebirdProducer(QTreeSemigroup<Double> summator) {
        this.summator = summator;
    }

    @Override
    public QuantileAdapter compute(Iterator<Double> input) {
        List<QTree<Double>> list = new LinkedList<>();
        while (input.hasNext()) {
            list.add(AlgebirdHelper.create(input.next()));
        }

        return new AlgebirdAdapter(summator.sumOption(JavaConversions.iterableAsScalaIterable(list)).get());
    }
}
