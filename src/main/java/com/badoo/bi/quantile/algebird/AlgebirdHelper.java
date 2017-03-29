package com.badoo.bi.quantile.algebird;

import com.twitter.algebird.QTree;
import scala.Tuple2;

/**
 * Created by krash on 15.02.17.
 */
public class AlgebirdHelper {
    public static QTree<Double> create(double input) {
        QTree result = QTree.apply(input);
        QTree<Double> retval = (QTree<Double>) result;
        return retval;
    }

    public static double getQuantile(QTree<Double> tree, double quantile)
    {
        Tuple2<Object, Object> bounds = tree.quantileBounds(quantile);
        return ((Double) bounds._1() + (Double) bounds._2()) / 2D;
    }
}
