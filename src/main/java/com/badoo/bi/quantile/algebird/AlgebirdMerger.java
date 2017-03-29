package com.badoo.bi.quantile.algebird;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileMerger;
import com.google.common.collect.Lists;
import com.twitter.algebird.QTree;
import com.twitter.algebird.QTreeSemigroup;
import scala.collection.JavaConversions;

/**
 * Created by krash on 16.02.17.
 */
public class AlgebirdMerger implements QuantileMerger {

    private final QTreeSemigroup<Double> summator;

    public AlgebirdMerger(QTreeSemigroup<Double> summator) {
        this.summator = summator;
    }

    private AlgebirdAdapter apply(AlgebirdAdapter one, AlgebirdAdapter two) {
        QTree<Double> mergedTree = summator.sumOption(JavaConversions.collectionAsScalaIterable(Lists.newArrayList(one.source, two.source))).get();
        return new AlgebirdAdapter(mergedTree);
    }

    @Override
    public QuantileAdapter apply(QuantileAdapter one, QuantileAdapter two) {
        return apply((AlgebirdAdapter) one, (AlgebirdAdapter) two);
    }
}
