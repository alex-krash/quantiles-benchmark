package com.badoo.quantile.benchmark.accuracy;

import com.badoo.bi.quantile.ProducerFactory;
import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.quantile.benchmark.helper.TestHelper;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import scala.Tuple2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;

/**
 * Test for providing given accuracy for range of input numbers
 * Created by krash on 15.02.17.
 */
@RunWith(Parameterized.class)
public abstract class AbstractAccuracyTest {

    // Accuracy 0.01 = 1 %
    private static final double DESIRED_ACCURACY_SMALL_SEQUENCE = 0.015;

    // whe check for this accuracies for our probes
    private static final List<Double> ACCURACIES = Lists.newArrayList(0.005, 0.01, 0.015, 0.02);

    @Parameterized.Parameter
    public RangeParameter range;

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<RangeParameter> data() {

        ArrayList<RangeParameter> protoType = Lists.newArrayList(
                new RangeParameter(1, 10_000, -1, 0.5D, 5_001),
                new RangeParameter(1, 100_000, -1, 0.5D, 50_000, 0.75, 75_000, 0.99, 99_001),
                new RangeParameter(1, 1_000_000, -1, 0.5D, 500_000, 0.75, 750_000, 0.99, 990_001)
        );

        List<RangeParameter> retval = new ArrayList<>();
        retval.add(new RangeParameter(1, 100, DESIRED_ACCURACY_SMALL_SEQUENCE, 0.5D, 51));
        ACCURACIES.forEach(accuracy -> protoType.forEach(rangeParameter -> retval.add(new RangeParameter(rangeParameter, accuracy))));
        return retval;
    }

    protected abstract TestHelper getHelper();

    @Test
    public final void test() throws Exception {

        final long numberOfElements = range.getNumberOfElements().longValue();
        Iterator<Double> iterator = DoubleStream.iterate(range.getStart().doubleValue(), n -> n + 1).limit(numberOfElements).boxed().iterator();

        final double maxAllowedError = range.getAbsoluteConfidenceValue();

        ProducerFactory factory = getHelper().createProducerFactory();
        QuantileAdapter adapter = factory.create(maxAllowedError).compute(iterator);

        DecimalFormat format = new DecimalFormat("##.######");
        range.getConditions().forEach(new Consumer<Tuple2<Double, Double>>() {
            @Override
            public void accept(Tuple2<Double, Double> tuple) {
                double quantile = tuple._1();
                double expected = tuple._2();
                double quantileValue = adapter.getQuantile(quantile);

                double error = Math.abs(1 - quantileValue / expected);

                String errorMessage = "Error " + format.format(error) + " > " + format.format(maxAllowedError) + ", probes " + numberOfElements + " for p(" + quantile + "=" + expected + "): " + quantileValue;
                Assert.assertTrue(errorMessage, error <= maxAllowedError);
            }
        });
    }

}
