package com.badoo.quantile.benchmark.bench.raw;

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

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

/**
 * Basic bench test, that provided probes in range [1,N]
 * Created by krash on 14.02.17.
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgsPrepend = "-XX:+UseG1GC")
@State(Scope.Benchmark)
public abstract class AbstractBenchTest {

    @Param({"10", "100", "1000", "10000", "100000", "1000000"})
    private long probes;

    @Param({"0.005", "0.01"})
    private double accuracy;

    private double iterate(Iterator<Double> input) {
        TestHelper helper = createHelper();
        return helper.createProducerFactory().create(accuracy).compute(input).getQuantile(0.75);
    }

    protected abstract TestHelper createHelper();

    @Test
    public void junitAdapter() throws Exception {
        Helper.run(this.getClass());
    }

    @Benchmark
    public double benchmarkAdd() throws Exception {
        Iterator<Double> stream = DoubleStream.iterate(1, n -> n + 1).limit(probes).boxed().iterator();
        return iterate(stream);
    }

}
