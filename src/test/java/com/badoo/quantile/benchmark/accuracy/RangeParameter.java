package com.badoo.quantile.benchmark.accuracy;

import scala.Tuple2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Condition object for tests
 * Includes closed range for check, and set of quantile -> expectation
 * Created by krash on 14.02.17.
 */
public class RangeParameter {

    private final Number start;
    private final Number numberOfElements;
    private final double absoluteConfidenceValue;
    private Collection<Tuple2<Double, Double>> conditions;

    public RangeParameter(Number start, Number numberOfElements, double absoluteConfidenceValue, double... varargs) {
        this.start = start;
        this.numberOfElements = numberOfElements;
        this.absoluteConfidenceValue = absoluteConfidenceValue;
        if (varargs.length % 2 != 0) {
            throw new IllegalArgumentException("Wrong varargs number");
        }
        List<Tuple2<Double, Double>> expectations = new ArrayList<>();
        for (int i = 0; i < varargs.length; i += 2) {
            expectations.add(new Tuple2<>(varargs[i], varargs[i + 1]));
        }
        conditions = expectations;
    }

    public RangeParameter(RangeParameter source, double absoluteConfidenceValue) {
        this(source.start, source.numberOfElements, absoluteConfidenceValue);
        this.conditions = source.getConditions();
    }

    public Double getAbsoluteConfidenceValue() {
        return absoluteConfidenceValue;
    }

    public Number getStart() {
        return start;
    }

    public Number getNumberOfElements() {
        return numberOfElements;
    }

    public Collection<Tuple2<Double, Double>> getConditions() {
        return conditions;
    }

    @Override
    public String toString() {
        return "Range [" + start + ", " + numberOfElements + "], " + absoluteConfidenceValue * 100d + "% err, " + conditions.size() + " predicates";
    }
}
