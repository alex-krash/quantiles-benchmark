package com.badoo.quantile.benchmark.bench.raw;

import com.badoo.quantile.benchmark.bench.Helper;

/**
 * Entrypoint for running all the classes within this bench
 * Created by krash on 15.02.17.
 */
public class UnitedBenchTest {

    public static void main(String... argv) throws Exception {
        Helper.run(AirliftBenchTest.class, AlgebirdBenchTest.class, NaiveBenchTest.class, TDunningBenchTest.class, HdrBenchTest.class);
    }
}
