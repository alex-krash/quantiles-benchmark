package com.badoo.quantile.benchmark.merge;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileProducer;
import com.badoo.quantile.benchmark.helper.AirliftTestHelper;
import com.badoo.quantile.benchmark.helper.AlgebirdTestHelper;
import com.badoo.quantile.benchmark.helper.CombinedTestHelper;
import com.badoo.quantile.benchmark.helper.HdrTestHelper;
import com.badoo.quantile.benchmark.helper.NaiveTestHelper;
import com.badoo.quantile.benchmark.helper.TDunningTestHelper;
import com.badoo.quantile.benchmark.helper.TestHelper;
import com.facebook.presto.jdbc.internal.guava.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Iterator;
import java.util.stream.DoubleStream;

/**
 * Test for correctness of merge operations for two sequences
 * Created by krash on 16.02.17.
 */
@RunWith(Parameterized.class)
public class MergeTest {


    private final double start = 1;
    private final long probes = 100_000;
    private final double accuracy = 0.01;
    private final double quantile = .75;
    private final double expectation = 75_000;

    @Parameterized.Parameter
    public TestHelper helper;

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<TestHelper> provider() {
        return Lists.newArrayList(
                new AirliftTestHelper(),
                new AlgebirdTestHelper(),
                new NaiveTestHelper(),
                new TDunningTestHelper(),
                new HdrTestHelper(),
                new CombinedTestHelper()
        );
    }

    @Test
    public void test() throws Exception {

        QuantileProducer producer = helper.createProducerFactory().create(accuracy);

        long middle = probes / 2;
        Iterator<Double> first = DoubleStream.iterate(start, n -> n + 1).limit(middle).boxed().iterator();
        Iterator<Double> second = DoubleStream.iterate(middle + 1, n -> n + 1).limit(middle).boxed().iterator();

        QuantileAdapter one = producer.compute(first);
        QuantileAdapter two = producer.compute(second);
        QuantileAdapter result = helper.createMerger().apply(one, two);

        double quantileValue = result.getQuantile(quantile);
        double error = Math.abs(1 - quantileValue / expectation);
        Assert.assertTrue("Error " + error + " > " + accuracy + ", got value: " + quantileValue, error <= accuracy);

    }

}
