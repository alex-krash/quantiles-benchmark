package com.badoo.quantile.benchmark.bench.mapreduce;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileProducer;
import com.badoo.quantile.benchmark.bench.Helper;
import com.badoo.quantile.benchmark.helper.TestHelper;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

/**
 * Created by krash on 17.02.17.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = "-XX:+UseG1GC")
@State(Scope.Benchmark)
public abstract class AbstractMapReduceTest {

    @Param({"10", "100", "1000", "10000", "500000"})
    private long probes;

    @Param({"0.005", "0.01"})
    private double accuracy;

    protected abstract TestHelper createHelper();

    @Test
    public void junitAdapter() throws Exception {
        Helper.run(this.getClass());
    }

    @Benchmark
    public double benchmarkMapReduce() throws Exception {

        final int parts = 10;
        long step = probes / parts;
        final TestHelper helper = createHelper();
        QuantileProducer producer = helper.createProducerFactory().create(accuracy);

        List<QuantileAdapter> toReduce = new ArrayList<>();
        for (int i = 0; i < parts; i++) {
            int start = (int) (i * step) + 1;
            toReduce.add(producer.compute(DoubleStream.iterate(start, n -> n + 1).limit(step).boxed().iterator()));
        }
        return toReduce.stream().reduce(helper.createMerger()).get().getQuantile(0.75);
    }
}
