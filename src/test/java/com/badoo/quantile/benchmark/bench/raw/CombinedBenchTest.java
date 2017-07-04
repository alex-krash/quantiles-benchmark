package com.badoo.quantile.benchmark.bench.raw;

import com.badoo.quantile.benchmark.bench.Helper;

/**
 * Created by krash on 15.02.17.
 */
public class CombinedBenchTest {

    public static void main(String... argv) throws Exception {
        Helper.run(AirliftBenchTest.class, AlgebirdBenchTest.class, NaiveBenchTest.class, TDunningBenchTest.class, HdrBenchTest.class);
    }
}
