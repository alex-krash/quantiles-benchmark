package com.badoo.quantile.benchmark.bench.serialization;

import com.badoo.quantile.benchmark.bench.Helper;
import com.badoo.quantile.benchmark.bench.raw.AirliftBenchTest;
import com.badoo.quantile.benchmark.bench.raw.AlgebirdBenchTest;
import com.badoo.quantile.benchmark.bench.raw.NaiveBenchTest;
import com.badoo.quantile.benchmark.bench.raw.TDunningBenchTest;

/**
 * Created by krash on 17.02.17.
 */
public class CombinedSerializationTest {

    public static void main(String... argv) throws Exception {
        Helper.run(AirliftSerializationTest.class, AlgebirdSerializationTest.class, NaiveSerializationTest.class, TDunningSerializationTest.class, HdrSerializationTest.class);
    }

}
