package com.badoo.quantile.benchmark.bench.mapreduce;

import com.badoo.bi.quantile.QuantileAdapter;
import com.badoo.bi.quantile.QuantileMerger;
import com.badoo.bi.quantile.QuantileProducer;
import com.badoo.bi.quantile.airlift.AirliftAdapter;
import com.badoo.bi.quantile.airlift.AirliftSerializer;
import com.badoo.bi.quantile.algebird.AlgebirdAdapter;
import com.badoo.bi.quantile.algebird.AlgebirdQTreeSerializer;
import com.badoo.bi.quantile.combined.CombinedAdapter;
import com.badoo.bi.quantile.combined.CombinedSerializer;
import com.badoo.bi.quantile.hdr.HdrAdapter;
import com.badoo.bi.quantile.hdr.HdrSerializer;
import com.badoo.bi.quantile.naive.NaiveAdapter;
import com.badoo.bi.quantile.naive.NaiveSerializer;
import com.badoo.bi.quantile.tdunning.TDunningAdapter;
import com.badoo.bi.quantile.tdunning.TDunningSerializer;
import com.badoo.quantile.benchmark.bench.Helper;
import com.badoo.quantile.benchmark.helper.TestHelper;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.algebird.QTree;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

/**
 * Created by krash on 17.02.17.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = "-XX:+UseG1GC")
@State(Scope.Benchmark)
public abstract class AbstractMapReduceTest {

    private static final int COMBINE_SIDE_RESULT_COUNT = 10;
    private static final int REDUCE_SIDE_RESULT_COUNT = 10;

    @Param({"10", "100", "1000", "10000", "500000"})
    private long probes;
    @Param({"0.005", "0.01"})
    private double accuracy;

    private QuantileProducer producer;
    private QuantileMerger merger;
    private List<QuantileAdapter> combineResults;

    @Setup
    public void setup() {
        producer = createHelper().createProducerFactory().create(accuracy);
        merger = createHelper().createMerger();
    }

    @Setup(Level.Invocation)
    public void setupIncomingParams() {
        combineResults = new ArrayList<>();
        QuantileAdapter quantileModel = producer.compute(DoubleStream.iterate(1, n -> n + 1).limit(probes).boxed().iterator());
        for (int i = 0; i < COMBINE_SIDE_RESULT_COUNT; i++) {
            combineResults.add(quantileModel);
        }
    }

    protected abstract TestHelper createHelper();

    @Test
    public void junitAdapter() throws Exception {
        Helper.run(this.getClass());
    }

    @Benchmark
    public QuantileAdapter benchmarkMapReduce(KryoWrapper wrapper) throws Exception {

        // reducing COMBINE_SIDE_RESULT_COUNT results, computed in different sources
        QuantileAdapter combinedResult = combineResults.stream().reduce(merger).get();

        List<QuantileAdapter> reduceSideResults = new ArrayList<>();
        final Class<? extends QuantileAdapter> exactClass = combinedResult.getClass();
        // emulating shuffle behavior
        for (int i = 0; i < 1; i++) {
            wrapper.kryo.writeObject(wrapper.output, combinedResult);
            QuantileAdapter deserialized = wrapper.kryo.readObject(wrapper.input, exactClass);
            reduceSideResults.add(deserialized);
            wrapper.reset();

        }
        return reduceSideResults.stream().reduce(merger).get();
    }

    @State(Scope.Thread)
    public static class KryoWrapper {
        private final Kryo kryo = new Kryo();
        private final ByteBuffer buffer = ByteBuffer.allocate(100 * 1024 * 1024);
        private final Output output;
        private final Input input;

        {
            output = new ByteBufferOutput(buffer);
            input = new ByteBufferInput(buffer);
            kryo.register(AirliftAdapter.class, new AirliftSerializer());
            kryo.register(HdrAdapter.class, new HdrSerializer());
            kryo.register(TDunningAdapter.class, new TDunningSerializer());
            kryo.register(QTree.class, new AlgebirdQTreeSerializer());
            kryo.register(AlgebirdAdapter.class, new AlgebirdQTreeSerializer.AlgebirdAdapterSerializer());
            kryo.register(NaiveAdapter.class, new NaiveSerializer());
            kryo.register(CombinedAdapter.class, new CombinedSerializer());
        }

        public void reset() {
            output.setPosition(0);
            input.rewind();
            buffer.clear();
            kryo.reset();
        }
    }
}
