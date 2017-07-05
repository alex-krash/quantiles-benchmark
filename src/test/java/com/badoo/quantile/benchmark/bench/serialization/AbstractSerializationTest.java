package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.bi.quantile.QuantileAdapter;
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
import com.esotericsoftware.kryo.io.ByteBufferOutput;
import com.esotericsoftware.kryo.io.Output;
import com.twitter.algebird.QTree;
import org.apache.spark.util.SizeEstimator;
import org.junit.Test;
import org.openjdk.jmh.annotations.AuxCounters;
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
import scala.Tuple2;

import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

/**
 * Created by krash on 17.02.17.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = "-XX:+UseG1GC")
@State(Scope.Benchmark)
public abstract class AbstractSerializationTest {

    private static final int BUFFER_SIZE = (int) Math.pow(2, 21);

    @Param({"10", "100", "1000", "10000", "50000", "250000"})
    private long probes;
    @Param({"0.005", "0.01"})
    private double accuracy;

    private QuantileProducer producer;
    private QuantileAdapter result;

    @Setup
    public void setup() {
        producer = createHelper().createProducerFactory().create(accuracy);
    }

    @Setup(Level.Invocation)
    public void setupResult() {
        result = producer.compute(DoubleStream.iterate(1, n -> n + 1).limit(probes).boxed().iterator());
    }

    protected abstract TestHelper createHelper();

    @Test
    public void junitAdapter() throws Exception {
        Helper.run(this.getClass());
    }

    protected long estimateMemorySize(QuantileAdapter adapter) {
        return SizeEstimator.estimate(adapter);
    }

    @Benchmark
    public double benchBytes(KryoWrapper wrapper) throws Exception {
        Tuple2<Output, QuantileAdapter> serialized = serialize(wrapper);
        wrapper.serializedSize = serialized._1().total();
        QuantileAdapter result = serialized._2();
        wrapper.memorySize = estimateMemorySize(result);
        return result.getQuantile(0.75);
    }

    public Tuple2<Output, QuantileAdapter> serialize(KryoWrapper wrapper) {
        Output output = new ByteBufferOutput(BUFFER_SIZE, BUFFER_SIZE);
        wrapper.kryo.writeObject(output, result);
        output.flush();
        return new Tuple2<>(output, result);
    }

    @State(Scope.Thread)
    @AuxCounters(AuxCounters.Type.EVENTS)
    public static class KryoWrapper {
        private final Kryo kryo = new Kryo();
        public long memorySize;
        public long serializedSize;

        {
            kryo.register(AirliftAdapter.class, new AirliftSerializer());
            kryo.register(HdrAdapter.class, new HdrSerializer());
            kryo.register(TDunningAdapter.class, new TDunningSerializer());
            kryo.register(QTree.class, new AlgebirdQTreeSerializer());
            kryo.register(AlgebirdAdapter.class, new AlgebirdQTreeSerializer.AlgebirdAdapterSerializer());
            kryo.register(NaiveAdapter.class, new NaiveSerializer());
            kryo.register(CombinedAdapter.class, new CombinedSerializer());
        }
    }


}
